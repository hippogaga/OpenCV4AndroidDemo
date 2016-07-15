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
                fragment.grey();
                break;
            case R.id.right_menu_itme2:

                break;
            case R.id.right_menu_itme3:

                break;
            case R.id.right_menu_itme4:

                break;
            case R.id.right_menu_itme5:

                break;
            case R.id.right_menu_itme6:

                break;
            case R.id.right_menu_itme7:

                break;
            case R.id.right_menu_itme8:

                break;
            case R.id.right_menu_itme9:

                break;
            case R.id.right_menu_itme10:

                break;
            case R.id.right_menu_itme11:

                break;
            case R.id.right_menu_itme12:

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
