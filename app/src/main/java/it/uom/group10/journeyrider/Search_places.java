package it.uom.group10.journeyrider;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import it.uom.group10.journeyrider.NetLink.JourneyDB.JRdb;

public class Search_places extends DialogFragment {
    String selected_dist=null;
    String selected_twn;
    AutoCompleteTextView ac_twn;
    AutoCompleteTextView ac_place;
    AutoCompleteTextView ac_distr;
    AutoCompleteTextView ac_cat;
    Context con;
    comTomap com;
    String[] citys= null;
    JRdb db;
    public Search_places(Context conin) {
        con=conin;
        db=new JRdb(con);


    }
    public Search_places() {

    }
    String sel_dis=null;
    String sel_twn=null;
    String sel_cat=null;
    String sel_plac=null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        com=(comTomap)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_search_places,container);
        ac_distr=(AutoCompleteTextView)view.findViewById(R.id.auto_distr);
        ac_cat=(AutoCompleteTextView)view.findViewById(R.id.auto_cat);
        ac_twn=(AutoCompleteTextView)view.findViewById(R.id.auto_twn);
        ac_place=(AutoCompleteTextView)view.findViewById(R.id.auto_plce);

        ac_twn.setDropDownBackgroundResource(R.color.abc_search_url_text_pressed);
        ac_distr.setDropDownBackgroundResource(R.color.abc_search_url_text_pressed);
        ac_place.setDropDownBackgroundResource(R.color.abc_search_url_text_pressed);
        ac_cat.setDropDownBackgroundResource(R.color.abc_search_url_text_pressed);

        ArrayAdapter adp=new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,db.getAllCategory());
        ac_cat.setAdapter(adp);
        ac_cat.setThreshold(1);
        final String[] dists=db.getAllDistrict();

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,dists);
        ac_distr.setAdapter(adapter);

        ac_distr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected_dist=dists[i];
                citys=db.getAllTown(db.getDistID((String)ac_distr.getAdapter().getItem(i)));
                sel_dis=dists[i];
                ArrayAdapter adapter_ac_twn = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,citys);
                ac_twn.setAdapter(adapter_ac_twn);

            }
        });


        ac_twn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               selected_twn =(String)ac_twn.getAdapter().getItem(i);
              //  Toast.makeText(getActivity().getApplicationContext(),selected_twn+" "+db.getAllPlacesStringArray(selected_twn).length,Toast.LENGTH_LONG).show();
                ArrayAdapter adapter_ac_plc = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,db.getAllPlacesStringArray(selected_twn));
                //sel_twn=
               ac_place.setAdapter(adapter_ac_plc);
            }
        });




        ac_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter adapter3 = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,db.getAllPlacesFilter(selected_twn,(String)ac_cat.getAdapter().getItem(i)));
                ac_place.setAdapter(adapter3);

            }
        });
        Button srch=(Button) view.findViewById(R.id.button2);
        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.ComToMap(ac_distr.getText().toString(),ac_twn.getText().toString(),ac_cat.getText().toString(),ac_place.getText().toString());
                dismiss();
            }
        });



        return view;
    }
    interface comTomap{
        void ComToMap(String dist,String twn,String cat,String pla);
    }

    }



