package com.solstice.codechallenge.contactlist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.events.PostTaskExecute;
import com.solstice.codechallenge.contactlist.events.PreTaskExecute;
import com.solstice.codechallenge.contactlist.helpers.EventHelper;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {

    private List<User> contactList;

    @Bind(R.id.marker_progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityFragment f = new MainActivityFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment, f).commit();
        ButterKnife.bind(this);
        EventHelper.register(this);
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

    @Subscribe
    public void showProgress(PreTaskExecute event) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void hideProgress(PostTaskExecute event) {
        progressBar.setVisibility(View.GONE);
    }
}
