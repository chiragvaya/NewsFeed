package com.bliss.android.helloworld.main;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.mirror.model.MenuItem;

public class MenuActivity1 {
    List<MenuItem> menus = new ArrayList<MenuItem>();
 
    public static final String READ_ALOUD = "READ_ALOUD";
    public static final String SHARE = "SHARE";
    public static final String NAVIGATE = "NAVIGATE";
    public static final String OPEN_URI = "OPEN_URI";

    public MenuActivity1() {
    }
 
    public List<MenuItem> getMenus() {
        return menus;
    }
 
    public boolean hasContent(){
        return (menus.size() > 0);
    }
 
    /**
     * READ_ALOUD - Read the timeline item's speakableText aloud; if this field is
     * not set, read the text field; if none of those fields are set, this menu item
     * is ignored.
     *
     * @param payload - the text to read aloud
     */
    public MenuActivity1 addReadAloudAction(String payload) {
        menus.add(new MenuItem().setAction(READ_ALOUD).setPayload(payload));
        return this;
    }
 
 
 
    /**
     * SHARE - Share the timeline item with the available contacts.
     */
    public MenuActivity1 addShareAction() {
        menus.add(new MenuItem().setAction(SHARE));
        return this;
    }
 
 
    
    public MenuActivity1 addNavigateAction() {
        menus.add(new MenuItem().setAction(NAVIGATE));
        return this;
    }
 
    /**
     * TOGGLE_PINNED - Toggle the isPinned state of the timeline item.
     */
 
    public MenuActivity1 addOpenUriAction(String payload) {
        menus.add(new MenuItem().setAction(OPEN_URI).setPayload(payload));
        return this;
    }
 
 
}