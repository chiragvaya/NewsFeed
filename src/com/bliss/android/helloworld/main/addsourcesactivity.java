package com.bliss.android.helloworld.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.Arrays;

public class addsourcesactivity extends Activity
{
	private GestureDetector mGestureDetector;
	CardScrollView csvCardsView;
	Bundle sourcesadded;
    private ArrayList<Card> mlcCards = new ArrayList<Card>();
    private ArrayList<String> mlsText = new ArrayList<String>(Arrays.asList("PC World", "Tech Crunch","NPR", "BBC"));
    private ArrayList<String> subscribeSource = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        System.out.println("Hello hello 1");
        
        

        for (int i = 0; i < mlsText.size(); i++)
        {
            Card newCard = new Card(this);
            //newCard.setFullScreenImages(true);
            newCard.setText(mlsText.get(i));
            mlcCards.add(newCard);
            mGestureDetector = createGestureDetector(this);
        }
        
        
        csvCardsView = new CardScrollView(this);
        csaAdapter cvAdapter = new csaAdapter();
        csvCardsView.setAdapter(cvAdapter);
        csvCardsView.activate();
        setContentView(csvCardsView);
        
    }

    protected void onRestart()
    {
    	super.onRestart();
    
    	System.out.println("enters if of onRestart addsourcesactivity");
    sourcesadded = getIntent().getExtras();
    subscribeSource = sourcesadded.getStringArrayList("subscribesource");
    
    }
    
    private class csaAdapter extends CardScrollAdapter
    {
        @Override
        public int findIdPosition(Object id)
        {
            return -1;
        }

        @Override
        public int findItemPosition(Object item)
        {
            return mlcCards.indexOf(item);
        }

        @Override
        public int getCount()
        {
            return mlcCards.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mlcCards.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return mlcCards.get(position).toView();
        }
    }
    
    private GestureDetector createGestureDetector(Context context) {
	    GestureDetector gestureDetector = new GestureDetector(context);
	        //Create a base listener for generic gestures
	        gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
	            @Override
	            public boolean onGesture(Gesture gesture) {
	            	System.out.println(gesture.name());
	                if (gesture == Gesture.TAP) {
	                	//intent(null, null);
	                	System.out.println("This is TAP");
	                	
	                    return true;
	                } 
	                if (gesture == Gesture.LONG_PRESS) {
	                	
	                	Intent intent = new Intent(addsourcesactivity.this, addSourcesMenu.class);
	                	intent.putExtra("sourceindex", csvCardsView.getSelectedItemPosition());
	                	startActivity(intent);
	                	
	                	
	                    return true;
	                }
	                if(gesture == Gesture.SWIPE_DOWN)
	                {
	                	finish();
	                	}
	                	
	                
	                return false;
	            }

	        });
	        gestureDetector.setFingerListener(new GestureDetector.FingerListener() {
	            @Override
	            public void onFingerCountChanged(int previousCount, int currentCount) {
	              // do something on finger count changes
	            }
	        });
	        gestureDetector.setScrollListener(new GestureDetector.ScrollListener() {
	            @Override
	            public boolean onScroll(float displacement, float delta, float velocity) {
	                // do something on scrolling
	            return true;
	            }
	        });
	        return gestureDetector;
	    }

	    /*
	     * Send generic motion events to the gesture detector
	     */
	    @Override
	    public boolean onGenericMotionEvent(MotionEvent event) {
	        if (mGestureDetector != null) {
	            return mGestureDetector.onMotionEvent(event);
	        }
	        return false;
	    }
	    public void onDestroy() {
		       //sourceCard.shutdown();
	    	Intent i = new Intent(addsourcesactivity.this, MainActivity.class);
	    	i.putExtra("subscribesource",subscribeSource);
	    	startActivity(i);
		        super.onDestroy();
		       
		       
		    }
    
    
}
