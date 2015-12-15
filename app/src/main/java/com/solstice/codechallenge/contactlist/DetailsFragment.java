package com.solstice.codechallenge.contactlist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.entities.UserDetail;
import com.solstice.codechallenge.contactlist.helpers.EventHelper;
import com.solstice.codechallenge.contactlist.task.DetailTask;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by snakepit on 27/06/2015.
 */
public class DetailsFragment extends Fragment {

    @Bind(R.id.large_image_view)
    ImageView imageView;

    @Bind(R.id.name_text_view)
    TextView nameView;

    @Bind(R.id.favorite_image_view)
    ImageView favoriteImageView;

    @Bind(R.id.company_text_view)
    TextView companyView;

    @Bind(R.id.phone_text_view)
    TextView phoneView;

    @Bind(R.id.phone_type_text_view)
    TextView phoneTypeView;

    @Bind(R.id.address_text_view)
    TextView addressView;

    @Bind(R.id.state_text_view)
    TextView stateView;

    @Bind(R.id.birthdate_text_view)
    TextView birthdateView;

    @Bind(R.id.email_text_view)
    TextView emailView;

    public DetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        Bundle arg = getArguments();
        User user = arg.getParcelable(getString(R.string.details_url_key));
        DetailTask task = new DetailTask(getActivity(), user);
        EventHelper.register(this);
        task.execute(user.getDetailsURL());
        return  view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventHelper.unregister(this);
    }

    @Subscribe
    public void onTaskComplete(UserDetail userDetail) {
        Picasso.with(getActivity()).load(userDetail.getLargeImageURL()).into(imageView);
        nameView.setText(userDetail.getUser().getName());
        favoriteImageView.setVisibility(userDetail.isFavorite() ? View.VISIBLE : View.INVISIBLE);
        companyView.setText(userDetail.getUser().getCompany());
        phoneView.setText(userDetail.getUser().getPhone().getMobile());
        phoneTypeView.setText("Mobile");
        addressView.setText(userDetail.getAddress().getStreet());
        stateView.setText(userDetail.getAddress().getState());
        birthdateView.setText(userDetail.getUser().getBirthdateAsString());
        emailView.setText(userDetail.getEmail());
    }
}