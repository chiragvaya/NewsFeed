package com.bliss.android.helloworld.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class MenuActivity extends Activity {
	String data="Hello";
	private ShareActionProvider mShareActionProvider;
	String url;
	int result;
	private TextToSpeech mSpeech;
	List<MenuItem> menus = new ArrayList<MenuItem>();
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    data = getIntent().getExtras().getString("desc");
	    url = "http://www.forbes.com/sites/lizryan/2014/03/17/can-an-anti-social-person-get-hired/";
	    System.out.println(data);
	    mSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
	    	
	        public void onInit(int status) {
if(status == TextToSpeech.SUCCESS)
	result=mSpeech.setLanguage(Locale.US);	
if(result==TextToSpeech.LANG_MISSING_DATA ||
result==TextToSpeech.LANG_NOT_SUPPORTED){
Log.e("error", "This Language is not supported");
}
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
			System.out.println(data);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	
	public Object sharejson(int menuid)
	{
		JSONObject obj = new JSONObject();
		try {
			System.out.println("goes into try of json");
			obj.put("text", data);
			obj.put("menuItems", new JSONObject().put("action", "SHARE"));
			//obj.put("menuItems", new JSONObject().put("payload", "https://developers.google.com/glass/develop/mirror/menu-items"));
			System.out.println(obj);
			System.out.println(data);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public Object openwebjson(int menuid)
	{
		JSONObject obj = new JSONObject();
		try {
			System.out.println("goes into try of json");
			obj.put("text", data);
			obj.put("menuItems", new JSONObject().put("action", "OPEN_URI"));
			obj.put("menuItems", new JSONObject().put("payload", "https://developers.google.com/glass/develop/mirror/menu-items"));
			System.out.println(obj);
			System.out.println(data);
			
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
            	System.out.println(item.getItemId());
            	System.out.println(data);
            	System.out.println(TextToSpeech.ERROR);
            	
            	mSpeech.speak("Hello, this is a sample data", TextToSpeech.QUEUE_FLUSH, null);
            	
            	
            	//mSpeech.speak("Hello how are you", TextToSpeech.QUEUE_FLUSH, null);
            	
                
                
                return true;
            case R.id.share:
            	
               	/*mShareActionProvider = (ShareActionProvider) item.getActionProvider();
            	//sharejson(item.getItemId());
            	Intent in=new Intent(Intent.ACTION_SEND);
            	
            	in.setType("image/*");
            	in.putExtra(Intent.EXTRA_TEXT, "This is the text to be shared");
            	//mShareActionProvider.setShareIntent(in);
            	startActivity(Intent.createChooser(in, "Share via"));
            	*/
            	
            	
            	String message = "Text I wan't to share.";
            	Intent share = new Intent(Intent.ACTION_SEND);
            	share.setType("text/plain");
            			share.putExtra(Intent.EXTRA_TEXT, message);

            			startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
            	
            	
                return true;
            	
            	
            	
            case R.id.openweb:
            	openwebjson(item.getItemId());
            	Intent i = new Intent(Intent.ACTION_VIEW);
            	i.setData(Uri.parse(url));
            	startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
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
