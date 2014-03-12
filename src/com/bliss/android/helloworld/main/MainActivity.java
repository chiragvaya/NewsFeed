package com.bliss.android.helloworld.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

public class MainActivity extends Activity {
	CardScrollView csvCardsView;
	private GestureDetector mGestureDetector;
	String identifysource;
	String selectedcard;
	int cardindex;

	Bundle intentfromaddSources;
	Boolean sourceexists;
	Boolean checkadd;
	private static ArrayList<Card> sourceCard;

	public static ArrayList<String> subscribesource;
	public static ArrayList<String> subscribesource1;
	public static String PREF_NAME = "pref";
	private ArrayList<String> mlsText = new ArrayList<String>(Arrays.asList(
			"PC World", "Tech Crunch", "NPR", "BBC", "Add Sources"));
	Context context;
	private SharedPreferences sPrefs;
	private csaAdapter cvAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sourceCard = new ArrayList<Card>();
		subscribesource = new ArrayList<String>();
		subscribesource1 = new ArrayList<String>();
		System.out.println("1st line of main");
		sPrefs = getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor sEdit = sPrefs.edit();
		// sEdit.clear();
		// sEdit.commit();

		Map<String, ?> prefsMap = sPrefs.getAll();

		for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
			System.out.print(entry.getKey() + ": "
					+ entry.getValue().toString());
		}

		if (sPrefs.contains("Add Sources")) {
			System.out.println("if of add add sources");
		} else {

			sEdit.putString("Add Sources", "1");
			System.out.println("Add Sources added");
		}
		if (sPrefs.contains("PC World") == false) {
			sEdit.putString("PC World", "0");
			System.out.println("PC World added");
		}
		if (sPrefs.contains("Tech Crunch") == false) {
			sEdit.putString("Tech Crunch", "0");
			System.out.println("Tech Crunch added");
		}
		if (sPrefs.contains("NPR") == false) {
			sEdit.putString("NPR", "0");
			System.out.println("NPR added");
		}
		if (sPrefs.contains("BBC") == false) {
			sEdit.putString("BBC", "0");
			System.out.println("BBC added");
		}

		sEdit.commit();

		
		
		
		// sPrefs.registerOnSharedPreferenceChangeListener(prefListener);

		// System.out.println(checkadd);
		// System.out.println("Contents of array:"+sourceText);

		/*
		 * Card addsourcecard=new Card(this);
		 * addsourcecard.setText(sourceText.get(subscribesource.size()-1));
		 * sourceCard.add(addsourcecard);
		 * 
		 * 
		 * checkadd=subscribesource.contains("add"); if(checkadd==false)
		 * if(sPrefs.contains("Add Sources")==false) {
		 * sEdit.putString("Add Sources","Add Sources");
		 * //subscribesource.add("Add Sources"); Card addsourcecard=new
		 * Card(this); addsourcecard.setText("Add Sources");
		 * sourceCard.add(addsourcecard); } mGestureDetector =
		 * createGestureDetector(this); //sourceCard.add(addsourcecard);
		 * System.out.println(subscribesource);
		 * 
		 * if (!(getIntent().getExtras().getStringArrayList("subscribesource")==
		 * null)) {
		 * 
		 * }
		 */

		// System.out.println("Contents of array after add:"+sourceText);

		/*
		 * sourceexists = sourceText.contains(subscribesource);
		 * 
		 * if(sourceexists.equals(true)) sourceText.remove(subscribesource);
		 * else sourceText.addFirst(subscribesource);
		 */
		// addsources = new Card(this);
		// addsources.setText("Add Sources");

		System.out.println(this);

		// testing
		// LiveCard liveCard = null;
		// RemoteViews views = new RemoteViews(this.getPackageName(),
		// R.layout.activity_main);
		// liveCard.setViews(views);

		// LiveCard liveCard;
		// Intent intent = new Intent(this, SecondActivity.class);
		// liveCard.setAction(PendingIntent.getActivity(this, 0, intent, 0));

		
