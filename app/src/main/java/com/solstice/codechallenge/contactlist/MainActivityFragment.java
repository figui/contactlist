package com.solstice.codechallenge.contactlist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solstice.codechallenge.contactlist.adapters.ContactsAdapter;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.helpers.EventHelper;
import com.solstice.codechallenge.contactlist.task.ContactTask;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @Bind(R.id.listView)
    RecyclerView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        MainActivity a = (MainActivity) getActivity();
        ContactTask task = new ContactTask(getActivity(), listView);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layout);
        listView.setHasFixedSize(true);

        EventHelper.register(this); // register Fragment in EventHelper to observe AsynTask completion
        if(a.getContactList() != null && !a.getContactList().isEmpty()) {
            onTaskComplete((ArrayList) a.getContactList());
        } else {
            task.execute();
        }
        return  view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventHelper.unregister(this);
    }

    @Subscribe
    public void onTaskComplete(ArrayList<User> users) {
        ((MainActivity) getActivity()).setContactList(users);
        listView.setAdapter(new ContactsAdapter(users, getActivity()));
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = listView.getChildAdapterPosition(v);
            }
        });
    }
}
