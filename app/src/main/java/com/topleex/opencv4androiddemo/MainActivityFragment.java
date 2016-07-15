package com.topleex.opencv4androiddemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Context mContext;

    private TextView textView;
    private ImageView imageView;

    private OpenCVHelper openCV;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        openCV = new OpenCVHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) root.findViewById(R.id.textView1);
        imageView = (ImageView) root.findViewById(R.id.imageView1);
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(Global.TAG, "request code:" + requestCode);
        Log.d(Global.TAG, "result code:" + resultCode);

        switch (requestCode) {
            case Global.IMPORT_IMG_CODE:
                if (data != null) {
                    Uri imageUri = data.getData();
                    useImage(imageUri.getPath());
                }
                break;
        }
    }

    public void useDefaultImage () {
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                R.drawable.layla)).getBitmap();

        imageView.setImageBitmap(bitmap);
        textView.setText("Layla");
    }

    public void useImage(String imgPath) {

        int with = imageView.getWidth();
        BitmapFactory.Options options = new BitmapFactory.Options();
        // options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
        int scale = (int)( options.outWidth / (float) with);
        if(scale <= 0)
            scale = 1;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(imgPath, options);

        imageView.setImageBitmap(bitmap);
        imageView.setMaxHeight(imageView.getHeight());
        textView.setText(imgPath);
    }

    public void importImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, Global.IMPORT_IMG_CODE);
    }

    //image process
    public void grey() {
        imageView.setDrawingCacheEnabled(true);
        Bitmap bm = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(true);

        int w = bm.getWidth(), h = bm.getHeight();
        int[] pix = new int[w * h];
        bm.getPixels(pix, 0, w, 0, 0, w, h);
        int [] resultPixes=OpenCVHelper.gray(pix,w,h);
        Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
        result.setPixels(resultPixes, 0, w, 0, 0,w, h);

        imageView.setImageBitmap(result);
    }
}
