package com.diegoalvesmdev.joyjettest.util;

import android.content.Context;

import com.diegoalvesmdev.joyjettest.database.ObjectRepository;
import com.diegoalvesmdev.joyjettest.model.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegoalves on 04/02/2017.
 */

public class RegisterObjects {

    private ObjectRepository repository;

    public RegisterObjects(Context context) {
        repository = new ObjectRepository(context);

    }

    public void insert() {
        String description = "Lorem ipsum dolor sit amet, consectetur.";
        String categoryPlace = "place";
        String categoryLife = "myLife";

        // place
        Object moon = new Object("Moon", description, "http://res.cloudinary.com/sayaryx/image/upload/v1486162760/moon_hvnaof.jpg", categoryPlace, 0);
        Object earth = new Object("Earth", description, "http://res.cloudinary.com/sayaryx/image/upload/v1486162759/earth_cqurpr.jpg", categoryPlace, 0);
        Object mars = new Object("Mars", description, "http://res.cloudinary.com/sayaryx/image/upload/v1486162761/mars_lncg8s.jpg", categoryPlace, 0);
        //my life
        Object station = new Object("Space station", description, "http://res.cloudinary.com/sayaryx/image/upload/v1486162759/station_vxikac.jpg", categoryLife, 0);
        Object curiosity = new Object("Curiosity", description, "http://res.cloudinary.com/sayaryx/image/upload/v1486162759/curiosity_tpjprk.jpg", categoryLife, 0);
        Object capsule = new Object("Capsule", description, "http://res.cloudinary.com/sayaryx/image/upload/v1486162758/capsule_dzwdyo.jpg", categoryLife, 0);
        List<Object> objects = new ArrayList<>();
        objects.add(moon);
        objects.add(earth);
        objects.add(mars);
        objects.add(station);
        objects.add(curiosity);
        objects.add(capsule);

        for (int i = 0; i < objects.size(); i++) {
            repository.insert(objects.get(i));
        }
    }

}
