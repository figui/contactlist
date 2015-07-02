package com.solstice.codechallenge.contactlist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.task.DetailTask;

/**
 * Created by snakepit on 27/06/2015.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle arg = getArguments();
        User user = arg.getParcelable(getString(R.string.details_url_key));
        DetailTask task = new DetailTask(getActivity(), user);
        task.execute(user.getDetailsURL());
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        return  view;
    }
}