package ch.hevs.fbonvin.disasterassistance.utils;

import android.location.Location;

import java.math.BigDecimal;
import java.util.ArrayList;

import ch.hevs.fbonvin.disasterassistance.models.Message;

import static ch.hevs.fbonvin.disasterassistance.Constant.CURRENT_DEVICE_LOCATION;

public abstract class LocationManagement {

    public static float getDistance(String provider ,double lat, double lng){
        Location locationMessage = new Location(provider);
        locationMessage.setLongitude(lng);
        locationMessage.setLatitude(lat);

        float distance = locationMessage.distanceTo(CURRENT_DEVICE_LOCATION);

        BigDecimal bd = new BigDecimal(Float.toString(distance));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

        return bd.floatValue();
    }

    public static void getDistance(ArrayList<Message> messages){

        for (int i = 0; i < messages.size(); i++){
            String provider = messages.get(i).getTitle();
            Double lat = messages.get(i).getMessageLatitude();
            Double lng = messages.get(i).getMessageLongitude();

            float dist = LocationManagement.getDistance(provider, lat, lng);

            messages.get(i).setDistance(dist);
        }
    }
}