//		csvCardsView.setAdapter(cvAdapter);
//		csvCardsView.activate();
//		setContentView(csvCardsView);
		// cvAdapter.getView(position, csvCardsView, );
		
		mGestureDetector = createGestureDetector(this);

		// cardindex=cvAdapter.findItemPosition(sourceCard);
		System.out.println("after cardscroll" + cardindex);

	}

	public void onResume() {

		super.onResume();
		csvCardsView = new CardScrollView(this);
		cvAdapter = new csaAdapter();
		sourceCard.clear();
		subscribesource1.clear();
		subscribesource.clear();
		for (int j = 0; j < 5; j++) {
			if (!subscribesource.contains(sPrefs.getString(mlsText.get(j), null)))
				subscribesource1.add(sPrefs.getString(mlsText.get(j), null));
		}
		System.out.println("this is subscribesource1" + subscribesource1);
		System.out.println("after adding" + subscribesource);
		for (int i = 0; i < 5; i++) {
			System.out.println(subscribesource1.get(i));
			if (subscribesource1.get(i).equals("1")) {
				System.out.println(mlsText.get(i));
				subscribesource.add(mlsText.get(i));
			}
		}
		System.out.println(subscribesource);
		createcards();
		csvCardsView.setAdapter(cvAdapter);
		csvCardsView.activate();
		setContentView(csvCardsView);
	}

	/*
	 * protected void onActivityResult(int requestCode, int resultCode, Intent
	 * data) { if(requestCode==2) { if(resultCode == RESULT_OK){
	 * 
	 * 
	 * /*Card addsourcecard=new Card(this); addsourcecard.setText("add");
	 * sourceCard.add(addsourcecard); ArrayHelper pref = new
	 * ArrayHelper(context);
	 * 
	 * 
	 * MainActivity.subscribesource=data.getStringArrayListExtra("subscribesource"
	 * );
	 * 
	 * pref.saveArray(, subscribesource);
	 * 
	 * pref.sEdit.putInt("size",subscribesource.size()); sEdit.commit();
	 * System.out.println("sources in onactivityresult"+subscribesource);
	 * //saveArray(null, subscribesource, null);
	 * 
	 * 
	 * 
	 * if (resultCode == RESULT_CANCELED) { //Write your code if there's no
	 * result } } } }
	 */

	private void createcards() {
		for (int i = 0; i < subscribesource.size(); i++) {
			System.out.println("create cards called");

			Card card = new Card(this);

			identifysource = subscribesource.get(i);
			System.out.println("cardtext"+identifysource);
			// identifysource=subscribesource.get(i);
			card.setText(identifysource);
			sourceCard.add(card);
			// card.setFootnote("blisstering");
			// setContentView(card.toView());

		}
	}

	/*
	 * public boolean onKeyDown(int keycode, KeyEvent event) { if (keycode ==
	 * KeyEvent.KEYCODE_DPAD_CENTER) { Log.e("Msg","What you have to print");
	 * Intent intent = new Intent(this, SecondScreen1.class);
	 * intent.putExtra("sourcename",identifysource); startActivity(intent);
	 * return true; }
	 * 
	 * else return false; }
	 */

	/*
	 * @Override public void onAttachedToWindow() { super.onAttachedToWindow();
	 * 
	 * }
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * super.onCreateOptionsMenu(menu); MenuInflater inflater =
	 * getMenuInflater(); inflater.inflate(R.menu.second, menu); return true; }
	 * 
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * item selection. Menu items typically start another // activity, start a
	 * service, or broadcast another intent. /*switch (item.getItemId()) { case
	 * R.id.; startActivity(new Intent(this, SecondActivity.class)); return
	 * true; default: return super.onOptionsItemSelected(item); }
	 * System.out.println("Success"); return true;
	 * 
	 * }
	 */

	private static class csaAdapter extends CardScrollAdapter {

		public int findIdPosition(Object id) {
			return -1;
		}

		public int findItemPosition(Object item) {
			return sourceCard.indexOf(item);
		}

		public int getCount() {
			return sourceCard.size();
		}

		public Object getItem(int position) {
			return sourceCard.get(position);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			return sourceCard.get(position).toView();
		}
	}

	private GestureDetector createGestureDetector(Context context) {
		GestureDetector gestureDetector = new GestureDetector(context);
		// Create a base listener for generic gestures
		gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
			@Override
			public boolean onGesture(Gesture gesture) {
				System.out.println(gesture.name());
				if (gesture == Gesture.TAP) {
					// intent(null, null);
					System.out.println("This is ");
					System.out.println(subscribesource);

					System.out.println("Inside tap");
					selectedcard = subscribesource.get(csvCardsView
							.getSelectedItemPosition());
					System.out.println(selectedcard);
					if (selectedcard.equals("Add Sources")) {
						System.out.println("Inside if of tap");
						Intent intentforadd = new Intent(MainActivity.this,
								addsourcesactivity.class);
						startActivity(intentforadd);
					} else {
						System.out.println("Inside else of tap");
						Intent intent = new Intent(MainActivity.this,
								SecondScreen1.class);
						intent.putExtra("sourcename", selectedcard);
						intent.putExtra("size", subscribesource.size());
						System.out.println(subscribesource.get(
								csvCardsView.getSelectedItemPosition())
								.toString());
						startActivity(intent);
					}

					return true;
				}
				if (gesture == Gesture.LONG_PRESS) {
					// Intent intent = new Intent(SecondScreen1.this,
					// MenuActivity1.class);
					// intent.putExtra("message", messageText);
					// startActivity(intent);

					csvCardsView.getSelectedItemPosition();
					System.out.println("Inside long press");
					System.out.println(subscribesource);
					selectedcard = subscribesource.get(csvCardsView
							.getSelectedItemPosition());
					System.out.println("in long press of main selected"
							+ selectedcard);
					if (selectedcard.equals("Add Sources")) {
						System.out.println("Inside if of long press");
						Intent intentforadd = new Intent(MainActivity.this,
								addsourcesactivity.class);
						startActivity(intentforadd);
					} else {

						System.out.println("Inside else of long press");
						Intent intent = new Intent(MainActivity.this,
								SecondScreen1.class);
						intent.putExtra("sourcename", selectedcard);
						// intent.putExtra("size",subscribesource.size());
						System.out.println(subscribesource.get(
								csvCardsView.getSelectedItemPosition())
								.toString());
						startActivity(intent);
					}
					System.out.println("This is long press of main");

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

	public void onDestroy() {
		// sourceCard.shutdown();
		super.onDestroy();
		// sourceCard.clear();

	}

}
