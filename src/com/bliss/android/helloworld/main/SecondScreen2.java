package com.bliss.android.helloworld.main;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class SecondScreen2 extends Activity
{
     ArrayList<Card> newscard = new ArrayList<Card>();
    private ArrayList<String> newstext = new ArrayList<String>();
    ArrayList links = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        new PostTask().execute("http://feeds.pcworld.com/pcworld/latestnews");

       

        CardScrollView csvCardsView = new CardScrollView(this);
        csaAdapter cvAdapter = new csaAdapter();
        csvCardsView.setAdapter(cvAdapter);
        csvCardsView.activate();
        setContentView(csvCardsView);
        
        
    }
        
       
    
    class PostTask extends AsyncTask<String, Integer, String> {
		  
		 
		   protected void onPreExecute() {
		      super.onPreExecute();
		      
		   }
		   
		   
		   @Override
		   protected String doInBackground(String... params) {
		      String url=params[0];
		try{
		      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			    factory.setNamespaceAware(false);
			    XmlPullParser xpp = factory.newPullParser();
			 
			        // We will get the XML from an input stream
			    
			 
			        /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
			         * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
			         * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
			         * so we should skip the "<title>" tag which is a child of "<channel>" tag,
			         * and take in consideration only "<title>" tag which is a child of "<item>"
			         *
			         * In order to achieve this, we will make use of a boolean variable.
			         */
			    boolean insideItem = false;
			 
			        // Returns the type of current event: START_TAG, END_TAG, etc..
			    int eventType = xpp.getEventType();
			    while (eventType != XmlPullParser.END_DOCUMENT) {
			        if (eventType == XmlPullParser.START_TAG) {
			 
			            if (xpp.getName().equalsIgnoreCase("item")) {
			                insideItem = true;
			            } else if (xpp.getName().equalsIgnoreCase("title")) {
			                if (insideItem){
			                	//Card newCard = new Card(SecondScreen2.this);
			                	//newCard.setText(xpp.nextText());
			                    //newstext.add(newCard); //extract the headline
			                    //newstext.add(xpp.nextText());
			                }
			            } else if (xpp.getName().equalsIgnoreCase("link")) {
			                if (insideItem)
			                    links.add(xpp.nextText()); //extract the link of article
			            }
			        }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
			            insideItem=false;
			        }
			 
			        eventType = xpp.next(); //move to next element
			    }
		      
		   }
	
		   catch (MalformedURLException e) {
			    e.printStackTrace();
			} catch (XmlPullParserException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
		return url;
		   
	
}
	

    }

    class csaAdapter extends CardScrollAdapter
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
}
    
