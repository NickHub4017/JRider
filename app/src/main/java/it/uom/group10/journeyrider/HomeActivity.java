package it.uom.group10.journeyrider;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.maps.android.PolyUtil;

import it.uom.group10.journeyrider.NetLink.JourneyDB.JRdb;
import it.uom.group10.journeyrider.NetLink.PathJSONParser;
import it.uom.group10.journeyrider.NetLink.Place;


public class HomeActivity extends ActionBarActivity implements ShowDetailsFragment.OnButtonClickListner,LocationListener ,Search_places.comTomap,GetAccomadation.CallTomap{
    GoogleMap googleMap;
    JRdb db=new JRdb(this);
    private DrawerLayout drawerLayout;
    private ListView listview;
    private String[] planets;
    private ActionBarDrawerToggle drawerListner;
    Polyline k;




    LocationManager locationManager;
    static LatLng mypos=new LatLng(0,0);
    static Marker mymrk=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout =(DrawerLayout)findViewById(R.id.drawerLayout);
        planets=getResources().getStringArray(R.array.planets);
        listview =(ListView)findViewById(R.id.drawerList);
        listview.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,planets));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, this);


        drawerListner=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(getApplicationContext(),"Close", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext(), "Open", Toast.LENGTH_LONG).show();
            }
        };

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), planets[i], Toast.LENGTH_LONG).show();
                if(i==1){
                    FragmentManager fm = getFragmentManager();
                    Search_places s_places = new Search_places(getApplicationContext());
                    s_places.setRetainInstance(true);
                    s_places.show(fm,"Serach_place");
                    s_places.setShowsDialog(true);
                    s_places.setStyle(s_places.STYLE_NO_TITLE,0);
                }
                else if (i==2){
                    FragmentManager fm = getFragmentManager();
                    GetAccomadation getAc = new GetAccomadation();
                    getAc.setRetainInstance(true);
                    getAc.show(fm,"Serach_acc");
                    getAc.setShowsDialog(true);
                    getAc.setStyle(getAc.STYLE_NO_TITLE,0);

                }
                else if (i==3){

                }
                else if (i==4){

                }
                else if (i==5){

                }
                else if (i==6){
                    FragmentManager fm = getFragmentManager();
                    AboutUs about = new AboutUs();
                    about.setRetainInstance(true);
                    about.show(fm,"Serach_place");
                    about.setShowsDialog(true);
                    about.setStyle(about.STYLE_NO_TITLE,0);

                }
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });


        drawerLayout.setDrawerListener(drawerListner);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_menu_click);



    try{
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.fragment)).getMap();

        googleMap.addMarker(new MarkerOptions().position(new LatLng(6.9229037,79.8835855)).title("Hi").draggable(true));

        /*Polyline line = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(6.9255009,79.9535368), new LatLng(6.9455009,79.9735368))
                .width(5)
                .color(Color.RED));*/


    }
    catch (Exception e){
        Log.e("mapApp", e.toString());
    }

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

            }
        });


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                FragmentManager fm = getFragmentManager();

                Bundle bundle = new Bundle();
                bundle.putDouble("toDataLat", (double)marker.getPosition().latitude);
                bundle.putDouble("toDataLong",(double)marker.getPosition().longitude);
                bundle.putDouble("fromDataLat",(double)mymrk.getPosition().latitude);
                bundle.putDouble("fromDataLong",(double) mymrk.getPosition().longitude);

// set Fragmentclass Arguments



                ShowDetailsFragment testfr = new ShowDetailsFragment();
                testfr.setArguments(bundle);
                testfr.setRetainInstance(true);
                //

                testfr.setStyle(testfr.STYLE_NO_TITLE, 0);
                testfr.show(fm,"Showing_details");
