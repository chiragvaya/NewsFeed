package com.bliss.android.helloworld.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	String[] arraylistToArray = new String[4];
	String selectedcard;
	private ArrayList<String> subscribesource = new ArrayList<String>();
    private ArrayList<Card> mlcCards = new ArrayList<Card>();
    private ArrayList<String> mlsText = new ArrayList<String>(Arrays.asList("PC World", "Tech Crunch","NPR", "BBC"));
    public static SharedPreferences sPrefs ;
    //= this.getSharedPreferences("com.bliss.android.helloworld.main", Context.MODE_PRIVATE);
    String source;
    int sPrefsSize=0;
    Context context;
    
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

    
    
    /*
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	 if (requestCode == 1)
    	 {
    		 if(resultCode==RESULT_OK)
    		 {
    		 source = data.getStringExtra("subscribesource");
    		 arraylistToArray = MainActivity.subscribesource.toArray(arraylistToArray);
    		
    		 if(MainActivity.subscribesource.contains(source)==false)
    		 {
    			 MainActivity.subscribesource.add(source);
    			 
    			
    		 }
    		 else
    		 {
    			 MainActivity.subscribesource.remove(source);
    			
    		 }
    System.out.println("you are at addsourcesactivity" +MainActivity.subscribesource);	 
    		 }
    		 if (resultCode == RESULT_CANCELED) {    
        //Write your code if there's no result
    }
    		 Intent i = new Intent();
 	    	i.putExtra("subscribesource",MainActivity.subscribesource);
 	    	setResult(RESULT_OK, i); 
    	 }
    	 mGestureDetector = createGestureDetector(this);

    
    }*/
    
    
    
        
    
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
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        
        inflater.inflate(R.menu.sourcesmenu, menu);
    
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
    	/*if(addedSources.contains(mlsText.get(getSourceindex))==false)
    	{
    	addedSources.add(mlsText.get(getSourceindex));
    	}
    	else
    	{
    	addedSources.remove(mlsText.get(getSourceindex));
    	}*/
    	System.out.println("onOptionsItemSelected");
    	sPrefs= getSharedPreferences(MainActivity.PREF_NAME,Activity.MODE_PRIVATE);
    	SharedPreferences.Editor sEdit=sPrefs.edit();
    	selectedcard=mlsText.get(csvCardsView.getSelectedItemPosition());
    	System.out.println(selectedcard);
    	if(sPrefs.contains(selectedcard))
    	{
    		System.out.println("Inside if of sfrefs");
    	sEdit.remove(selectedcard);
    	sEdit.commit();
    	sEdit.putInt("size",sPrefs.getAll().size());
    	sEdit.commit();
    	}
    	else
    	{
    		System.out.println("Inside else of sfrefs");
    		sEdit.putString(selectedcard, selectedcard);
    		sEdit.commit();
    		sEdit.putInt("size",sPrefs.getAll().size());
    		sEdit.commit();
    		
    	}
    	System.out.println(sPrefs.toString());
    	int size = sPrefs.getInt("size", 0);
    	System.out.println("size"+size);
    	for(int j=0;j<size;j++)
        {
                  subscribesource.add(sPrefs.getString(mlsText.get(j),null));
        }
    	System.out.println(subscribesource);
        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the activity.
    	System.out.println("Menu closing.. intent to addsourcesactivity");
    	
        
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
	                	
	                	 openOptionsMenu();
	                	
	                	
	                    return true;
	                }
	                if(gesture == Gesture.SWIPE_DOWN)
	                {
	                	System.out.println("swipe down");
	                	
	                	
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
	    	
		        super.onDestroy();
		       
		       
		    }
	    
	    
    
    
}
