package it.uom.group10.journeyrider;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import it.uom.group10.journeyrider.NetLink.JourneyDB.JRdb;
import it.uom.group10.journeyrider.NetLink.Place;


public class ShowDetailsFragment extends DialogFragment implements View.OnClickListener{
    Activity act;
    double toLat,toLong,fromLat,fromLong;
    Context cont;
    public ShowDetailsFragment(){

    }
    public ShowDetailsFragment(Context con){
        cont=con;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_show_details,container);
        Button btn_nav=(Button)view.findViewById(R.id.bn_nav);
        JRdb db=new JRdb(cont);
        Button btn_can=(Button)view.findViewById(R.id.btn_can);
        btn_can.setOnClickListener(this);


        toLat=getArguments().getDouble("toDataLat");
        toLong=getArguments().getDouble("toDataLong");
        fromLat=getArguments().getDouble("fromDataLat");
        fromLong=getArguments().getDouble("fromDataLong");
if(fromLong==0.0 && fromLat==0.0){
    Toast.makeText(getActivity().getApplicationContext(),"You cannot navigate GPS not set yet",Toast.LENGTH_LONG).show();
    btn_nav.setEnabled(false);
}
        else{
    Place x=db.getPlaceDetailsFromLatLong(toLat,toLong);
    TextView txt_pl_name=(TextView)view.findViewById(R.id.txt_placeName);
    txt_pl_name.setText(x.getPlaceName());
    TextView txt_pl_desc=(TextView)view.findViewById(R.id.txt_desc);
    txt_pl_desc.setText(x.getLngDes());

    //Toast.makeText(getActivity().getApplicationContext(),""+toLat+" "+toLong+" "+fromLat+" "+fromLong,Toast.LENGTH_LONG).show();
}
        btn_nav.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        act=activity;
    }


    @Override
    public void onClick(View view) {

        ((OnButtonClickListner) this.getActivity()).onBtnClick(view.getId(),toLat,toLong,fromLat,fromLong);
        this.dismiss();

    }

    public interface OnButtonClickListner{
        public void onBtnClick(int position,double fromLat,double fromLon,double toLat,double toLon);
    }

}
