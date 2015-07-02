package com.solstice.codechallenge.contactlist.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.solstice.codechallenge.contactlist.R;
import com.solstice.codechallenge.contactlist.adapters.ContactsAdapter;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.services.ContactService;

import java.util.List;

import butterknife.Bind;
import retrofit.RestAdapter;

/**
 * Created by snakepit on 27/06/2015.
 */
public class ContactTask extends AsyncTask<Void, Void, List<User>> {

    private final String TAG = ContactTask.class.getSimpleName();
    private RecyclerView listView;
    private Context context;

    public ContactTask(Context context, RecyclerView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected List<User> doInBackground(Void... params) {
        List<User> result = null;
        try {
            RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://solstice.applauncher.com");
            RestAdapter adapter = builder.build();
            ContactService service = adapter.create(ContactService.class);
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

    public void setContactList(final List<User> users) {
        listView.setAdapter(new ContactsAdapter(users, context));
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = listView.getChildAdapterPosition(v);

            }
        });
    }
}
