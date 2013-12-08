package com.kar.GlassBTCPrice;

/** 
 * @author Ben
 *
 * @param <T>
 * 
 * Classes implement this interface and single method to allow
 * call backs from other threads. 
 */
interface AsyncTaskCompleteListener<T> {
	public void onTaskComplete(T result);
}
