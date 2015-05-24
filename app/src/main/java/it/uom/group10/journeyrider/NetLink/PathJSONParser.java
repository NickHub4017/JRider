package it.uom.group10.journeyrider.NetLink;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by NRV on 5/24/2015.
 */
public class PathJSONParser {

    public List<LatLng> parse(JSONObject jObject) {

        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;
        java.util.List<LatLng> list_main = new List<LatLng>() {
            @Override
            public void add(int i, LatLng latLng) {
                this.add(i,latLng);
            }

            @Override
            public boolean add(LatLng latLng) {
                this.add(latLng);
                return false;
            }

            @Override
            public boolean addAll(int i, Collection<? extends LatLng> latLngs) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends LatLng> latLngs) {
                return false;
            }

            @Override
            public void clear() {
            this.clear();
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> objects) {
                return false;
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public LatLng get(int i) {
                return this.get(i);

            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return this.isEmpty();
            }

            @NonNull
            @Override
            public Iterator<LatLng> iterator() {
                return null;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<LatLng> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<LatLng> listIterator(int i) {
                return null;
            }

            @Override
            public LatLng remove(int i) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> objects) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> objects) {
                return false;
            }

            @Override
            public LatLng set(int i, LatLng latLng) {
                return null;
            }

            @Override
            public int size() {
                return this.size();

            }

            @NonNull
            @Override
            public List<LatLng> subList(int i, int i2) {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

        };

        try {
            jRoutes = jObject.getJSONArray("routes");
            /** Traversing all routes */
            for (int i = 0; i < jRoutes.length(); i++) {
                jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();

                /** Traversing all legs */
                for (int j = 0; j < jLegs.length(); j++) {
                    jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

                    /** Traversing all steps */
                    for (int k = 0; k < jSteps.length(); k++) {
                        String polyline = "";
                        polyline = (String) ((JSONObject) ((JSONObject) jSteps
                                .get(k)).get("polyline")).get("points");
                        java.util.List<LatLng> list = PolyUtil.decode(polyline);


                        /** Traversing all points */
                        for (int l = 0; l < list.size(); l++) {
                            list_main.add(list.get(i));
                        }
                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
return list_main;
    }

    /**
     * Method Courtesy :
     * jeffreysambells.com/2010/05/27
     * /decoding-polylines-from-google-maps-direction-api-with-java
     * */
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }
}