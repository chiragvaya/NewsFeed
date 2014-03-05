package com.bliss.android.helloworld.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuActivity extends Activity {
	String data;
	private TextToSpeech mSpeech;
	List<MenuItem> menus = new ArrayList<MenuItem>();
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //data = getIntent().getExtras().getString("message");
	    //System.out.println(data);
	    mSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
	    	
	        @Override
	        public void onInit(int status) {
	            // Do nothing.
	        }
	    });
	}
	
	public Object json(int menuid)
	{
		JSONObject obj = new JSONObject();
		try {
			System.out.println("goes into try of json");
			obj.put("text", data);
			obj.put("menuItems", new JSONObject().put("action", "READ_ALOUD"));
			//obj.put("menuItems", new JSONObject().put("payload", "https://developers.google.com/glass/develop/mirror/menu-items"));
			System.out.println(obj);
			//System.out.println(data);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.read_aloud_menu_item:
            	System.out.println("goes itno read aloud case");
            	//mSpeech.speak(json(item.getItemId()).toString(), TextToSpeech.QUEUE_FLUSH, null);
            	mSpeech.speak("Hello how are you", TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(item.getItemId());
                
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the activity.
        finish();
    }
    public void onDestroy() {
        mSpeech.shutdown();
        mSpeech = null;
        super.onDestroy();
    }
}
