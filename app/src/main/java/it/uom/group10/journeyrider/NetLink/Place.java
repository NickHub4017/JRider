package it.uom.group10.journeyrider.NetLink;

import java.io.Serializable;

/**
 * Created by NRV on 5/25/2015.
 */
public class Place implements Serializable {
    public Place(double lat, double lon, String placeName) {
        this.lat = lat;
        this.lon = lon;
        this.placeName = placeName;
    }

    public double getLat() {

        return lat;
    }

    public double getLon() {
        return lon;
    }

    public Place(int placeID, String placeName, String photo, String placeType, String shtDes, String lngDes, int city_cityID,  double lat ,double lon) {
        this.placeID = placeID;
        this.placeName = placeName;
        this.photo = photo;
        this.placeType = placeType;
        this.shtDes = shtDes;
        this.lngDes = lngDes;
        this.city_cityID = city_cityID;
        this.lat=lat;
        this.lon=lon;

    }
    int city_cityID;
    int placeID;
    String placeName;
    String photo;
    String placeType;
    String shtDes;
    String lngDes;
    double lat;
    double lon;

    public int getPlaceID() {
        return placeID;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPlaceType() {
        return placeType;
    }

    public String getShtDes() {
        return shtDes;
    }

    public String getLngDes() {
        return lngDes;
    }

    public int getCity_cityID() {
        return city_cityID;
    }



}
