package com.diegoalvesmdev.joyjettest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegoalvesmdev.joyjettest.R;
import com.diegoalvesmdev.joyjettest.activities.CollapsingToolbarActivity;
import com.diegoalvesmdev.joyjettest.model.Object;

/**
 * Created by diegoalves on 03/02/2017.
 */
public class ObjectFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.object_fragment, container, false);

        Intent it = getActivity().getIntent();
        Object object = it.getParcelableExtra("object");
        if (object != null) {

            CollapsingToolbarActivity activity = (CollapsingToolbarActivity) getActivity();
            activity.setAppBarImage(object.getPhoto());
            activity.setAppBarTitle(object.getName());
        }

        return view;
    }
}
