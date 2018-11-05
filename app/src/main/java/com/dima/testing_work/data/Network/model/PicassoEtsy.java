package com.dima.testing_work.data.Network.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.dima.testing_work.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PicassoEtsy {

    public static void  imageDownload(String url,ImageView image, Callback callback){
        Picasso.get().load(url).into(image,callback);

    }

    public static void imageDownloadFile(String url,ImageView image, Target target ){
        Picasso.get().load(url).into(target);
    }

    //target to save
    private Target getTarget(){
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        File path = new File(Environment.getExternalStorageDirectory(),
                                "/BIGDIG/test/B");
                        path.mkdirs();
                        File file = new File(Environment.getExternalStorageDirectory()
                                +"/BIGDIG/test/B/2.jpg");
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
}
