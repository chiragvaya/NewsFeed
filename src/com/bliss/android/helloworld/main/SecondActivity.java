package com.bliss.android.helloworld.main;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends Activity
{
    private ArrayList<Card> newscard = new ArrayList<Card>();
    private ArrayList<String> newstext = new ArrayList<String>(Arrays.asList("Hello", "World"));

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < newstext.size(); i++)
        {
            Card newCard = new Card(this);
            //newCard.setFullScreenImages(true);
            newCard.setText(newstext.get(i));
            newscard.add(newCard);
        }

        CardScrollView csvCardsView = new CardScrollView(this);
        csaAdapter cvAdapter = new csaAdapter();
        csvCardsView.setAdapter(cvAdapter);
        csvCardsView.activate();
        setContentView(csvCardsView);
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
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
          if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
              openOptionsMenu();
              return true;
          }
          return false;
    }
}