//https://maps.googleapis.com/maps/api/directions/json?origin=40.722543,%20-73.998585&destination=40.7064,%20-74.0094&waypoints=optimize:true|40.722543,%20-73.998585|40.7064,%20-74.0094&sensor=false
                return true;
            }
        });

    }

    public void markPositions(double x,double y,String name){
        googleMap.addMarker(new MarkerOptions().position(new LatLng(x,y)).title(name).draggable(false));

    }


    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerListner.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (drawerListner.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }




        return super.onOptionsItemSelected(item);
    }

    public void changefragment(){


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListner.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBtnClick(int position,double fromLat,double fromLon,double toLat,double toLon) {
        if(position==R.id.btn_can){
        }
        else if (position==R.id.bn_nav){
            try {
                new ReadTask().execute("https://maps.googleapis.com/maps/api/directions/json?origin="+mymrk.getPosition().latitude+"%2C"+mymrk.getPosition().longitude+"&destination="+toLat+"%2C"+toLon+"&waypoints=optimize:true%7C"+fromLat+"%2C"+fromLon+"%7C"+toLat+"%2C"+toLon+"&sensor=false");
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onLocationChanged(Location location) {
        if (mymrk==null){
            mymrk=googleMap.addMarker(new MarkerOptions().position(mypos).title("me").draggable(false));
            mymrk.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        }
        else  if ((mymrk.getPosition().latitude!=location.getLatitude())&&(mymrk.getPosition().longitude!=location.getLongitude())){
            //mymrk.setPosition(mypos);

                mymrk.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
                mymrk.setTitle("Me");
                mymrk.setDraggable(false);
                mypos = new LatLng(location.getLatitude(), location.getLongitude());
                mymrk.setPosition(mypos);


        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void ComToMap(LatLng[] x) {
        for (int i=0;i<x.length;i++){
            markPositions(x[i].latitude,x[0].longitude,"1");
        }
    }

    @Override
    public void onCallMap(int type) {

        Place[] hotels=db.getAllAccomodation(type);
        //Toast.makeText(getApplicationContext(),type,Toast.LENGTH_LONG).show();
        Log.e("onCallMap",""+type+"  "+hotels.length);
        googleMap.clear();
        try {
            if(mymrk==null){
                Log.d("MymrkClear","NullIF");
            }
            else{
                Log.d("MymrkClear","NullELSE");
            }
            mymrk=null;
        }catch (Exception e){
            Log.d("MymrkClear","Null");
        }
        for (Place x:hotels){

            this.markPositions(x.getLat(),x.getLon(),x.getPlaceName());
        }
    }


    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {

            return GET(url[0]);
        }

        @Override
        protected void onPostExecute(String s) {
           // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            LatLng prvs=null;
            Polyline line;
            try {

                JSONObject k=new JSONObject(s);
                JSONArray jRoutes = k.getJSONArray("routes");
                for (int i = 0; i < jRoutes.length(); i++) {
                    JSONArray jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                    for (int j = 0; j < jLegs.length(); j++) {
                        JSONArray jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");
                        for (int kk = 0; kk < jSteps.length(); kk++) {
                            String polyline = (String) ((JSONObject) ((JSONObject) jSteps
                                    .get(kk)).get("polyline")).get("points");
                            List<LatLng> data=PolyUtil.decode(polyline);

                            for (int c=0;c<data.size();c++){
                                //Toast.makeText(getApplicationContext(),data.get(c).latitude+" "+data.get(c).longitude,Toast.LENGTH_LONG).show();
                                line= googleMap.addPolyline(new PolylineOptions()
                                        .add(new LatLng(data.get(c).latitude,data.get(c).longitude), new LatLng(data.get(c).latitude,data.get(c).longitude))
                                        .width(5)
                                        .color(Color.RED));
                                if(prvs==null){
                                    if((line.getPoints().get(0).latitude!=data.get(c).latitude)&&(line.getPoints().get(0).longitude!=data.get(c).longitude)){
                                        line.remove();
                                    }
                                    markPositions(data.get(c).latitude,data.get(c).longitude,"");
                                    prvs=data.get(c);



                                }

                                else{

                                    line= googleMap.addPolyline(new PolylineOptions()
                                            .add(new LatLng(prvs.latitude,prvs.longitude), new LatLng(data.get(c).latitude,data.get(c).longitude))
                                            .width(5)
                                            .color(Color.RED));
                                            prvs=data.get(c);
                                }
                                if(c==data.size()-1 && i==jRoutes.length()-1 && jLegs.length()-1==j && kk==jSteps.length()-1){
                                    markPositions(data.get(c).latitude,data.get(c).longitude,"");
                                }

                            }

                        }
                    }
                }
}
            catch (JSONException e) {
                e.printStackTrace();
            }



        }

        public String GET(String url){
            InputStream inputStream = null;
            String result = "";
            try {

                // create HttpClient

                HttpClient httpclient = new DefaultHttpClient();

                // make GET request to the given URL
                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

                // receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();

                // convert inputstream to string
                if(inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            return result;
        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;


            return result;

        }



    }



}
