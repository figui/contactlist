package com.solstice.codechallenge.contactlist.task;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.solstice.codechallenge.contactlist.BuildConfig;
import com.solstice.codechallenge.contactlist.MainActivity;
import com.solstice.codechallenge.contactlist.services.ContactService;
import com.solstice.codechallenge.contactlist.services.impl.ContactServiceImpl;

import java.lang.ref.WeakReference;

public class BitmapTask extends AsyncTask<String, Void, Bitmap>{

    private String url;
    private WeakReference<ImageView> imageViewWeakReference;
    private static final String TAG = BitmapTask.class.getSimpleName();
    private Context context;

    public BitmapTask(Context context, ImageView view) {
        this.context = context;
        this.imageViewWeakReference = new WeakReference<ImageView>(view);
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        this.url = params[0];
        ContactService service = new ContactServiceImpl();
        Bitmap bitmap = service.getImage(this.url);
        ((MainActivity)this.context).addBitmapToMemoryCache(this.url, bitmap);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(isCancelled()){
            bitmap = null;
        }

        if(imageViewWeakReference != null && bitmap != null) {
            ImageView view = imageViewWeakReference.get();
            if(view != null) {
                view.setImageBitmap(bitmap);
            }
        }
    }

    public static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap, BitmapTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference = new WeakReference<BitmapTask>(bitmapWorkerTask);
        }

        public BitmapTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }

    public String getUrl() {
        return url;
    }

    public static boolean cancelPotentialWork(Object data, ImageView imageView) {
        //BEGIN_INCLUDE(cancel_potential_work)
        final BitmapTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final Object bitmapData = bitmapWorkerTask.getUrl();
            if (bitmapData == null || !bitmapData.equals(data)) {
                bitmapWorkerTask.cancel(true);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "cancelPotentialWork - cancelled work for " + data);
                }
            } else {
                // The same work is already in progress.
                return false;
            }
        }
        return true;
        //END_INCLUDE(cancel_potential_work)
    }

    private static BitmapTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }
}