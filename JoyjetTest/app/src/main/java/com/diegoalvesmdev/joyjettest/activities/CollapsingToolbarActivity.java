package com.diegoalvesmdev.joyjettest.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.diegoalvesmdev.joyjettest.R;
import com.diegoalvesmdev.joyjettest.StarterApplication;

/**
 * Created by diegoalves on 03/02/2017.
 */
public class CollapsingToolbarActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;
    private NetworkImageView appBarImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
    }

    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBarImg = (NetworkImageView) findViewById(R.id.appBarImg);
        ViewCompat.setTransitionName(appBarImg, getString(R.string.transition_key));
    }

    public void setAppBarTitle(String title) {
        collapsingToolbar.setTitle(title);
    }

    public void setAppBarImage(String url) {

        ImageLoader imageLoader = StarterApplication.getInstance().getImageLoader();

        appBarImg.setImageUrl(url, imageLoader);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
