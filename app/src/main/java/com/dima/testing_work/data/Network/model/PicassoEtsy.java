package com.dima.testing_work.data.Network.model;

import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class PicassoEtsy {

    public static void imageDownload(String url,ImageView image){
        Picasso.get().load(url).transform(new CropCircleTransformation()).into(image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    public static void imageDownloadFile(String url,ImageView image, Target target ){
        Picasso.get().load(url).into(target);
    }

    /*//target to save
    private Target getTarget() {
        Target target = new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        File path = new File(Environment.getExternalStorageDirectory(),
                                "/BIGDIG/test/B");
                        path.mkdirs();
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/BIGDIG/test/B/2.jpg");
                        Log.d("picc", Environment.getExternalStorageDirectory().getAbsolutePath());
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }*/
}
