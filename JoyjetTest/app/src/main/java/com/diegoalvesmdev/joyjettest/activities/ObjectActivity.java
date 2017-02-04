package com.diegoalvesmdev.joyjettest.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.diegoalvesmdev.joyjettest.R;
import com.diegoalvesmdev.joyjettest.database.ObjectRepository;
import com.diegoalvesmdev.joyjettest.fragments.ObjectFragment;
import com.diegoalvesmdev.joyjettest.model.Object;

/**
 * Created by diegoalves on 03/02/2017.
 */
public class ObjectActivity extends CollapsingToolbarActivity {

    Object object;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        object = getIntent().getParcelableExtra("object");

        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fabFavorite);

        if (object.getFavorite() == 1) {
            floatingActionButton.setImageResource(R.drawable.ic_action_star_10);
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_action_star_1);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (object.getFavorite() == 0) {
                    setFavorite(true);
                    floatingActionButton.setImageResource(R.drawable.ic_action_star_10);
                    Toast.makeText(ObjectActivity.this, R.string.add_favorites, Toast.LENGTH_SHORT).show();
                } else {
                    setFavorite(false);
                    floatingActionButton.setImageResource(R.drawable.ic_action_star_1);
                    Toast.makeText(ObjectActivity.this, R.string.remove_favorites, Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (savedInstanceState == null) {
            ObjectFragment fragment = new ObjectFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragContainer, fragment, null).commit();
        }
    }

    public void setFavorite(boolean favorite) {
        ObjectRepository objectRepository = new ObjectRepository(this);

        if (favorite) {
            object.setFavorite(1);
            objectRepository.update(object);
        } else {
            object.setFavorite(0);
            objectRepository.update(object);
        }

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
