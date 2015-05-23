package it.uom.group10.journeyrider;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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


public class HomeActivity extends ActionBarActivity {
    GoogleMap googleMap;
    private DrawerLayout drawerLayout;
    private ListView listview;
    private String[] planets;
    private ActionBarDrawerToggle drawerListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout =(DrawerLayout)findViewById(R.id.drawerLayout);
        planets=getResources().getStringArray(R.array.planets);
        listview =(ListView)findViewById(R.id.drawerList);
        listview.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,planets));

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

        googleMap.addMarker(new MarkerOptions().position(new LatLng(6.9255009,79.9535368)).title("Hi").draggable(true));

        Polyline line = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(6.9255009,79.9535368), new LatLng(6.9455009,79.9735368))
                .width(5)
                .color(Color.RED));


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
                ShowDetailsFragment testfr = new ShowDetailsFragment();
                testfr.setRetainInstance(true);
                testfr.setStyle(testfr.STYLE_NO_TITLE, 0);
                testfr.show(fm,"Showing_details");

                return false;
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
}
