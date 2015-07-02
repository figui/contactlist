package com.solstice.codechallenge.contactlist.task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.solstice.codechallenge.contactlist.MainActivity;
import com.solstice.codechallenge.contactlist.R;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.entities.UserDetail;
import com.solstice.codechallenge.contactlist.services.ContactService;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import retrofit.RestAdapter;

/**
 * Created by snakepit on 27/06/2015.
 */
public class DetailTask extends AsyncTask<String, Void, UserDetail> {

    private final String TAG = DetailTask.class.getSimpleName();
    private Context ctx;
    private User user;
    public DetailTask(Context ctx, User user) {
        this.ctx = ctx;
        this.user = user;
    }

    @Override
    protected UserDetail doInBackground(String... params) {
        UserDetail result = null;
        try {
            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint("https://solstice.applauncher.com");
            RestAdapter adapter = builder.build();
            ContactService service = adapter.create(ContactService.class);
            result = service.getDetails(user.getEmployeeId());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }

    @Override
    protected void onPostExecute(UserDetail userDetail) {
        if(isCancelled()) {
            userDetail = null;
        }

        if(userDetail != null) {
            Activity a = (MainActivity) ctx;
            TextView name = ButterKnife.findById(a, R.id.name_text_view);
            TextView company = ButterKnife.findById(a, R.id.company_text_view);
            TextView phone = ButterKnife.findById(a, R.id.phone_text_view);
            TextView phoneType = ButterKnife.findById(a, R.id.phone_type_text_view);
            TextView address = ButterKnife.findById(a, R.id.address_text_view);
            TextView state = ButterKnife.findById(a, R.id.state_text_view);
            TextView birth = ButterKnife.findById(a, R.id.birthdate_text_view);
            TextView email = ButterKnife.findById(a, R.id.email_text_view);
            ImageView avatar = ButterKnife.findById(a, R.id.large_image_view);
            ImageView favorite = ButterKnife.findById(a, R.id.favorite_image_view);
            Picasso.with(a).load(userDetail.getLargeImageURL()).into(avatar);

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

