package com.kar.GlassBTCPrice;

import com.kar.GlassBTCPrice.RestAsync;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import com.google.android.glass.app.Card;
import java.util.Locale;

/**
 * @author Ben
 * 
 * This class handles the TTS, card and the async callback. 
 */
public class kar extends Activity implements TextToSpeech.OnInitListener, AsyncTaskCompleteListener<String> {

	public TextToSpeech tts;
	private int result = 0;

	@Override
	protected void onDestroy() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}

		super.onDestroy();
	}

	/**
	 * Called when the activity is first created.
	 * Setup the TTS engine and start an Asynchronous web request
	 * to get bitcoin price data. 
	 * Additionally, generate a card for the user that lets them
	 * know we are working on the request. 
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tts = new TextToSpeech(this, this);
		new RestAsync<Object>(getApplicationContext(), this).execute();

		Card card1 = new Card(getApplicationContext());
		card1.setText("Fetching bitcoin price");
		View card1View = card1.toView();

		setContentView(card1View);
	}

	/**
	 * Called when the TTS engine is initialized. 
	 * @see android.speech.tts.TextToSpeech.OnInitListener#onInit(int)
	 * 
	 * @param status The status of the TTS initialization
	 */
	@Override
	public void onInit(int status) {    	
		if (status == TextToSpeech.SUCCESS) {
			result = tts.setLanguage(Locale.US);
			if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Unsupported Language");
			} 
		} else {
			Log.e("TTS", "Initilization Failed");
		}
	}

	/** 
	 * @param result The result of the Async call
	 */
	public void onTaskComplete(String result) {
		this.sayBTCPrice(result);
	}

	/** 
	 * Format the price, create a new card and use speech to say the price. 
	 * 
	 * @param price The price of bitcoin as a string of a floating point number (xxx.xxxxx)
	 */
	public void sayBTCPrice(String price) {	
		// Format the price to include the hundredth place and greater. (xxx.xx)
		int spaceIndex = price.indexOf(".");
		if (spaceIndex != -1) {
			price = price.substring(0, spaceIndex);
		}

		// Place a new card in the view
		Card card1 = new Card(getApplicationContext());
		card1.setText("$" + price);
		View card1View = card1.toView();
		setContentView(card1View);

		String text = new String("The current bit coin price is " + price + " dollars");
		if(result >= 0) {
			tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}
	}
}