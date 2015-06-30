package com.solstice.codechallenge.contactlist.task;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.solstice.codechallenge.contactlist.DetailsFragment;
import com.solstice.codechallenge.contactlist.MainActivity;
import com.solstice.codechallenge.contactlist.R;
import com.solstice.codechallenge.contactlist.adapters.ContactsAdapter;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.services.ContactService;
import com.solstice.codechallenge.contactlist.services.impl.ContactServiceImpl;

import java.util.List;

/**
 * Created by snakepit on 27/06/2015.
 */
public class ContactTask extends AsyncTask<Void, Void, List<User>> {

    private final String TAG = ContactTask.class.getSimpleName();
    private ListView listView;
    private Context context;

    public ContactTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected List<User> doInBackground(Void... params) {
        List<User> result = null;
        try {
            ContactService service = new ContactServiceImpl();
            result = service.getContacts();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<User> users) {
        if(isCancelled()) {
            users = null;
        }

        if(users != null) {
            setContactList(users);
        }
    }

    public void setContactList(List<User> users) {
        listView.setAdapter(new ContactsAdapter(context, R.layout.fragment_main_list_item, users));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity activity = (MainActivity) context;
                User user = (User) parent.getItemAtPosition(position);
                DetailsFragment nextFrag = new DetailsFragment();

                Fragment oldFragment = activity.getFragmentManager().findFragmentById(R.id.fragment);
                Bundle args = new Bundle();
                args.putSerializable(activity.getString(R.string.details_url_key), user);
                nextFrag.setArguments(args);
                activity.getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(oldFragment.getTag())
                        .replace(R.id.fragment, nextFrag)
                        .commit();
            }
        });
    }
}
