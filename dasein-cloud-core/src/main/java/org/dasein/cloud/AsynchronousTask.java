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

import javax.annotation.Nonnegative;
import javax.annotation.Nullable;

public class AsynchronousTask<T> {
	private double           percentComplete;
	private long             endTime;
	private T                result;
	private long             startTime;
	private Throwable        taskError;
	
	public AsynchronousTask() { 
		startTime = System.currentTimeMillis();
		endTime = -1L;
	}

	public synchronized void complete(@Nullable Throwable withError) {
		taskError = withError;
		endTime = System.currentTimeMillis();
		notifyAll();
	}
	
	public synchronized void completeWithResult(@Nullable T result) {
		this.result = result;
		endTime = System.currentTimeMillis();
		notifyAll();
	}
	
	public synchronized boolean isComplete() {
		return endTime > -1L;
	}
	
	public synchronized long getDuration() {
		if( endTime == -1L ) {
			return System.currentTimeMillis() - startTime;
		}
		else {
			return endTime - startTime;
		}
	}
	
	public synchronized @Nonnegative double getPercentComplete() {
		return percentComplete;
	}

	public synchronized void setPercentComplete(@Nonnegative double percentComplete) {
		this.percentComplete = percentComplete;
		notifyAll();
	}

	public synchronized @Nonnegative long getEndTime() {
		return endTime;
	}
	
	public @Nullable T getResult() {
		return result; 
	}

	public @Nonnegative long getStartTime() {
		return startTime;
	}

	public void setStartTime(@Nonnegative long startTime) {
		this.startTime = startTime;
	}

	public @Nullable Throwable getTaskError() {
		return taskError;
	}
}
