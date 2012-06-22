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

package org.dasein.cloud;


import junit.framework.TestCase;

import org.junit.Test;

public class TaskTestCase extends TestCase {
    public TaskTestCase() { }
    
    @Test
    public void testProgressiveTask() { 
        final AsynchronousTask<String> task = new AsynchronousTask<String>();
        
        Thread t = new Thread() {
            public void run() {
                for( int i = 1; i< 10; i++ ) {
                    try { Thread.sleep(1000L); }
                    catch( InterruptedException e ) { }
                    task.setPercentComplete(i*10);
                }
                try { Thread.sleep(1000L); }
                catch( InterruptedException e ) { }
                task.setPercentComplete(100);
                task.completeWithResult("Success");
            }
        };
        
        t.start();
        
        double d = task.getPercentComplete();
        double expected = 10.0;
        
        while( task.getPercentComplete() < 99.99 ) {
            if( task.isComplete() && task.getPercentComplete() < 99.99) {
                fail("Task completed unexpectedly.");
            }
            assertTrue("Task completion was not expected: " + expected + " vs. " + d, d <= expected);
            try { Thread.sleep(1000L); }
            catch( InterruptedException e ) { }
            expected += 10.0;
        }
        try { Thread.sleep(5000L); }
        catch( InterruptedException e ) { }
        assertTrue("Task never completed.", task.isComplete());
    }
    
    @Test
    public void testTaskDuration() { 
        final AsynchronousTask<String> task = new AsynchronousTask<String>();
        
        Thread t = new Thread() {
            public void run() {
                try { Thread.sleep(1000L); }
                catch( InterruptedException e ) { }
                task.completeWithResult("Success");
            }
        };
        
        t.start();
        
        while( !task.isComplete() ) {
            try { Thread.sleep(1000L); }
            catch( InterruptedException e ) { }
        }
        assertTrue("Task duration is suspect: " + task.getDuration(), (task.getDuration() >= 1000L) && (task.getDuration() <= 20000L));
    }
    
    @Test
    public void testTaskError() { 
        final AsynchronousTask<String> task = new AsynchronousTask<String>();
        
        Thread t = new Thread() {
            public void run() {
                try { Thread.sleep(1000L); }
                catch( InterruptedException e ) { }
                task.complete(new RuntimeException("Failed"));
            }
        };
        
        t.start();
        
        while( !task.isComplete() ) {
            try { Thread.sleep(1000L); }
            catch( InterruptedException e ) { }
        }
        Throwable error = task.getTaskError();
        
        assertNotNull("Task did not supply an error.", error);
        assertTrue("Error was not an expected error: " + error.getClass().getName() + " vs. java.lang.RuntimeException", error instanceof RuntimeException);
        assertEquals("Error message is not expected message: " + error.getMessage(), "Failed", error.getMessage());
    }
    
    @Test
    public void testTaskResult() {
        final AsynchronousTask<String> task = new AsynchronousTask<String>();
        
        Thread t = new Thread() {
            public void run() {
                try { Thread.sleep(1000L); }
                catch( InterruptedException e ) { }
                task.completeWithResult("Success");
            }
        };
        
        t.start();
        
        while( !task.isComplete() ) {
            try { Thread.sleep(1000L); }
            catch( InterruptedException e ) { }
        }
        assertEquals("Invalid task result: " + task.getResult(), "Success", task.getResult());
    }
}
