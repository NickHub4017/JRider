package it.uom.group10.journeyrider;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


public class Progress extends DialogFragment {
    progresscom pcm;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        pcm=(progresscom)activity;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_progress,container);
        ProgressBar pbr=(ProgressBar)view.findViewById(R.id.progressBar);
        pbr.setMax(1000);
        for(int i=0;i<1000;i++){
            pbr.setProgress(i);

        }

        //.isjobdone();

        return view;
    }
    interface progresscom{
        public void isjobdone();
    }
}
