package it.uom.group10.journeyrider;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.uom.group10.journeyrider.NetLink.JourneyDB.JRdb;
import it.uom.group10.journeyrider.NetLink.Place;


public class ShowDetailsFragment extends DialogFragment implements View.OnClickListener{
    Activity act;
    double toLat,toLong,fromLat,fromLong;
    Context cont;
    Place x;
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
    x=db.getPlaceDetailsFromLatLong(toLat,toLong);
    TextView txt_pl_name=(TextView)view.findViewById(R.id.txt_placeName);
    txt_pl_name.setText(x.getPlaceName());
    TextView txt_pl_desc=(TextView)view.findViewById(R.id.txt_desc);
    txt_pl_desc.setText(x.getLngDes());
    new FetchImageAsyncTask().execute("http://surangabuilders.vv.si/"+x.getPlaceName()+".jpg");
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

    class FetchImageAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ImageView p=(ImageView)getView().findViewById(R.id.img_place);
            //p.setMaxWidth(l.getWidth());
            File dir=new File("sdcard/Jrider/");
            if(!dir.exists()){
                dir.mkdirs();
            }
            File img=new File("sdcard/Jrider/" + x.getPlaceName() + ".jpg");
            if(img.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile("sdcard/Jrider/" + x.getPlaceName() + ".jpg");
                p.setImageBitmap(bitmap);
            }else{
                Toast.makeText(getActivity().getApplicationContext(),"Sorry No Image available",Toast.LENGTH_LONG).show();
                p.setImageResource(R.drawable.noimage);
            }







            //p.setIma
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            String u;
            HttpURLConnection connection = null;
            try {
                Log.d("Image", sUrl[0]);
                if (!sUrl[0].startsWith("http")) {
                    u = "http://" + sUrl[0];
                }
                else{
                    u= sUrl[0];
                }

                Log.d("Image", u);
                URL url = new URL(u);
                //Log.d("Image", "thumbs1.ebaystatic.com//m//mOR0AuMSi9LLnSjryx8wE3Q//140.jpg");
                Log.d("Image", "con");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                Log.d("Image", "if");
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                Log.d("Image", "save");
                if (x!=null) {
                    output = new FileOutputStream("sdcard/Jrider/"+x.getPlaceName()+".jpg");
                }
                Log.d("Image", "byte");
                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    //if (fileLength > 0) // only if total length is known
                    //publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                Log.d("Image", e.toString());
                e.getStackTrace();
                e.printStackTrace();
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;

        }
    }

}
