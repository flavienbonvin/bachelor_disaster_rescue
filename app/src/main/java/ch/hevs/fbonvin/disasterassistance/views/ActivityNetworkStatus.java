package ch.hevs.fbonvin.disasterassistance.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ch.hevs.fbonvin.disasterassistance.R;
import ch.hevs.fbonvin.disasterassistance.utils.NearbyManagement;

import static ch.hevs.fbonvin.disasterassistance.Constant.*;

public class ActivityNetworkStatus extends AppCompatActivity {

    private TextView tvDiscovering;
    private TextView tvAdvertising;

    private TextView tvPeersConnected;
    private TextView tvPeerConnecting;
    private TextView tvPeerDiscovered;

    private ImageView imError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_status);

        initView();

        setText();
    }


    /**
     * Init elements presents in the view
     */
    private void initView() {
        tvDiscovering = findViewById(R.id.tv_status_discover);
        tvAdvertising = findViewById(R.id.tv_status_advertise);

        tvPeersConnected = findViewById(R.id.tv_established_peer);
        tvPeerConnecting = findViewById(R.id.tv_pending_peer);
        tvPeerDiscovered = findViewById(R.id.tv_discovered_peer);

        imError = findViewById(R.id.im_status);
    }


    /**
     * Set text of elements presents in the view
     */
    private void setText(){

        setTextNearbyStatus();

        setText(tvPeersConnected, tvPeersConnected.getText() + " " +
                String.valueOf(ESTABLISHED_ENDPOINTS.size()));
        setText(tvPeerConnecting, tvPeerConnecting.getText() + " " +
                String.valueOf(CONNECTING_ENDPOINTS.size()));
        setText(tvPeerDiscovered, tvPeerDiscovered.getText() + " " +
                String.valueOf(DISCOVERED_ENDPOINTS.size()));

        //TODO add tooltip on icon to help user understand the problem
        //normal if discovery off while connecting
        //something wrong otherwise, check permission
        if ((!NearbyManagement.ismIsAdvertising() && !NearbyManagement.ismIsDiscovering())) {

            setErrorIcon();

            Snackbar.make(findViewById(android.R.id.content),
                    R.string.problem_google_nearby, Snackbar.LENGTH_LONG)
                    
                    .setAction("More info", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO: display help for fixing problems (Toggle bluetooth, reboot)
                        }
                    }).show();
        }
        else if(NearbyManagement.ismIsConnecting() && NearbyManagement.ismIsDiscovering()){
            setErrorIcon();


        }
    }

    /**
     * Set the message of the Nearby status (Discovery and Advertising) to the right color and text
     */
    private void setTextNearbyStatus() {
        if(NearbyManagement.ismIsDiscovering()){
            setTextWithColor(tvDiscovering, getString(R.string.discovering_ok),
                    getResources().getColor(R.color.okColor));
        } else {
            setTextWithColor(tvDiscovering, getString(R.string.discovering_nok),
                    getResources().getColor(R.color.errorColor));
        }

        if(NearbyManagement.ismIsAdvertising()){
            setTextWithColor(tvAdvertising, getString(R.string.advertising_ok),
                    getResources().getColor(R.color.okColor));
        } else {
            setTextWithColor(tvAdvertising, getString(R.string.advertising_nok),
                    getResources().getColor(R.color.errorColor));
        }
    }

    /**
     * Change the text of the TextView
     * @param tv TextView to change
     * @param text text to place in the TextView
     */
    private void setText(TextView tv, String text){
        tv.setText(text);
    }

    /**
     * Change the text and the color of the TextView
     * @param tv TextView to edit
     * @param text text to place in the TextView
     * @param color color to place in the textView
     */
    private void setTextWithColor(TextView tv, String text, int color){
        tv.setText(text);
        tv.setTextColor(color);
    }

    private void setErrorIcon() {
        imError.setImageResource(R.drawable.ic_error_outline_black);
        imError.setColorFilter(this.getResources().getColor(R.color.errorColor));
    }


    /**
     * Make the back button of the action bar behave as the hardware button
     * @param item menu item pressed
     * @return true if ok, false if MenuItem not handled by method
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return false;
    }
}
