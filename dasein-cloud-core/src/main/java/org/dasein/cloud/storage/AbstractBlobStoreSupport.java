/**
 * Copyright (C) 2009-2012 enStratus Networks Inc.
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.apache.commons.codec.binary.Base64;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.encryption.Encryption;
import org.dasein.cloud.encryption.EncryptionException;
import org.dasein.util.Retry;

public abstract class AbstractBlobStoreSupport implements BlobStoreSupport {
    private byte[] computeMD5Hash(InputStream is) throws NoSuchAlgorithmException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[16384];
            int bytesRead = -1;
            while ((bytesRead = bis.read(buffer, 0, buffer.length)) != -1) {
                messageDigest.update(buffer, 0, bytesRead);
            }
            return messageDigest.digest();
        } 
        finally {
            try {
                bis.close();
            } catch (Exception e) {
                System.err.println("Unable to close input stream of hash candidate: " + e);
            }
        }
    }
    
    protected CloudStoreObject copy(CloudStoreObject file, CloudStoreObject toDirectory, String copyName) throws InternalException, CloudException {
        if( file.isContainer() ) {
            CloudStoreObject directory = new CloudStoreObject();
            String pathName;
            int idx;
             
            directory.setContainer(true);
            directory.setCreationDate(new Date());
            directory.setSize(0);
            if( file.getDirectory() != null ) {
                pathName = createDirectory(file.getDirectory() + "." + copyName, true);
            }
            else {
                pathName = createDirectory(copyName, true);
            }
            idx = pathName.lastIndexOf('.');
            String tmp = pathName;
            while( idx > -1 && idx == tmp.length()-1 ) {
                tmp = tmp.substring(0, idx);
                idx = tmp.lastIndexOf('.');
            }
            if( idx == -1 ) {
                directory.setDirectory(null);
                directory.setName(pathName);
            }
            else {
                directory.setDirectory(pathName.substring(0, idx));
                directory.setName(pathName.substring(idx+1));
            }
            for( CloudStoreObject f : listFiles(file.getDirectory()) ) {
                copy(f, directory, f.getName());
            }
            return directory;
        }
        else {
            return copyFile(file, toDirectory, copyName);
        }
    }
    
    protected void copy(InputStream input, OutputStream output, FileTransfer xfer) throws IOException {
        try {
            byte[] bytes = new byte[10240];
            long total = 0L;
            int count;
            
            if( xfer != null ) {
                xfer.setBytesTransferred(0L);
            }
            while( (count = input.read(bytes, 0, 10240)) != -1 ) {
                if( count > 0 ) {
                    output.write(bytes, 0, count);
                    total = total + count;
                    if( xfer != null ) {
                        xfer.setBytesTransferred(total);
                    }
                }
            }
            output.flush();
        }
        finally {
            input.close();
            output.close();
        }
    }
    
    protected CloudStoreObject copyFile(CloudStoreObject file, CloudStoreObject toDirectory, String newName) throws InternalException, CloudException {
        CloudStoreObject replacement;
        File tmp = null;
        
        newName = verifyName(newName, file.isContainer());
        try {
            try {
                tmp = File.createTempFile("file", ".tmp");
            }
            catch( IOException e ) {
                e.printStackTrace();
                throw new InternalException(e);
            }
            try {
                get(file.getDirectory(), file.getName(), tmp, null);
                put(toDirectory.getDirectory() + "." + toDirectory.getName(), newName, tmp);
            }
            catch( IOException e ) {
                e.printStackTrace();
                throw new InternalException(e);
            }
            catch( NoSuchAlgorithmException e ) {
                e.printStackTrace();
                throw new InternalException(e);
            }
        }
        finally {
            if( tmp != null ) { 
                tmp.delete();
            }
        }
        replacement = new CloudStoreObject();
        replacement.setContainer(file.isContainer());
        replacement.setCreationDate(new Date());
        replacement.setDirectory(toDirectory.getDirectory() + "." + toDirectory.getName());
        replacement.setLocation(replacement.getDirectory() + getSeparator() + newName);
        replacement.setName(newName);
        replacement.setProviderRegionId(toDirectory.getProviderRegionId());
        replacement.setSize(file.getSize());
        return replacement;
    }
    
    @Override
    public FileTransfer download(CloudStoreObject cloudFile, File diskFile) throws CloudException, InternalException {
        final FileTransfer transfer = new FileTransfer();
        final CloudStoreObject source = cloudFile;
        final File target = diskFile;
            
        transfer.setBytesToTransfer(exists(cloudFile.getDirectory(), cloudFile.getName(), false));
        if( transfer.getBytesToTransfer() == -1L ) {
            throw new CloudException("No such file: " + cloudFile.getDirectory() + "." + cloudFile.getName());
        }
        Thread t = new Thread() {
            public void run() {
                Callable<Object> operation = new Callable<Object>() {
                    public Object call() throws Exception {
                        boolean success = false;
                        
                        try {
                            get(source.getDirectory(), source.getName(), target, transfer);
                            success = true;
                            return null;
                        }
                        finally {
                            if( !success ) {
                                if( target.exists() ) {
                                    target.delete();
                                }
                            }
                        }
                    }
                };
                try {
                    (new Retry<Object>()).retry(5, operation);
                    transfer.complete(null);
                }
                catch( CloudException e ) {
                    transfer.complete(e);
                }
                catch( InternalException e ) {
                    transfer.complete(e);
                }
                catch( Throwable t ) {
                    t.printStackTrace();
                    transfer.complete(t);
                }
            }
        };
        
        t.setDaemon(true);
        t.start();
        return transfer;
    }
    
    @Override
    public FileTransfer download(String directory, String fileName, File toFile, Encryption encryption) throws InternalException, CloudException {
        final FileTransfer transfer = new FileTransfer();
        final Encryption enc = encryption;
        final String dname = directory;
        final String fname = fileName;
        final File target = toFile;

        Thread t = new Thread() {
            public void run() {
                try {
                    Callable<Object> operation = new Callable<Object>() {
                        public Object call() throws Exception {
                            boolean success = false;
                            
                            try {
                                downloadMultipartFile(dname, fname, target, transfer, enc);
                                success = true;
                                return null;
                            }
                            finally {
                                if( !success ) {
                                    if( target.exists() ) {
                                        target.delete();
                                    }
                                }
                            }
                        }
                    };
                    try {
                        (new Retry<Object>()).retry(5, operation);
                        transfer.complete(null);
                    }
                    catch( CloudException e ) {
                        transfer.complete(e);
                    }
                    catch( InternalException e ) {
                        transfer.complete(e);
                    }
                    catch( Throwable t ) {
                        t.printStackTrace();
                        transfer.complete(t);
                    }
                }
                finally {
                    if( enc != null ) {
                        enc.clear();
                    }
                }
            }
        };
        
        t.setDaemon(true);
        t.start();
        return transfer;            

    }
    
    protected void downloadMultipartFile(String directory, String fileName, File restoreFile, FileTransfer transfer, Encryption encryption) throws CloudException, InternalException {
        try {
            File f;
            String str;
            int parts;
            
            if( restoreFile.exists() ) {
                if( !restoreFile.delete() ) {
                    throw new InternalException("Unable to delete restore file: " + restoreFile.getAbsolutePath());
                }
            }
            f = File.createTempFile("download", ".dl");
            f.deleteOnExit();
            Properties props = new Properties();
            try {
                get(directory, fileName + ".properties", f, transfer);                
                props.load(new FileInputStream(f));
            }
            finally {
                f.delete();
            }
            try {
                boolean newEncryption;
                
                str = props.getProperty("encrypted");
                newEncryption = (str != null && str.equalsIgnoreCase("true"));
                if( newEncryption ) {
                    str = props.getProperty("encryptionVersion");
                    if( str != null ) {
                        try {
                            encryption = (Encryption)Class.forName(str).newInstance();
                        }
                        catch( Exception e ) {
                            if( encryption == null ) {
                                throw new CloudException("Encryption mismatch: " + str);
                            }
                        }
                    }
                }
                str = props.getProperty("parts");
                parts = (str == null ? 1 : Integer.parseInt(str));
                
                String checksum = props.getProperty("checksum");
                
                File encFile = null;
                
                if( encryption != null ) {
                    encFile = new File(restoreFile.getAbsolutePath() + ".enc");
                    if( encFile.exists() ) {
                        encFile.delete();
                    }
                }
                for( int i = 1; i<=parts; i++ ) {
                    FileOutputStream out;
                    FileInputStream in;
                    
                    if( f.exists() ) {
                        f.delete();
                    }
                    f = File.createTempFile("part", "." + i);
                    get(directory, fileName + ".part." + i, f, transfer);
                    in = new FileInputStream(f);
                    if( encryption != null ) {
                        out = new FileOutputStream(encFile, true);
                    }
                    else {
                        out = new FileOutputStream(restoreFile, true);                
                    }
                    copy(in, out, transfer);
                }
                if( encryption != null ) {
                    try {
                        try {
                            try {
                                if( !getChecksum(new FileInputStream(encFile)).equals(checksum) ) {
                                    throw new IOException("Checksum mismatch.");
                                }
                            }
                            catch( NoSuchAlgorithmException e ) {
                                e.printStackTrace();
                                throw new InternalException(e.getMessage());
                            }
                            encryption.decrypt(new FileInputStream(encFile), new FileOutputStream(restoreFile));
                        }
                        finally {
                            if( encFile.exists() ) {
                                encFile.delete();
                            }
                        }
                    }
                    catch( EncryptionException e ) {
                        e.printStackTrace();
                        throw new InternalException(e);
                    }
                }
                else {
                    try {
                        if( !getChecksum(new FileInputStream(restoreFile)).equals(checksum) ) {
                            throw new IOException("Checksum mismatch.");
                        }
                    }
                    catch( NoSuchAlgorithmException e ) {
                        e.printStackTrace();
                        throw new InternalException(e.getMessage());
                    }
                }
            }
            finally {
                if( f != null && f.exists() ) {
                    f.delete();
                }
            }
        }
        catch( IOException e ) {
            e.printStackTrace();
            throw new InternalException(e);
        }
    }
    
    @Override
    public boolean exists(String abstractDirectoryName) throws InternalException, CloudException {
        return (exists(abstractDirectoryName, null, false) != -1L);
    }
    
    protected abstract void get(String directory, String location, File toFile, FileTransfer transfer) throws IOException, CloudException;
        
        
    protected String getChecksum(InputStream input) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        return toBase64(computeMD5Hash(input));
    }
    
    public String getSeparator() {
        return ".";
    }
    
    protected final String join(String separator, String ... path) {
        if( path == null || path.length < 1 ) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        
        for( int i=0; i<path.length; i++ ) {
            if( i > 0 ) {
                str.append(separator);
            }
            str.append(path[i]);
        }
        return str.toString();
    }
    
    protected abstract void put(String directory, String fileName, File file) throws NoSuchAlgorithmException, IOException, CloudException;
        
    protected abstract void put(String directory, String fileName, String content) throws NoSuchAlgorithmException, IOException, CloudException;
        
    @Override
    public void removeFile(String directory, String name, boolean multipartFile) throws CloudException, InternalException {     
        if( !multipartFile ) {
            removeFile(directory, name);
        }
        else {
            removeMultipart(directory, name);
        }
    }
    
    protected abstract void removeFile(String directory, String name) throws CloudException, InternalException;
    
    protected void removeMultipart(String bucket, String object) throws InternalException, CloudException {
        File propsFile = null;
        
        try {
            try {
                Properties props = new Properties();
                String str;
                int parts;
                
                propsFile = File.createTempFile("props", ".properties");            
                get(bucket, object + ".properties", propsFile, null);        
                props.load(new FileInputStream(propsFile));
                str = props.getProperty("parts");
                parts = (str == null ? 1 : Integer.parseInt(str));
                removeFile(bucket, object + ".properties");
                for( int i = 1; i<=parts; i++ ) {
                    removeFile(bucket, object + ".part." + i);
                }  
            }
            catch( IOException e ) {
                e.printStackTrace();
                throw new InternalException(e);
            }
        }
        finally {
            if( propsFile != null ) {
                propsFile.delete();
            }
        }
    }
    
    private String toBase64(byte[] data) {
        byte[] b64 = Base64.encodeBase64(data);
        
        return new String(b64);
    }
    
    protected void uploadMultipartFile(File sourceFile, String directory, String fileName, Encryption encryption) throws InterruptedException, InternalException, CloudException {
        File toDelete = null;
        File toUpload;
        
        if( encryption == null ) {
            toUpload = sourceFile;
        }
        else {
            try {
                FileInputStream input = new FileInputStream(sourceFile);
                FileOutputStream output;
                
                toDelete = File.createTempFile(fileName, ".enc");                    
                output = new FileOutputStream(toDelete);
                encryption.encrypt(input, output);
                output.flush();
                output.close();
                input.close();
                toUpload = toDelete;
            }
            catch( EncryptionException e ) {
                e.printStackTrace();
                throw new InternalException(e);
            }
            catch( IOException e ) {
                e.printStackTrace();
                throw new InternalException(e);
            }
        }
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(toUpload));

            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                BufferedOutputStream output;
                byte[] buffer = new byte[32768];
                long count = 0;
                int b, partNumber = 1;
                File part;
                
                part = File.createTempFile(fileName, ".part." + partNumber);
                output = new BufferedOutputStream(new FileOutputStream(part));
                while( (b = input.read(buffer, 0, buffer.length)) > 0 ) {
                    count += b;
                    output.write(buffer, 0, b);
                    messageDigest.update(buffer, 0, b);
                    if( count >= 2000000000L ) {
                        int tries = 5;
                        
                        output.flush();
                        output.close();
                        while( true ) {
                            tries--;
                            try {
                                put(directory, null, part);
                                break;
                            }
                            catch( NoSuchAlgorithmException e ) {
                                e.printStackTrace();
                                if( tries < 1 ) {
                                    throw new InternalException("Unable to complete upload for part " + partNumber + " of " + part.getAbsolutePath());
                                }
                            }                            
                        }
                        part.delete();
                        partNumber++;
                        part = File.createTempFile(fileName, ".part." + partNumber);
                        output = new BufferedOutputStream(new FileOutputStream(part));
                        count = 0L;
                    }
                }
                if( count > 0L ) {
                    int tries = 5;
                    
                    output.flush();
                    output.close();
                    while( true ) {
                        tries--;
                        try {
                            put(directory, fileName + ".part." + partNumber, part);
                            break;
                        }
                        catch( NoSuchAlgorithmException e ) {
                            e.printStackTrace();
                            if( tries < 1 ) {
                                throw new InternalException("Unable to complete upload for part " + partNumber + " of " + part.getAbsolutePath());
                            }
                        }                        
                    }                
                    part.delete();            
                }
                String content = "parts=" + partNumber + "\nchecksum=" + toBase64(messageDigest.digest()) + "\n" ;
                
                if( encryption != null ) {
                    content = content + "encrypted=true\n";
                    content = content + "encryptionVersion=" + encryption.getClass().getName() + "\n";
                }
                else {
                    content = content + "encrypted=false\n";
                }
                content = content + "\nlength=" + sourceFile.length();
                int tries = 5;
                while( true ) {
                    tries--;
                    try {
                        put(directory, fileName + ".properties", content);
                        break;
                    }
                    catch( NoSuchAlgorithmException e ) {
                        e.printStackTrace();
                        if( tries < 1 ) {
                            throw new InternalException("Unable to complete upload for properties of " + part.getAbsolutePath());
                        }
                    }                              
                }
            }
            catch( NoSuchAlgorithmException e ) {
                e.printStackTrace();
                throw new InternalException(e);
            }
            finally {
                if( toDelete != null ) {
                    toDelete.delete();
                }
            }
        }
        catch( IOException e ) {
            e.printStackTrace();
            throw new InternalException(e);
        }
    }
    
    protected String verifyName(String name, boolean container) throws CloudException {
        return name;
    }
}
