package com.bliss.android.helloworld.main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.google.api.services.mirror.model.TimelineItem;
import com.google.api.services.mirror.model.MenuItem;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class SecondScreen1 extends Activity {
	String url;
	/*
	private static final String TAG = CardImageLoader.class.getSimpleName(); 
    private static final boolean DEBUG = false;

    private static final int MAX_IMAGE_LOAD_RETRIES = 3;
    private static Map<String, Integer> mLoadFailures = new ConcurrentHashMap<String, Integer>();
*/
	CardScrollView csvCardsView;
	int size;
	// Intent intent = new Intent(new GestureDetector.BaseListener(){},
	// Class<MenuActivity> ma);
	private GestureDetector mGestureDetector;
	ArrayList<String> s = null;
	Bundle intentfromMain;
	String getSource;
	// String subscribesource;
	String sourcename;
	private ArrayList<Card> newscard = new ArrayList<Card>();
	private ArrayList<String> newstext = new ArrayList<String>();
	private ArrayList<String> newslink = new ArrayList<String>();
	private ArrayList<String> newsimage = new ArrayList<String>();
	private ArrayList<String> newsdesc = new ArrayList<String>();
	private ArrayList<String> messageText = new ArrayList<String>();
	Card newcard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
   // ImageLoader.getInstance().init(config);
		setContentView(R.layout.activity_second);
		System.out.println("Hello hello");

		intentfromMain = getIntent().getExtras();
		getSource = intentfromMain.getString("sourcename");
		System.out.println(getSource);

		System.out.println(getSource);

		// android.os.Debug.waitForDebugger();
		PostTask post = new PostTask();
		System.out.println("post task executed");
		ArrayList<String> s = null;
		System.out.println("try 1 executed");
		if (getSource.equals("Forbes")) {
			post.execute("http://www.forbes.com/most-popular/feed/");
			url = "http://www.forbes.com/most-popular/feed/";
			sourcename = "pcworld";
		} else if (getSource.equals("Tech Crunch")) {
			post.execute("http://feeds.feedburner.com/TechCrunch/");
			url = "http://feeds.feedburner.com/TechCrunch/";
			sourcename = "techcrunch";

		}

		else if (getSource.equals("NPR")) {
			post.execute("http://www.npr.org/rss/rss.php");
			url = "http://www.npr.org/rss/rss.php";
			sourcename = "NPR";

		}

		else if (getSource.equals("BBC")) {
			post.execute("http://feeds.bbci.co.uk/news/rss.xml");
			url = "http://feeds.bbci.co.uk/news/rss.xml";
			sourcename = "BBC";

		}

		// }

	}

	class PostTask extends AsyncTask<String, Integer, ArrayList<String>> {

		protected void onPreExecute() {
			super.onPreExecute();
			System.out.println("Hello hello 11");
		}

		protected ArrayList<String> doInBackground(String... params) {
			Document doc = null;
			try {
				System.out.println("Hello hello 1");
				URL url_link = new URL(url);
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				doc = db.parse(new InputSource(url_link.openStream()));

				doc.getDocumentElement().normalize();
				NodeList nodeList = doc.getElementsByTagName("item");
				System.out.println(nodeList);

				for (int i = 0; i < 3; i++) {
					Node node = nodeList.item(i);

					Element fstElmnt = (Element) node;
					NodeList nameList = ((org.w3c.dom.Element) fstElmnt)
							.getElementsByTagName("title");
					Element nameElement = (Element) nameList.item(0);
					nameList = ((Node) nameElement).getChildNodes();

					newstext.add(nameList.item(0).getNodeValue());
					System.out.println(newstext.get(i));

					NodeList LinkList = ((org.w3c.dom.Element) fstElmnt)
							.getElementsByTagName("link");
					Element LinkElement = (Element) LinkList.item(0);
					LinkList = ((Node) LinkElement).getChildNodes();

					newslink.add(LinkList.item(0).getNodeValue());
					System.out.println(newslink.get(i));
					
					NodeList DescList = ((org.w3c.dom.Element) fstElmnt)
							.getElementsByTagName("description");
					Element DescElement = (Element) DescList.item(0);
					DescList = ((Node) DescElement).getChildNodes();

					newsdesc.add(DescList.item(0).getNodeValue());
					System.out.println(newsdesc.get(i));
					
					/*NodeList imageList = ((org.w3c.dom.Element) fstElmnt)
							.getElementsByTagName("url");
					Element imageElement = (Element) imageList.item(0);
					imageList = ((Node) imageElement).getChildNodes();

					newsimage.add(imageList.item(0).getNodeValue());
					System.out.println(newsimage.get(i));

*/
				}
			} catch (Exception e) {
				System.out.println("XML Pasing Excpetion = " + e);
			}
			return newstext;
		}

		protected void onPostExecute(ArrayList<String> arr) {
			super.onPostExecute(arr);
			finalview(arr);

		}
	}

	private void finalview(ArrayList<String> ss) {
		for (int i = 0; i < ss.size(); i++) {
			System.out.println(ss.get(i));
			Card newcard = new Card(this);
			newcard.setText(ss.get(i));
			//Uri myUri = Uri.parse(newsimage.get(i));
			//newcard.addImage(myUri);
			//newcard.loadUrl("file:///android_res/html/try234.html");
			newscard.add(newcard);
			messageText.add(ss.get(i));
			mGestureDetector = createGestureDetector(this);

		}
		csvCardsView = new CardScrollView(this);
		csaAdapter cvAdapter = new csaAdapter();
		csvCardsView.setAdapter(cvAdapter);
		csvCardsView.activate();
		setContentView(csvCardsView);

	}

	private class csaAdapter extends CardScrollAdapter {
		public int findIdPosition(Object id) {
			return -1;
		}

		public int findItemPosition(Object item) {
			return newscard.indexOf(item);
		}

		@Override
		public int getCount() {
			return newscard.size();
		}

		@Override
		public Object getItem(int position) {
			return newscard.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return newscard.get(position).getView(convertView, parent);
		}

		@Override
		public int getPosition(Object item) {
			// TODO Auto-generated method stub
			return newscard.indexOf(item);
		}
	}

	// Detect gesture - touchpad

	private GestureDetector createGestureDetector(Context context) {
		GestureDetector gestureDetector = new GestureDetector(context);
		// Create a base listener for generic gestures
		gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
			@Override
			public boolean onGesture(Gesture gesture) {
				System.out.println(gesture.name());
				if (gesture == Gesture.TAP) {
					// intent(null, null);
					System.out.println("This is TAP");

					return true;
				}
				if (gesture == Gesture.LONG_PRESS) {
					Intent intent = new Intent(SecondScreen1.this,
							MenuActivity.class);
					intent.putExtra("message", messageText.get(csvCardsView
							.getSelectedItemPosition()));
					intent.putExtra("link", newslink.get(csvCardsView
							.getSelectedItemPosition()));
					intent.putExtra("desc", newsdesc.get(csvCardsView
							.getSelectedItemPosition()));
					
					startActivity(intent);
					
					return true;
				} else if (gesture == Gesture.SWIPE_DOWN) {
					// Intent intent = new Intent(this, MenuActivity.class);
					// startActivity(intent);
					finish();
					return true;
				} else if (gesture == Gesture.LONG_PRESS) {
					// Intent intent = new Intent(this, MenuActivity.class);
					// startActivity(intent);
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
			public boolean onScroll(float displacement, float delta,
					float velocity) {
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
	
	/*
	public class CardImageLoader {

	    
	    public void init(Context context) {
	        File cacheDir = StorageUtils.getCacheDirectory(context);
	        DisplayImageOptions options = new DisplayImageOptions.Builder()
	                .cacheOnDisc(true)
	                .build();
	        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
	                .discCache(new UnlimitedDiscCache(cacheDir))
	                .defaultDisplayImageOptions(options)
	                .build();
	        ImageLoader.getInstance().init(config);
	    }

	    public void loadCardImage(final Card card, final String imageUri, final CardScrollView cardScrollView) {
	        card.setImageLayout(Card.ImageLayout.FULL);
	        int failures = mLoadFailures.containsKey(imageUri) ? mLoadFailures.get(imageUri) : 0;
	        if (failures > MAX_IMAGE_LOAD_RETRIES) {
	            if (DEBUG) Log.i(TAG, "Exceeded max retries on imageUri=" + imageUri);
	            return;
	        }
	        File file = ImageLoader.getInstance().getDiscCache().get(imageUri);
	        if (file != null && file.exists() && file.length() > 0) {
	            Uri uri = Uri.fromFile(file);
	            card.addImage(uri);
	        }
	        else {
	            ImageLoader.getInstance().loadImage(imageUri, new ImageLoadingListener() {
	                @Override
	                public void onLoadingStarted(String imageUri, View view) {
	                    if (DEBUG) Log.i(TAG, "onLoadingStarted");
	                }

	                @Override
	                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
	                    if (DEBUG) Log.i(TAG, "onLoadingFailed");
	                    int failures = mLoadFailures.containsKey(imageUri) ? mLoadFailures.get(imageUri) : 0;
	                    mLoadFailures.put(imageUri, ++failures);
	                }

	                @Override
	                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
	                    File file = ImageLoader.getInstance().getDiscCache().get(imageUri);
	                    if (DEBUG) Log.i(TAG, "onLoadingComplete uri=" + imageUri + " file=" + file
	                            + " len=" + (file == null ? 0 : file.length()));
	                    if (file != null && file.exists() && file.length() > 0) {
	                        if (DEBUG) Log.i(TAG, "onLoadingComplete scheduling update of scroll views");
	                        if (cardScrollView != null)
	                            cardScrollView.updateViews(true);
	                    }
	                }

	                @Override
	                public void onLoadingCancelled(String imageUri, View view) {
	                    if (DEBUG) Log.i(TAG, "onLoadingCancelled");
	                }
	            });
	        }
	    }
	}
*/
	public void onDestroy() {
		// sourceCard.shutdown();
		super.onDestroy();

	}
}
