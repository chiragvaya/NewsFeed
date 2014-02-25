package com.bliss.android.helloworld.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.api.services.mirror.model.MenuItem;
import com.google.api.services.mirror.Mirror;

public class MenuActivity extends Activity {
	List<MenuItem> menus = new ArrayList<MenuItem>();
	
	public Object json(int menuid)
	{
		JSONObject obj = new JSONObject();
		try {
			System.out.println("goes into try of json");
			obj.put("text", "Hello World");
			obj.put("menuItems", new JSONObject().put("action", "OPEN_URI"));
			obj.put("menuItems", new JSONObject().put("payload", "https://developers.google.com/glass/develop/mirror/menu-items"));
			System.out.println(obj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.read_aloud_menu_item:
            	System.out.println("goes itno read aloud case");
            	menus.add(new MenuItem().setAction("READ_ALOUD").setPayload(payload));
                //json(item.getItemId());
                System.out.println(item.getItemId());
                
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the activity.
        finish();
    }
}
