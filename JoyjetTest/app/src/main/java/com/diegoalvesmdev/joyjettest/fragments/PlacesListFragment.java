package com.diegoalvesmdev.joyjettest.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.diegoalvesmdev.joyjettest.util.VerifyConnection;

import java.util.List;

/**
 * Created by diegoalves on 03/02/2017.
 */

public class PlacesListFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<Object> mObjects;
    ObjectAdapter mObjectAdapter;
    private static final String PREFS = "config";
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.places_list_fragment, container, false);

        sharedPreferences = getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        completeList();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_places);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mObjectAdapter = new ObjectAdapter(mObjects, getContext(), onClick());
        mRecyclerView.setAdapter(mObjectAdapter);

        return view;

    }

    private void completeList() {
        if (sharedPreferences.getBoolean(PREFS, true)) {
            RegisterObjects registerObjects = new RegisterObjects(getContext());
            registerObjects.insert();
            sharedPreferences.edit().putBoolean(PREFS, false).apply();

            VerifyConnection connection = new VerifyConnection(getContext());
            if(!connection.hasConnection()) {
                showSnackBar();
            }

        }

        ObjectRepository objectRepository = new ObjectRepository(getContext());
        mObjects = objectRepository.findByCategory("place");
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public void showSnackBar() {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.content_main), R.string.download, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.settings, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                getActivity().finish();
            }
        });
        snackbar.show();
    }

}
