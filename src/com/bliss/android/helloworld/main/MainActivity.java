package com.bliss.android.helloworld.main;



import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.google.api.services.mirror.model.TimelineItem;

public class MainActivity extends Activity {
	CardScrollView csvCardsView;
	private GestureDetector mGestureDetector;
	String identifysource;
	int cardindex;
	private static ArrayList<Card> sourceCard = new ArrayList<Card>();
	private ArrayList<String> sourceText = new ArrayList<String>(Arrays.asList("PCWorld", "TechCrunch"));
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      
      // Card addsources = new Card(this);
     //  addsources.setText("Add Sources");
       
       
       System.out.println(this);
      
       // testing
        //LiveCard liveCard = null; 
        //RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.activity_main);
       // liveCard.setViews(views);
        
       // LiveCard liveCard; 
        //Intent intent = new Intent(this, SecondActivity.class);
       // liveCard.setAction(PendingIntent.getActivity(this, 0, intent, 0));
       createcards();
       csvCardsView = new CardScrollView(this);
       csaAdapter cvAdapter = new csaAdapter();
       csvCardsView.setAdapter(cvAdapter);
       csvCardsView.activate();
       setContentView(csvCardsView);
       //cvAdapter.getView(position, csvCardsView, );
      cardindex=csvCardsView.getSelectedItemPosition();
      mGestureDetector = createGestureDetector(this);
      
       //cardindex=cvAdapter.findItemPosition(sourceCard);
        System.out.println("after cardscroll"+cardindex);
        
    }
	
	private void createcards()
	{
	 for(int i=0;i<sourceText.size();i++)
     {
  	   System.out.println("whats up");
      Card card = new Card(this);
      identifysource=sourceText.get(i);
      card.setText(identifysource);
      sourceCard.add(card);
      //card.setFootnote("blisstering");
      //setContentView(card.toView());
 
     }
	 Card addsourcecard=new Card(this);
	 addsourcecard.setText("Add Sources");
	 sourceCard.add(addsourcecard);
	 
	}
	/*public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_DPAD_CENTER) {
        	Log.e("Msg","What you have to print");
        	Intent intent = new Intent(this, SecondScreen1.class);
        	intent.putExtra("sourcename",identifysource);
        	startActivity(intent);
            return true;
        }
        
        else
        	return false;
    }*/

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
	
	private static class csaAdapter extends CardScrollAdapter
    {

        public int findIdPosition(Object id)
        {
            return -1;
        }

       
        public int findItemPosition(Object item)
        {
            return sourceCard.indexOf(item);
        }

        public int getCount()
        {
            return sourceCard.size();
        }

     
        public Object getItem(int position)
        {
            return sourceCard.get(position);
        }

      
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return sourceCard.get(position).toView();
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
	                	System.out.println("This is ");
	                	
	                    return true;
	                } 
	                if (gesture == Gesture.LONG_PRESS) {
	                	//Intent intent = new Intent(SecondScreen1.this, MenuActivity1.class);
	                	//intent.putExtra("message", messageText);
	                	//startActivity(intent);
	                	Intent intent = new Intent(MainActivity.this, SecondScreen1.class);
	                	csvCardsView.getSelectedItemPosition();
	                	intent.putExtra("sourcename",csvCardsView.getSelectedItemPosition());
	                	intent.putExtra("size",sourceText.size() );
	                	startActivity(intent);
	                	
	                	System.out.println("This is long press of main");
	                	
	                    return true;
	                }
	                else if (gesture == Gesture.TWO_TAP) {
	                	//Intent intent = new Intent(this, MenuActivity.class);
	                	//startActivity(intent);
	                    return true;
	                } 
	                else if (gesture == Gesture.LONG_PRESS) {
	                	//Intent intent = new Intent(this, MenuActivity.class);
	                	//startActivity(intent);
	                    return true;	
	                }
	                else if (gesture == Gesture.SWIPE_RIGHT) {
	                    // do something on right (forward) swipe
	                    return true;
	                } else if (gesture == Gesture.SWIPE_LEFT) {
	                    // do something on left (backwards) swipe
	                    return true;
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
	        sourceCard.clear();
	        sourceText.clear();
	    }
}
   
