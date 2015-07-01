package com.solstice.codechallenge.contactlist.adapters;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solstice.codechallenge.contactlist.DetailsFragment;
import com.solstice.codechallenge.contactlist.MainActivity;
import com.solstice.codechallenge.contactlist.R;
import com.solstice.codechallenge.contactlist.entities.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by snakepit on 27/06/2015.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<User> data;
    private Context ctx;

    public ContactsAdapter(List<User> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v, data);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = data.get(position);
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone().getMobile());
        Picasso.with(ctx).load(user.getSmallImageURL()).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView name;
        public TextView phone;
        public ImageView pic;
        private List<User> users;

        public ViewHolder(View v, List<User> users) {
            super(v);
            this.users = users;
            v.setOnClickListener(this);
            name = (TextView)v.findViewById(R.id.contact_text_view);
            phone = (TextView)v.findViewById(R.id.phone_text_view);
            pic = (ImageView)v.findViewById(R.id.small_image_view);
        }

        @Override
        public void onClick(View v) {
            MainActivity activity = (MainActivity) v.getContext();
            int position = getAdapterPosition();
            DetailsFragment nextFrag = new DetailsFragment();
            User user = users.get(position);

            Fragment oldFragment = activity.getFragmentManager().findFragmentById(R.id.fragment);
            Bundle args = new Bundle();
            args.putParcelable(activity.getString(R.string.details_url_key), user);
            nextFrag.setArguments(args);
            activity.getFragmentManager()
                .beginTransaction()
                .addToBackStack(oldFragment.getTag())
                .replace(R.id.fragment, nextFrag)
                .commit();
        }
    }

}
