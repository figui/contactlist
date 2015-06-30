package com.solstice.codechallenge.contactlist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;

import com.solstice.codechallenge.contactlist.entities.User;

import java.util.List;


public class MainActivity extends Activity {

    private final int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;
    private List<User> contactList;

    LruCache mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return bitmap.getByteCount() / 1024;
        }
    };

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return (Bitmap) mMemoryCache.get(key);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityFragment f = new MainActivityFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment, f).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<User> getContactList() {
        return contactList;
    }

    public void setContactList(List<User> contactList) {
        this.contactList = contactList;
    }
}
