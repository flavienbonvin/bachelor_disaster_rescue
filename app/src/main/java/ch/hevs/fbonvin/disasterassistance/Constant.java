package ch.hevs.fbonvin.disasterassistance;

import android.Manifest;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ch.hevs.fbonvin.disasterassistance.models.Endpoint;
import ch.hevs.fbonvin.disasterassistance.models.Message;
import ch.hevs.fbonvin.disasterassistance.utils.NearbyManagement;
import ch.hevs.fbonvin.disasterassistance.views.fragments.FragMessagesList;
import ch.hevs.fbonvin.disasterassistance.views.fragments.FragMessagesSent;

/**
 * File regrouping all the constant needed for the project
 */
public class Constant {

    public static final String TAG = "DisasterRescue";



    /**
     * Fragments saved instead of recreated each time
     */
    public static final FragMessagesList FRAG_MESSAGE_LIST = new FragMessagesList();
    public static final FragMessagesSent FRAG_MESSAGES_SENT = new FragMessagesSent();



    /**
     * All constants related to the permissions
     */
    public static final String[] MANDATORY_PERMISSION =
            new String[]{
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
    public static final int CODE_MANDATORY_PERMISSIONS = 1;


    public static NearbyManagement NEARBY_MANAGEMENT;



    /**
     * All constants related to the messages
     */
    public static final String MESSAGE_SEPARATOR = Character.toString((char)30);

    //All message that have been received by the device
    public static ArrayList<Message> MESSAGES_RECEIVED;
    //All messages send by the device
    public static ArrayList<Message> MESSAGE_SENT;
    //All messages the user wanted to send but there was no peers around
    public static ArrayList<Message> MESSAGE_QUEUE;
    //All messages the user wanted to delete but there was no peers around
    public static ArrayList<Message> MESSAGE_QUEUE_DELETED;

    public static final String MESSAGE_STATUS_NEW = "new";
    public static final String MESSAGE_STATUS_DELETE = "delete";
    public static final String MESSAGE_STATUS_UPDATE = "update";

    //Headers used to identify messages type
    public static final String HEADER_MESSAGE = "message";



    /**
     * All constants related to Google Nearby
     */
    //Discovered devices
    public static final Map<String, Endpoint> DISCOVERED_ENDPOINTS = new HashMap<>();
    //Device that have pending connection
    public static final Map<String, Endpoint> CONNECTING_ENDPOINTS = new HashMap<>();
    //Device we are currently connected to
    public static final Map<String, Endpoint> ESTABLISHED_ENDPOINTS = new HashMap<>();



    /**
     * All constants related to the position
     */
    public static FusedLocationProviderClient FUSED_LOCATION_PROVIDER;
    public static Location CURRENT_DEVICE_LOCATION = null;



    /**
     * All constants related to the preferences
     */
    public static boolean FIRST_INSTALL = false;
    public static final String PREF_NAME = "ch.hevs.fbonvin.settings";

    //Store all the messages received, sent and in queue
    public static final String PREF_NAME_MESSAGE_RECEIVED = "ch.hevs.fbonvin.message.received";
    public static final String PREF_NAME_MESSAGE_SENT = "ch.hevs.fbonvin.message.sent";
    public static final String PREF_NAME_MESSAGE_QUEUE = "ch.hevs.fbonvin.message.queue";
    public static final String PREF_NAME_MESSAGE_QUEUE_DELETED = "ch.hevs.fbonvin.message.queue.deleted";

    public static final String PREF_KEY_MESSAGE_RECEIVED = "message_received";
    public static final String PREF_KEY_MESSAGE_SENT = "message_sent";
    public static final String PREF_KEY_MESSAGE_QUEUE = "message_queue";
    public static final String PREF_KEY_MESSAGE_QUEUE_DELETED = "message_queue_deleted";

    public static final String PREF_NOT_SET = "NOT_SET";



    public static String VALUE_PREF_APPID;
    public static String VALUE_PREF_USERNAME;
    public static String VALUE_PREF_RADIUS_GEO_FENCING;
}
