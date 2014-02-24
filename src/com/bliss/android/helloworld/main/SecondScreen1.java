package com.bliss.android.helloworld.main;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.w3c.dom.Element;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SecondScreen1 extends Activity {
	Intent intent = new Intent(new GestureDetector.BaseListener(){}, Class<MenuActivity> ma);
	private GestureDetector mGestureDetector;
	ArrayList<String> s = null;
	private ArrayList<Card> newscard = new ArrayList<Card>();
    private ArrayList<String> newstext = new ArrayList<String>();
	//private ArrayList<Card> headlines = new ArrayList<Card>();
	Card newcard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mGestureDetector = createGestureDetector(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		System.out.println("Hello hello");
		//headlines = new ArrayList();
		//ArrayList<String> links = new ArrayList<String>();
		//android.os.Debug.waitForDebugger();
		PostTask post=new PostTask();
		System.out.println("post task executed");
		ArrayList<String> s = null;
		System.out.println("try 1 executed");
		post.execute("http://feeds.pcworld.com/pcworld/latestnews");
		//System.out.println(s.get(2));
		
		CardScrollView csvCardsView = new CardScrollView(this);
        csaAdapter cvAdapter = new csaAdapter();
        csvCardsView.setAdapter(cvAdapter);
        csvCardsView.activate();
        setContentView(csvCardsView);
	}
		class PostTask extends AsyncTask<String, Integer, ArrayList<String>> {
			
			protected void onPreExecute() {
			      super.onPreExecute();
			      System.out.println("Hello hello 11");   
			   }
			
			   protected ArrayList<String> doInBackground(String... params) {
				   Document doc = null;
				   try{
					   System.out.println("Hello hello 1");   
	            URL url_link = new URL("http://feeds.pcworld.com/pcworld/latestnews");
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            doc = db.parse(new InputSource(url_link.openStream()));
				
	            doc.getDocumentElement().normalize();
	            NodeList nodeList = doc.getElementsByTagName("item");
	            System.out.println(nodeList);

	            for (int i = 0; i < 3; i++) {
	                Node node = nodeList.item(i);

	                Element fstElmnt = (Element)node;
	                NodeList nameList = ((org.w3c.dom.Element) fstElmnt).getElementsByTagName("title");
	                Element nameElement = (Element)nameList.item(0);
	                nameList = ((Node) nameElement).getChildNodes();

	                newstext.add(nameList.item(0).getNodeValue());
	                System.out.println(newstext.get(i));
	            
	            }
				   }
	            catch (Exception e) {
	    	            System.out.println("XML Pasing Excpetion = " + e);
	    	        }
	                return newstext;
	            }
	            
	            protected void onPostExecute(ArrayList<String> arr){       		
	            	super.onPostExecute(arr);
	            	finalview(arr);
	     
	            }
			   }
			   
		private void finalview(ArrayList<String> ss)
		{
			for(int i=0;i<ss.size();i++)
			{
				System.out.println(ss.get(i));
			Card newcard= new Card(this);
			newcard.setText(ss.get(i));
			 newscard.add(newcard);
			 

			}
			
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
            return newscard.indexOf(item);
        }

        @Override
        public int getCount()
        {
            return newscard.size();
        }

        @Override
        public Object getItem(int position)
        {
            return newscard.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return newscard.get(position).toView();
        }
    }
        

	/*public InputStream getInputStream(URL url) {
		   try {
		       return url.openConnection().getInputStream();
		   } catch (IOException e) {
		       return null;
		     }
		}*/
	
	public boolean onKeyDown(int keycode1, KeyEvent event) {
        if (keycode1 == KeyEvent.KEYCODE_DPAD_CENTER) {
        	Log.e("Msg","Second Screen");
        	Intent intent = new Intent(this, MenuActivity.class);
        	startActivity(intent);
            return true;
            
        }
        
        else
        	return false;
    }
   // Detect gesture - touchpad
	
	private GestureDetector createGestureDetector(Context context) {
	    GestureDetector gestureDetector = new GestureDetector(context);
	        //Create a base listener for generic gestures
	        gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
	            @Override
	            public boolean onGesture(Gesture gesture) {
	                if (gesture == Gesture.TAP) {
	                	Intent intent = new Intent(this, MenuActivity.class);
	                	startActivity(intent);
	                    return true;
	                } else if (gesture == Gesture.TWO_TAP) {
	                	Intent intent = new Intent(this, MenuActivity.class);
	                	startActivity(intent);
	                    return true;
	                } else if (gesture == Gesture.SWIPE_RIGHT) {
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
	}
	
    
}
