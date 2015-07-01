package com.solstice.codechallenge.contactlist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solstice.codechallenge.contactlist.task.ContactTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.listView);
        MainActivity a = (MainActivity) getActivity();
        ContactTask task = new ContactTask(getActivity(), listView);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layout);
        listView.setHasFixedSize(true);
        if(a.getContactList() != null && !a.getContactList().isEmpty()) {
            task.setContactList(a.getContactList());
        } else {
            task.execute();
        }
        return  view;
    }
}
