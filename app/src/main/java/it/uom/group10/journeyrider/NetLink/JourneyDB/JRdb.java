package it.uom.group10.journeyrider.NetLink.JourneyDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import it.uom.group10.journeyrider.NetLink.Place;

/**
 * Created by NRV on 5/24/2015.
 */
public class JRdb extends SQLiteOpenHelper {
    public JRdb(Context context) {
        super(context, "tourguide3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            Log.d("Tables", "0");
            String cr_accom = "CREATE TABLE accomadation (" +
                    "APlaceID INTEGER  NOT NULL PRIMARY KEY," +
                    "APlaceName VARCHAR(255) DEFAULT NULL," +
                    "photo VARCHAR(100) NOT NULL," +
                    "Description TEXT," +
                    "city_cityID INTEGER  NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_accom);
            Log.d("Tables", "1");

            String cr_city = "CREATE TABLE city (" +
                    "  cityID INTEGER NOT NULL PRIMARY KEY," +
                    "  cityName VARCHAR(45) DEFAULT NULL," +
                    "  lat FLOAT NOT NULL," +
                    "  long FLOAT NOT NULL," +
                    "  did INTEGER NOT NULL" +//changed here addede district field
                    " )";
            Log.d("Tables", "2");
            sqLiteDatabase.execSQL(cr_city);


            String cr_enter = "CREATE TABLE entertaintment (" +
                    "ID INTEGER NOT NULL PRIMARY KEY," +
                    "  EntertaintmentType VARCHAR(100) NOT NULL," +
                    "  photo VARCHAR(100) NOT NULL," +
                    "  Link VARCHAR(100) NOT NULL," +
                    "  Description TEXT NOT NULL" +
                    ") ";
            sqLiteDatabase.execSQL(cr_enter);

            String cr_guide = "CREATE TABLE guideinfo (" +
                    "  GID INTEGER NOT NULL PRIMARY KEY," +
                    "  GName VARCHAR(255) DEFAULT NULL," +
                    "  photo VARCHAR(100) NOT NULL," +
                    "  Category VARCHAR(100) NOT NULL," +
                    "  RegistrationNo VARCHAR(100) NOT NULL," +
                    "  TelephoneNo VARCHAR(100) NOT NULL," +
                    "  Address VARCHAR(255) NOT NULL," +
                    "  city_cityID INTEGER NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_guide);

            String cr_plc = "CREATE TABLE place (" +
                    "  placeID INTEGER NOT NULL DEFAULT '0' PRIMARY KEY," +
                    "  placeName VARCHAR(255) DEFAULT NULL," +
                    "  photo VARCHAR(100) NOT NULL," +
                    "  lat FLOAT NOT NULL," +
                    "  long FLOAT NOT NULL," +
                    "  placeType VARCHAR(255) DEFAULT NULL," +
                    "  shtDes TEXT," +
                    "  lngDes TEXT," +
                    "  city_cityID INTEGER NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_plc);

            String cr_trv = "CREATE TABLE travelinfo (" +
                    "  SerID INTEGER NOT NULL PRIMARY KEY," +
                    "  SerName VARCHAR(255) DEFAULT NULL," +
                    //"  Photo blob," +
                    "  SerDes TEXT," +
                    "  city_cityID INTEGER NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_trv);

            String cr_user = "CREATE TABLE user (" +
                    "id INTEGER NOT NULL PRIMARY KEY," +
                    "  username VARCHAR(15) NOT NULL," +
                    "  password VARCHAR(32) NOT NULL," +
                    "  first_name VARCHAR(40) NOT NULL," +
                    "  last_name VARCHAR(40) NOT NULL," +
                    "  email VARCHAR(50) NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_user);

            String cr_categ = "CREATE TABLE category (" +
                    "id INTEGER NOT NULL PRIMARY KEY," +
                    "  name VARCHAR(15) NOT NULL" +
                    ")";
            sqLiteDatabase.execSQL(cr_categ);

            String cr_dist = "CREATE TABLE district (" +
                    "id INTEGER NOT NULL PRIMARY KEY," +
                    "  did VARCHAR(15) NOT NULL" +
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
                results[i]=cursor.getString(cursor.getColumnIndex("name"));
                i++;
            }while (cursor.moveToNext());
        }
        return results;

    }
    public void insertDummyData(){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put("id",1);
        values.put("did","Colombo");
        database.insert("district", null, values);
        values.clear();
        values.put("id", 2);
        values.put("did","Kaluthara");
        database.insert("district", null, values);
        values.clear();
        values.put("id", 3);
        values.put("did","Rathnapura");
        database.insert("district", null, values);
        values.clear();
        values.put("id", 4);
        values.put("did","trincomalee");
        database.insert("district",null, values);
        values.clear();




        values.put("cityID",1);
        values.put("cityName","Mattakkuliya");
        values.put("lat",6.9215479);
        values.put("long",79.8563022);
        values.put("did",1);
        database.insert("city", null, values);
        values.clear();

        values.put("cityID",2);
        values.put("cityName","Panchikawatte");
        values.put("lat",6.9370156);
        values.put("long",79.8648315);
        values.put("did",1);
        database.insert("city", null, values);
        values.clear();

        values.put("cityID",3);
        values.put("cityName","athurugiriya");
        values.put("lat",6.8734215);
        values.put("long",79.9981405);
        values.put("did",1);
        database.insert("city", null, values);
        values.clear();

        values.put("cityID",3);
        values.put("cityName","athurugiriya");
        values.put("lat",6.8734215);
        values.put("long",79.9981405);
        values.put("did",1);
        database.insert("city", null, values);
        values.clear();

        values.put("cityID",3);
        values.put("cityName","athurugiriya");
        values.put("lat",6.8734215);
        values.put("long",79.9981405);
        values.put("did",1);
        database.insert("city", null, values);
        values.clear();

        values.put("cityID",3);
        values.put("cityName","athurugiriya");
        values.put("lat",6.8734215);
        values.put("long",79.9981405);
        values.put("did",1);
        database.insert("city", null, values);
        values.clear();



        values.put("placeID",1);
        values.put("placeName","Colombo Meseaum");
        values.put("lat",6.910732);
        values.put("long",79.861533);
        values.put("photo","1");
        values.put("placeType",1);
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
        values.put("placeType",1);
        values.put("shtDes","ccccccccccc");
        values.put("lngDes","ddddddddddddddddddddddddddddddddddddddddd");
        values.put("city_cityID",1);
        database.insert("place", null, values);
        values.clear();


        values.put("placeID",3);
        values.put("placeName","Hilton");
        values.put("lat",6.9322132);
        values.put("long",79.8451044);
        values.put("photo","1");
        values.put("placeType",1);
        values.put("shtDes","xxxxxxxxxxxxxx");
        values.put("lngDes","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
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
                results[i]=cursor.getString(cursor.getColumnIndex("did"));
                i++;
            }while (cursor.moveToNext());
        }
        return results;
    }

    public int getDistID(String districtName){
        SQLiteDatabase database = this.getReadableDatabase();

        String select_item_dist_Query = "SELECT id FROM district where did='"+districtName+"'";
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

        String select_item_dist_Query = "SELECT cityName FROM city where did='"+district+"'";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        String results[]=new String[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=cursor.getString(cursor.getColumnIndex("cityName"));
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
                        cursor.getString(cursor.getColumnIndex("placeType")),cursor.getString(cursor.getColumnIndex("shtDes")),cursor.getString(cursor.getColumnIndex("lngDes")),
                        cursor.getInt(cursor.getColumnIndex("city_cityID")));
                i++;
            }while (cursor.moveToNext());
        }
        return results;

    }

    public Place[] getAllPlacesFilter(String city,String type){
        SQLiteDatabase database = this.getReadableDatabase();
        int cityID=getCityID(city);
        String select_item_dist_Query = "SELECT * FROM place where city_cityID='"+cityID+"' and placeType='"+type+"'";
        Cursor cursor = database.rawQuery(select_item_dist_Query,null);
        Place results[]=new Place[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                results[i]=new Place(cursor.getInt(cursor.getColumnIndex("placeID")),cursor.getString(cursor.getColumnIndex("placeName")),cursor.getString(cursor.getColumnIndex("photo")),
                        cursor.getString(cursor.getColumnIndex("placeType")),cursor.getString(cursor.getColumnIndex("shtDes")),cursor.getString(cursor.getColumnIndex("lngDes")),
                        cursor.getInt(cursor.getColumnIndex("city_cityID")));
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
}
