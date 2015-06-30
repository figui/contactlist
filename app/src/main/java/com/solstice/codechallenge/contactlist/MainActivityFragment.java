package com.solstice.codechallenge.contactlist;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.solstice.codechallenge.contactlist.task.ContactTask;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        MainActivity a = (MainActivity) getActivity();
        ContactTask task = new ContactTask(getActivity(), listView);
        if(a.getContactList() != null && !a.getContactList().isEmpty()) {
            task.setContactList(a.getContactList());
        } else {
            task.execute();
        }
        return  view;
    }
}
