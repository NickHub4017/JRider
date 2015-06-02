package it.uom.group10.journeyrider.NetLink.JourneyDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import it.uom.group10.journeyrider.NetLink.Place;


public class JRdb extends SQLiteOpenHelper {
    Context con;
    public JRdb(Context context) {
        super(context, "tourguide8.db", null, 1);
        con=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



            String cr_plc = "CREATE TABLE place (" +
                    "  placeID INTEGER NOT NULL DEFAULT '0' PRIMARY KEY," +
                    "  placeName VARCHAR(255) DEFAULT NULL," +
                    "  photo VARCHAR(100) NOT NULL," +
                    "  lat FLOAT NOT NULL," +
                    "  long FLOAT NOT NULL," +
                    "  catid INTEGER DEFAULT NULL," +
                    "  shtDes TEXT," +
                    "  lngDes TEXT," +
                    "  hotel_type INTEGER NOT NULL," +
                    "  city_cityID INTEGER NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_plc);


            String cr_twn = "CREATE TABLE town (" +
                    "townid INTEGER NOT NULL PRIMARY KEY," +
                    "  townname VARCHAR(15) NOT NULL," +
                    "  districtid INTEGER NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_twn);

            String cr_categ = "CREATE TABLE category (" +
                    "id INTEGER NOT NULL PRIMARY KEY," +
                    "  catname VARCHAR(15) NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_categ);

            String cr_dist = "CREATE TABLE district (" +
                    "districtid INTEGER NOT NULL PRIMARY KEY," +
                    "  districtname VARCHAR(15) NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_dist);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        //return cursor;
    }

