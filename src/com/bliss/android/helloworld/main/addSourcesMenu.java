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
        Intent i = new Intent(addSourcesMenu.this, SecondScreen1.class);
        i.putExtra("sourcename11",mlsText.get(getSourceindex) );
        addSourcesMenu.this.startActivity(i);
        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the activity.
        finish();
    }
    
}