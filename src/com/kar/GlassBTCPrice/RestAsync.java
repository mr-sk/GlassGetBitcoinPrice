/**
 * 
 */
package com.kar.GlassBTCPrice;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

/**
 * @author Ben
 * 
 * This class will make an async request in a separate thread than main.  
 * When instantiating this class a callback must be provided, which will 
 * be called after "onPostExecute()" is called. 
 */
public class RestAsync<Result> extends AsyncTask<Object, Object, Object> {
	private AsyncTaskCompleteListener<String> callback;
	
	/**
	 * Class constructor sets the callback to use when our async operation is complete. 
	 * 
	 * @param context Application context, set by caller
	 * @param cb      Callback method name, set by caller
	 */
	public RestAsync(Context context, AsyncTaskCompleteListener<String> cb) {
		this.callback = cb;
	}

	/**
	 * Make a REST Get request to the provided URL. The response
	 * object is JSON so transform to a JSON object and get the value
	 * for the provided key. 
	 * 
	 * @return price Bitcoin price
	 */
	@Override
	protected Object doInBackground(Object... arg0) {	

	    DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(
        		"https://coinbase.com/api/v1/currencies/exchange_rates");
        request.addHeader("accept", "application/json");

		String price = new String();
        
        try {
			HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            JSONObject jo = new JSONObject(result);	
            price = jo.getString("btc_to_usd");
     
        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return price;
	}
	
	@Override
	/**
	 * Executes the callback method, sending the data back to the main thread. 
	 * 
	 * @param result The result of the doInBackground() operation
	 */
	protected void onPostExecute (Object result) {		
	    callback.onTaskComplete((String) result);
	}
}
