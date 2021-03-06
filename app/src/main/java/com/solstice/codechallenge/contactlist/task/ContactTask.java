package com.solstice.codechallenge.contactlist.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.helpers.EventHelper;
import com.solstice.codechallenge.contactlist.services.ContactService;

import java.util.List;

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
    protected void onPreExecute() {
        super.onPreExecute();
        EventHelper.post(EventHelper.PRE_TASK_EXECUTE_EVENT);
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
            EventHelper.post(users);
            EventHelper.post(EventHelper.POST_TASK_EXECUTE_EVENT);
        }
    }
}
