package com.solstice.codechallenge.contactlist.task;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.solstice.codechallenge.contactlist.MainActivity;
import com.solstice.codechallenge.contactlist.R;
import com.solstice.codechallenge.contactlist.services.ContactService;
import com.solstice.codechallenge.contactlist.services.impl.ContactServiceImpl;

/**
 * Created by snakepit on 27/06/2015.
 */
public class ImageTask extends AsyncTask<String, Void, Bitmap> {

    private Context ctx;

    public ImageTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        ContactService service = new ContactServiceImpl();
        return service.getImage(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(isCancelled()) {
            bitmap = null;
        }

        if(bitmap != null) {
            Activity a = (MainActivity) ctx;
            ImageView large = (ImageView) a.findViewById(R.id.large_image_view);
            large.setImageBitmap(bitmap);
        }
    }
}
