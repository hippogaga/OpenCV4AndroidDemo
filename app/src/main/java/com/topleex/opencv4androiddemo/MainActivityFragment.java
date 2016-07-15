package com.topleex.opencv4androiddemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View root;

    private TextView textView;
    private ImageView imageView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) root.findViewById(R.id.textView1);
        imageView = (ImageView) root.findViewById(R.id.imageView1);
        return root;
    }



    public void test () {
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                R.drawable.layla)).getBitmap();

        textView.setText("Hello World Li Xiang");
        imageView.setImageBitmap(bitmap);
    }
}
