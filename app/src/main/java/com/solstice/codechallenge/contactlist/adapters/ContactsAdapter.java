package com.solstice.codechallenge.contactlist.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.solstice.codechallenge.contactlist.MainActivity;
import com.solstice.codechallenge.contactlist.R;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.task.BitmapTask;

import java.util.List;

/**
 * Created by snakepit on 27/06/2015.
 */
public class ContactsAdapter extends ArrayAdapter<User> {

    private final String TAG = ContactsAdapter.class.getSimpleName();
    private LayoutInflater vi;

    public ContactsAdapter(Context context, int resource, List<User> list) {
        super(context, resource , resource, list);
        vi = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        User contact = getItem(position);
        if(convertView == null) {
            convertView = vi.inflate(R.layout.fragment_main_list_item, parent, false);
            holder.url = contact.getSmallImageURL();
            holder.contactName =  (TextView) convertView.findViewById(R.id.contact_text_view);
            holder.phoneNumber = (TextView ) convertView.findViewById(R.id.phone_text_view);
            holder.icon = (ImageView) convertView.findViewById(R.id.small_image_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhone().getMobile());
        Bitmap cacheBitmap = ((MainActivity)getContext()).getBitmapFromMemCache(contact.getSmallImageURL());

        if(cacheBitmap != null) {
            holder.icon.setImageBitmap(cacheBitmap);
        } else {
            if(BitmapTask.cancelPotentialWork(contact.getSmallImageURL(), holder.icon)) {
                BitmapTask task = new BitmapTask(getContext(), holder.icon);
                BitmapTask.AsyncDrawable asyncDrawable = new BitmapTask.AsyncDrawable(getContext().getResources(), null, task);
                holder.icon.setImageDrawable(asyncDrawable);
                task.execute(contact.getSmallImageURL());
            }
        }


        return convertView;
    }

    class ViewHolder {
        TextView contactName;
        TextView phoneNumber;
        ImageView icon;
        String url;
    }

}
