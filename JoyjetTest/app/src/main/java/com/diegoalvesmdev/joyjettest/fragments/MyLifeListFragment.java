package com.diegoalvesmdev.joyjettest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.diegoalvesmdev.joyjettest.R;
import com.diegoalvesmdev.joyjettest.activities.ObjectActivity;
import com.diegoalvesmdev.joyjettest.adapters.ObjectAdapter;
import com.diegoalvesmdev.joyjettest.database.ObjectRepository;
import com.diegoalvesmdev.joyjettest.model.Object;
import com.diegoalvesmdev.joyjettest.util.RegisterObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegoalves on 03/02/2017.
 */

public class MyLifeListFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<Object> mObjects;
    ObjectAdapter mObjectAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_life_list_fragment, container, false);

        mObjects = new ArrayList<>();
        completeList();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_my_life);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mObjectAdapter = new ObjectAdapter(mObjects, getContext(), onClick());
        mRecyclerView.setAdapter(mObjectAdapter);

        return view;

    }

    private void completeList() {
        ObjectRepository objectRepository = new ObjectRepository(getContext());
        mObjects = objectRepository.findByCategory("myLife");
    }

    @Override
    public void onStart() {
        super.onStart();
        completeList();
    }

    private ObjectAdapter.OnClickObject onClick() {
        return new ObjectAdapter.OnClickObject() {
            @Override
            public void onClick(ObjectAdapter.PlaceHolder holder, int position) {
                Object object = mObjects.get(position);

                Intent intent = new Intent(getContext(), ObjectActivity.class);
                intent.putExtra("object", object);

                String key = getString(R.string.transition_key);

                ImageView img = holder.imageView;
                ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), img, key);
                ActivityCompat.startActivity(getActivity(), intent, opts.toBundle());
            }
        };
    }
}
