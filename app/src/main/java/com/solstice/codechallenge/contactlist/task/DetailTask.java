package com.solstice.codechallenge.contactlist.task;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.solstice.codechallenge.contactlist.MainActivity;
import com.solstice.codechallenge.contactlist.R;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.entities.UserDetail;
import com.solstice.codechallenge.contactlist.services.ContactService;
import com.solstice.codechallenge.contactlist.services.impl.ContactServiceImpl;

/**
 * Created by snakepit on 27/06/2015.
 */
public class DetailTask extends AsyncTask<String, Void, UserDetail> {

    private Context ctx;
    private User user;

    public DetailTask(Context ctx, User user) {
        this.ctx = ctx;
        this.user = user;
    }

    @Override
    protected UserDetail doInBackground(String... params) {
        ContactService service = new ContactServiceImpl();
        return service.getDetails(params[0]);
    }

    @Override
    protected void onPostExecute(UserDetail userDetail) {
        if(isCancelled()) {
            userDetail = null;
        }

        if(userDetail != null) {
            Activity a = (MainActivity) ctx;

            // load the large image bitmap
            new ImageTask(ctx).execute(userDetail.getLargeImageURL());

            TextView name = (TextView) a.findViewById(R.id.name_text_view);
            TextView company = (TextView) a.findViewById(R.id.company_text_view);
            TextView phone = (TextView) a.findViewById(R.id.phone_text_view);
            TextView phoneType = (TextView) a.findViewById(R.id.phone_type_text_view);
            TextView address = (TextView) a.findViewById(R.id.address_text_view);
            TextView state = (TextView) a.findViewById(R.id.state_text_view);
            TextView birth = (TextView) a.findViewById(R.id.birthdate_text_view);
            TextView email = (TextView) a.findViewById(R.id.email_text_view);
            ImageView favorite = (ImageView) a.findViewById(R.id.favorite_image_view);

            name.setText(String.valueOf(user.getName()));
            company.setText(user.getCompany());
            phone.setText(user.getPhone().getMobile());
            phoneType.setText("Mobile");
            address.setText(userDetail.getAddress().getStreet());
            state.setText(userDetail.getAddress().getState());
            birth.setText(user.getBirthdateAsString());
            email.setText(userDetail.getEmail());

            if(userDetail.isFavorite()) {
                favorite.setVisibility(View.VISIBLE);
            }
        }
    }
}
