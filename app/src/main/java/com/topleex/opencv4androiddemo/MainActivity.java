package com.topleex.opencv4androiddemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_AVERAGE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_BINARY;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_CLOSE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_DILATION;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_ERODE;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_GAUSS;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_GREY;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_HIST;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_MEDIAN;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_OPEN;
import static com.topleex.opencv4androiddemo.Global.VIEW_MODE_SOBEL;

public class MainActivity extends AppCompatActivity {

    private MainActivityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.importImage();
            }
        });

        //在程序中加入Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new MainActivityFragment();
        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.right_menu_itme1:
                fragment.process(VIEW_MODE_GREY);;
                break;
            case R.id.right_menu_itme2:
                fragment.process(VIEW_MODE_BINARY);
                break;
            case R.id.right_menu_itme4:
                fragment.process(VIEW_MODE_HIST);
                break;
            case R.id.right_menu_itme5:
                fragment.process(VIEW_MODE_GAUSS);
                break;
            case R.id.right_menu_itme6:
                fragment.process(VIEW_MODE_SOBEL);
                break;
            case R.id.right_menu_itme8:
                fragment.process(VIEW_MODE_MEDIAN);
                break;
            case R.id.right_menu_itme9:
                fragment.process(VIEW_MODE_DILATION);
                break;
            case R.id.right_menu_itme10:
                fragment.process(VIEW_MODE_ERODE);
                break;
            case R.id.right_menu_itme11:
                fragment.process(VIEW_MODE_OPEN);
                break;
            case R.id.right_menu_itme12:
                fragment.process(VIEW_MODE_CLOSE);
                break;
            case R.id.right_menu_itme13:
                fragment.process(VIEW_MODE_AVERAGE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
