package com.topleex.opencv4androiddemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_AVERAGE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_BINARY;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_CANNY;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_CLOSE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_DILATION;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_ERODE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_GAUSS;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_GREY;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_HIST;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_MEDIAN;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_OPEN;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_PIXELIZE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_POSTERIZE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_RGBA;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_SEPIA;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_SOBEL;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_ZOOM;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Context mContext;

    private TextView textView;
    private ImageView imageView;

    private Bitmap source;

    private OpenCVHelper openCV;


    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) root.findViewById(R.id.textView1);
        imageView = (ImageView) root.findViewById(R.id.imageView1);
        openCV = new OpenCVHelper();

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

    public void useDefaultImage() {
//        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
//                R.drawable.layla)).getBitmap();
//
//        imageView.setImageBitmap(bitmap);
//        textView.setText("Layla");
    }

    public void useImage(String imgPath) {

        int with = imageView.getWidth();
        BitmapFactory.Options options = new BitmapFactory.Options();
        // options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
        int scale = (int) (options.outWidth / (float) with);
        if (scale <= 0)
            scale = 1;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(imgPath, options);
        source = bitmap;
        imageView.setImageBitmap(bitmap);
        imageView.setMaxHeight(imageView.getHeight());
        textView.setText(imgPath);
    }

    public void importImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, Global.IMPORT_IMG_CODE);
    }

    public void process(int mode) {

        Bitmap bm = Bitmap.createBitmap(source);

        int w = bm.getWidth(), h = bm.getHeight();
        int[] pix = new int[w * h];
        bm.getPixels(pix, 0, w, 0, 0, w, h);

        int[] result = new int[w * h];


        switch (mode) {
            case VIEW_MODE_RGBA:

                break;

            case VIEW_MODE_HIST:
                result = OpenCVHelper.histo(pix, w, h);
                break;

            case VIEW_MODE_CANNY:

                break;

            case VIEW_MODE_SOBEL:
                result = OpenCVHelper.sobel(pix, w, h);
                break;

            case VIEW_MODE_SEPIA:

                break;

            case VIEW_MODE_ZOOM:

                break;

            case VIEW_MODE_PIXELIZE:

                break;

            case VIEW_MODE_POSTERIZE:

                break;
            case VIEW_MODE_GREY:
                result = OpenCVHelper.gray(pix, w, h);
                break;
            case VIEW_MODE_BINARY:
                result = OpenCVHelper.binary(pix, w, h);
                break;
            case VIEW_MODE_GAUSS:
                result = OpenCVHelper.gauss(pix, w, h);
                break;
            case VIEW_MODE_MEDIAN:
                result = OpenCVHelper.median(pix, w, h);
                break;
            case VIEW_MODE_AVERAGE:
                result = OpenCVHelper.average(pix, w, h);
                break;
            case VIEW_MODE_ERODE:
                result = OpenCVHelper.erode(pix, w, h);
                break;
            case VIEW_MODE_DILATION:
                result = OpenCVHelper.dilation(pix, w, h);
                break;
            case VIEW_MODE_OPEN:
                result = OpenCVHelper.open(pix, w, h);
                break;
            case VIEW_MODE_CLOSE:
                result = OpenCVHelper.close(pix, w, h);
                break;
        }

        Bitmap res = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        res.setPixels(result, 0, w, 0, 0, w, h);

        imageView.setImageBitmap(res);
    }
}
