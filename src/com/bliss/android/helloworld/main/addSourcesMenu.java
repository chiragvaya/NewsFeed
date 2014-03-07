package com.bliss.android.helloworld.main;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class addSourcesMenu extends Activity {
	private ArrayList<String> mlsText = new ArrayList<String>(Arrays.asList("PC World", "Tech Crunch","NPR", "BBC"));
	 private ArrayList<String> addedSources = new ArrayList<String>();
	int getSourceindex;
	Bundle intentfromaddsources;
protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	intentfromaddsources = getIntent().getExtras();
	getSourceindex = intentfromaddsources.getInt("sourceindex");
	System.out.println(getSourceindex);
	
	
}
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
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
    	if(addedSources.contains(mlsText.get(getSourceindex))==false)
    	{
    	addedSources.add(mlsText.get(getSourceindex));
    	}
    	else
    	{
    	addedSources.remove(mlsText.get(getSourceindex));
    	}
    	
        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the activity.
    	System.out.println("Menu closing.. intent to addsourcesactivity");
    	Intent i = new Intent(addSourcesMenu.this, addsourcesactivity.class);
        i.putExtra("subscribesource", addedSources );
        addSourcesMenu.this.startActivity(i);
        finish();
    }
    
}