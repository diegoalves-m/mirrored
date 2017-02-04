package com.diegoalvesmdev.joyjettest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegoalvesmdev.joyjettest.R;
import com.diegoalvesmdev.joyjettest.adapters.ObjectAdapter;
import com.diegoalvesmdev.joyjettest.database.ObjectRepository;
import com.diegoalvesmdev.joyjettest.model.Object;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ObjectAdapter adapter;
    List<Object> mObjects;
    TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.favorites));
        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorites);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvMsg = (TextView) findViewById(R.id.tvMsg);

        completeList();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void completeList() {
        ObjectRepository objectRepository = new ObjectRepository(this);
        mObjects = objectRepository.findFavorites();
        if(!mObjects.isEmpty()) {
            tvMsg.setVisibility(View.INVISIBLE);
        } else {
            tvMsg.setVisibility(View.VISIBLE);
        }

        adapter = new ObjectAdapter(mObjects, this, onClick());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        completeList();
    }

    private ObjectAdapter.OnClickObject onClick() {
        return new ObjectAdapter.OnClickObject() {
            @Override
            public void onClick(ObjectAdapter.PlaceHolder holder, int position) {
                Object object = mObjects.get(position);

                Intent intent = new Intent(FavoritesActivity.this, ObjectActivity.class);
                intent.putExtra("object", object);

                String key = getString(R.string.transition_key);
                ImageView img = holder.imageView;
                ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(FavoritesActivity.this, img, key);
                ActivityCompat.startActivity(FavoritesActivity.this, intent, opts.toBundle());
            }
        };
    }

}