    public String[] getAllCategory(){
        SQLiteDatabase database = this.getReadableDatabase();

        String select_item_cat_Query = "SELECT * FROM category";
        Cursor cursor = database.rawQuery(select_item_cat_Query,null);
        String results[]=new String[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=cursor.getString(cursor.getColumnIndex("catname"));
                i++;
            }while (cursor.moveToNext());
        }
        return results;

    }
    public void insertDummyData(){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put("districtid",1);
        values.put("districtname","Colombo");
        database.insert("district", null, values);
        values.clear();
        values.put("districtid", 2);
        values.put("districtname","Kaluthara");
        database.insert("district", null, values);
        values.clear();
        values.put("districtid", 3);
        values.put("districtname","Rathnapura");
        database.insert("district", null, values);
        values.clear();
        values.put("districtid", 4);
        values.put("districtname","trincomalee");
        database.insert("district",null, values);
        values.clear();



        values.put("townid",1);
        values.put("townname","Mattakkuliya");
        values.put("districtid",1);
        database.insert("town", null, values);
        values.clear();

        values.put("townid",2);
        values.put("townname","Panchikawatte");
        values.put("districtid",1);
        database.insert("town", null, values);
        values.clear();

        values.put("townid",3);
        values.put("townname","athurugiriya");
        values.put("districtid",1);
        database.insert("town", null, values);
        values.clear();

        values.put("townid",4);
        values.put("townname","galleface");
        values.put("districtid",1);
        database.insert("town", null, values);
        values.clear();

        values.put("townid",5);
        values.put("townname","bagathale");
        values.put("districtid",1);
        database.insert("town", null, values);
        values.clear();


        values.put("placeID",1);
        values.put("placeName","Colombo Meseaum");
        values.put("lat",6.910732);
        values.put("long",79.861533);
        values.put("photo","1");
        values.put("catid",1);
        values.put("hotel_type",0);
        values.put("shtDes","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        values.put("lngDes","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        values.put("city_cityID",1);
        database.insert("place", null, values);
        values.clear();


        values.put("placeID",2);
        values.put("placeName","Zoo");
        values.put("lat",6.857087);
        values.put("long",79.873784);
        values.put("photo","1");
        values.put("catid",1);
        values.put("shtDes","ccccccccccc");
        values.put("lngDes","ddddddddddddddddddddddddddddddddddddddddd");
        values.put("hotel_type",0);
        values.put("city_cityID",1);
        database.insert("place", null, values);
        values.clear();


        values.put("placeID",3);
        values.put("placeName","Hilton");
        values.put("lat",6.9322132);
        values.put("long",79.8451044);
        values.put("photo","1");
        values.put("catid",1);
        values.put("shtDes","xxxxxxxxxxxxxx");
        values.put("lngDes","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        values.put("city_cityID",1);
        values.put("hotel_type",5);
        database.insert("place", null, values);
        values.clear();

        values.put("placeID",4);
        values.put("placeName","TajSamudra");
        values.put("lat",6.9226751);
        values.put("long",79.8466305);
        values.put("photo","1");
        values.put("catid",1);
        values.put("shtDes","xxxxxxxxxxxxxx");
        values.put("lngDes","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        values.put("hotel_type",5);
        values.put("city_cityID",1);
        database.insert("place", null, values);
        values.clear();

        values.put("placeID",5);
        values.put("placeName","ShoreByO");
        values.put("lat",6.838283);
        values.put("long",79.863275);
        values.put("photo","1");
        values.put("catid",1);
        values.put("shtDes","xxxxxxxxxxxxxx");
        values.put("lngDes","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        values.put("hotel_type",4);
        values.put("city_cityID",1);
        database.insert("place", null, values);
        values.clear();

        values.put("placeID",6);
        values.put("placeName","La Voile Banche");
        values.put("lat",6.8401372);
        values.put("long",79.8631744);
        values.put("photo","1");
        values.put("catid",1);
        values.put("shtDes","xxxxxxxxxxxxxx");
        values.put("lngDes","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        values.put("hotel_type",4);
        values.put("city_cityID",1);
        database.insert("place", null, values);
        values.clear();





    }


    public String[] getAllDistrict(){
        try {
            insertDummyData();
        }
        catch (Exception e){

        }
        SQLiteDatabase database = this.getReadableDatabase();

        String select_item_dist_Query = "SELECT * FROM district";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);

        String results[]=new String[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=cursor.getString(cursor.getColumnIndex("districtname"));
                i++;
            }while (cursor.moveToNext());
        }
        return results;
    }

    public int getDistID(String districtName){
        SQLiteDatabase database = this.getReadableDatabase();

        String select_item_dist_Query = "SELECT id FROM district where districtname='"+districtName+"'";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        String results[]=new String[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=cursor.getString(cursor.getColumnIndex("id"));
                i++;
            }while (cursor.moveToNext());
        }
        return Integer.parseInt(results[0]);

    }


    public String[] getAllTown(int district){
        SQLiteDatabase database = this.getReadableDatabase();

        String select_item_dist_Query = "SELECT townname FROM town where districtid='"+district+"'";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        String results[]=new String[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=cursor.getString(cursor.getColumnIndex("townname"));
                i++;
            }while (cursor.moveToNext());
        }
        return results;

    }


    public Place[] getAllPlaces(String city){
        SQLiteDatabase database = this.getReadableDatabase();
        int cityID=getCityID(city);
        String select_item_dist_Query = "SELECT * FROM place where city_cityID='"+cityID+"'";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        Place results[]=new Place[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=new Place(cursor.getInt(cursor.getColumnIndex("placeID")),cursor.getString(cursor.getColumnIndex("placeName")),cursor.getString(cursor.getColumnIndex("photo")),
                        cursor.getString(cursor.getColumnIndex("catid")),cursor.getString(cursor.getColumnIndex("shtDes")),cursor.getString(cursor.getColumnIndex("lngDes")),
                        cursor.getInt(cursor.getColumnIndex("city_cityID")),cursor.getDouble(cursor.getColumnIndex("lat")),cursor.getDouble(cursor.getColumnIndex("lon")));
                i++;
            }while (cursor.moveToNext());
        }
        return results;

    }

    public Place[] getAllPlacesFilter(String city,String type){
        SQLiteDatabase database = this.getReadableDatabase();
        int cityID=getCityID(city);
        String select_item_dist_Query = "SELECT * FROM place where city_cityID='"+cityID+"' and catid='"+type+"'";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        Place results[]=new Place[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=new Place(cursor.getInt(cursor.getColumnIndex("placeID")),cursor.getString(cursor.getColumnIndex("placeName")),cursor.getString(cursor.getColumnIndex("photo")),
                        cursor.getString(cursor.getColumnIndex("placeType")),cursor.getString(cursor.getColumnIndex("shtDes")),cursor.getString(cursor.getColumnIndex("lngDes")),
                        cursor.getInt(cursor.getColumnIndex("city_cityID")),cursor.getDouble(cursor.getColumnIndex("lat")),cursor.getDouble(cursor.getColumnIndex("lon")));
                i++;
            }while (cursor.moveToNext());
        }
        return results;

    }


    public String[] getAllPlacesStringArray(String city){
        int cityID=getCityID(city);
        SQLiteDatabase database = this.getReadableDatabase();
        String select_item_dist_Query = "SELECT * FROM place where city_cityID='"+cityID+"'";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        String results[]=new String[cursor.getCount()+1];
        results[0]="Any Place";
        int i=1;
        if(cursor.moveToFirst()){
            do{
                results[i]=cursor.getString(cursor.getColumnIndex("placeName"));
                i++;
            }while (cursor.moveToNext());
        }
        return results;

    }

    public int getCityID(String city){
        SQLiteDatabase database = this.getReadableDatabase();
        String select_item_dist_Query1 = "SELECT cityID FROM city where cityName='"+city+"'";
        Cursor cursor1 = database.rawQuery(select_item_dist_Query1,null);
        int cityID=-1;
        if(cursor1.moveToFirst()){
            cityID=cursor1.getInt(cursor1.getColumnIndex("cityID"));
        }
        return cityID;
    }

    public LatLng[] getSerchplaces(String twn,String place,String cat){
        String select_item_dist_Query;
        SQLiteDatabase database = this.getReadableDatabase();
        if (cat==null && place ==null){
             select_item_dist_Query = "SELECT * FROM place where city_cityID='"+twn+"'";
        }
        else if (cat==null && place!=null){
            select_item_dist_Query="SELECT * FROM place where placeName='"+place+"'";
        }
        else {
            select_item_dist_Query="SELECT * FROM place";
        }

        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
       LatLng[] results=new LatLng[cursor.getCount()];

        int i=1;
        if(cursor.moveToFirst()){
            do{
                results[i]=new LatLng(cursor.getDouble(cursor.getColumnIndex("lat")),cursor.getDouble(cursor.getColumnIndex("long")));

                i++;
            }while (cursor.moveToNext());
        }


        return  results;
    }


    public Place[] getAllAccomodation(int hoteltype){

        SQLiteDatabase database = this.getReadableDatabase();

       String select_item_dist_Query = "SELECT * FROM place where hotel_type="+hoteltype;// where hotel_type ="+hoteltype;
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        //Log.d("JRDB place",""+""+cursor.getCount());
        Place results[]=new Place[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=new Place(cursor.getDouble(cursor.getColumnIndex("lat")),cursor.getDouble(cursor.getColumnIndex("long")),cursor.getString(cursor.getColumnIndex("placeName")));
                i++;

            }while (cursor.moveToNext());
        }
        return results;

    }
}
