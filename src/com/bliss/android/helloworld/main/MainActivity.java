package com.bliss.android.helloworld.main;



import java.util.ArrayList;
import java.util.Arrays;

import com.google.android.glass.app.Card;
import com.google.android.glass.timeline.LiveCard;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Card card = new Card(this);
        card.setText("Hello world!");
        card.setFootnote("blisstering");
        
        
        setContentView(card.toView());
       // testing
        //LiveCard liveCard = null; 
        //RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.activity_main);
       // liveCard.setViews(views);
        
       // LiveCard liveCard; 
        //Intent intent = new Intent(this, SecondActivity.class);
       // liveCard.setAction(PendingIntent.getActivity(this, 0, intent, 0));
        
        
    }
	public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_DPAD_CENTER) {
        	Log.e("Msg","What you have to print");
        	Intent intent = new Intent(this, SecondScreen1.class);
        	startActivity(intent);
            return true;
        }
        
        else
        	return false;
    }

    /*
   @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.second, menu);
        return true;
    }

   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection. Menu items typically start another
        // activity, start a service, or broadcast another intent.
        /*switch (item.getItemId()) {
            case R.id.;
                startActivity(new Intent(this, SecondActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    	System.out.println("Success");
		return true;
		
    }*/
}
   
