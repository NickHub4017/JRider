package it.uom.group10.journeyrider;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class GetAccomadation extends DialogFragment {//In here it will show the all accomodation places
CallTomap callmap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_get_accomadation,container);
        ImageButton btn_f_star=(ImageButton)view.findViewById(R.id.imagefivestarButton);
        ImageButton btn_fo_star=(ImageButton)view.findViewById(R.id.imagefourstarButton);
        ImageButton btn_t_star=(ImageButton)view.findViewById(R.id.imagethreestarButton);
        ImageButton btn_to_star=(ImageButton)view.findViewById(R.id.imagetwostarButton);
        ImageButton btn_n_star=(ImageButton)view.findViewById(R.id.imageNormalButton);
        btn_f_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callmap.onCallMap(5);
                dismiss();
            }
        });
        btn_fo_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callmap.onCallMap(4);
                dismiss();
            }
        });
        btn_t_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callmap.onCallMap(3);
                dismiss();
            }
        });
        btn_to_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callmap.onCallMap(2);
                dismiss();
            }
        });
        btn_n_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callmap.onCallMap(1);
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callmap=(CallTomap)activity;

    }

    interface CallTomap{
        public void onCallMap(int type);
    }

}
