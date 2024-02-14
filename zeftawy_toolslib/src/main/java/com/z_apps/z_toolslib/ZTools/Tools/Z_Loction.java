package com.z_apps.z_toolslib.ZTools.Tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.model.value.GeoPointValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;

public class Z_Loction  implements LocationListener
    {

    Location MYlocation;

    public Location getMYlocation() {
       // getLocation_andPermssion() ;


        return MYlocation;
    }

    Location L;
    LocationManager Loman;
    Activity activity;




    public Z_Loction(Context context) {
        this.context = context;
        this.activity= (Activity) context;
    }

    public Z_Loction() {

    }

    Context context;
 ;
    public void getLocation_andPermssion() {

        Loman = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //  اظهار شاشة طلب الاذن
                String s[] = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                activity.requestPermissions( s, 1);


                return;
            } else {

                L = Loman.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                if (L == null) {

                    L = Loman.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);


                    if (L == null) {


                        L = Loman.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (L == null) {

                            LocationListener locationListener = new Z_Loction();
                            Loman.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER, 10, 0, locationListener);
                            if (L == null ) {
                                locationListener = new Z_Loction();
                                Loman.requestLocationUpdates(
                                        LocationManager.PASSIVE_PROVIDER, 10, 0, locationListener);
                                if (L == null) {
                                    locationListener = new Z_Loction();
                                    Loman.requestLocationUpdates(
                                            LocationManager.NETWORK_PROVIDER, 10, 0, locationListener);
                                }
                            }

                        }

                    }
                }

            }
        }
        else {


            L = Loman.getLastKnownLocation(LocationManager.GPS_PROVIDER);



            if (L == null) {

                L = Loman.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);


                if (L == null) {


                    L = Loman.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (L == null) {

                        LocationListener locationListener = new Z_Loction();
                        Loman.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, 5000, 0, locationListener);


                    }

                }
            }


        }

        MYlocation = L ;
    }

    public void  requstpermission (int requestCode , String[] permissions, int[] grantResults){
        switch (requestCode) {

            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation_andPermssion();



                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }


    }


        private GoogleApiClient googleApiClient;
        final static int REQUEST_LOCATION = 199;

public  void onNullLocation(){

    if (googleApiClient == null) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        googleApiClient.connect();
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {

                        Log.d("Location error","Location error " + connectionResult.getErrorCode());
                    }
                }).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult( activity, REQUEST_LOCATION);


                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                }
            }
        });
    }
}


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (L == null) {
                try {
                    Thread.sleep(100);
                    getLocation_andPermssion();
                    Log.i("getinetlocation", "tr ");
                } catch (Exception e) {
                    Log.e("Error " , e.getMessage()) ;
                    getLocation_andPermssion();
                }
                ;
            }
        }
    };
    void getlocationlistner(){


    }

    @Override
    public void onLocationChanged(Location location) {
        MYlocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


        String address;
        String city  ;
        String state ;
        String zip ;
        String country ;

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getZip() {
            return zip;
        }

        public String getCountry() {
            return country;
        }

        public   String  getAddressfromLocation_Details  (long latitude , long longitude) throws IOException {

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        List<Address> addresses  = geocoder.getFromLocation(latitude,longitude, 1);
         address = addresses.get(0).getAddressLine(0);
         city = addresses.get(0).getLocality();
         state = addresses.get(0).getAdminArea();
         zip = addresses.get(0).getPostalCode();
         country = addresses.get(0).getCountryName();

 return  address ;
    }
        public   void getAddressfromLocation_Details  ( ) throws IOException {
            double latitude = MYlocation.getLatitude() ;

            double longitude  = MYlocation.getLongitude();

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            List<Address> addresses  = geocoder.getFromLocation(latitude,longitude, 1);
             address = addresses.get(0).getAddressLine(0);
             city = addresses.get(0).getLocality();
             state = addresses.get(0).getAdminArea();
             zip = addresses.get(0).getPostalCode();
             country = addresses.get(0).getCountryName();
        }



        public  String getAddressfromLocation() throws IOException{

        Geocoder geocoder  ;

            double latitude = MYlocation.getLatitude() ;

            double longitude  = MYlocation.getLongitude();

            geocoder = new Geocoder(context, Locale.getDefault());

            List<Address> addresses  = geocoder.getFromLocation(latitude,longitude, 1);
            address = addresses.get(0).getAddressLine(0);

            return  address;
    }

double Latp1 , Latp3  , Longp2 ,  Longp4 ;

        public double getLatp1() {
            return Latp1;
        }

        public double getLatp3() {
            return Latp3;
        }

        public double getLongp2() {
            return Longp2;
        }

        public double getLongp4() {
            return Longp4;
        }

        public  void getpoints(){

        PointF center = new PointF( Float.parseFloat( getMYlocation().getLatitude() + ""),

                (Float.parseFloat(  getMYlocation().getLongitude() + "")));
        final double mult = 1; // mult = 1.1; is more reliable
        PointF p1 = calculateDerivedPosition(center, mult * 10, 0);
        PointF p2 = calculateDerivedPosition(center, mult * 10, 90);
        PointF p3 = calculateDerivedPosition(center, mult * 10, 180);
        PointF p4 = calculateDerivedPosition(center, mult * 10, 270);
Latp1 = p1.x ;
            Latp3 = p3.x ;
Longp2 = p2.y ;
Longp4 = p4.y ;
        }


        public  void getpoints(double lat , double longe  , int destance){

            PointF center = new PointF( Float.parseFloat(  lat+ ""),

                    (Float.parseFloat(  longe+ "")));
            final double mult = 1; // mult = 1.1; is more reliable
            PointF p1 = calculateDerivedPosition(center,   destance, 0);
            PointF p2 = calculateDerivedPosition(center,  destance, 90);
            PointF p3 = calculateDerivedPosition(center, destance, 180);
            PointF p4 = calculateDerivedPosition(center, destance, 270);
            Latp1 = p1.x ;
            Latp3 = p3.x ;
            Longp2 = p2.y ;
            Longp4 = p4.y ;


            Log.i("latp1 " , Latp1 + "");
            Log.i("longp2 " , Longp2 + "");
            Log.i("latp3 " , Latp3 + "");
            Log.i("longp4 " , Longp4 + "");



        }


        public static PointF calculateDerivedPosition(PointF point,
                                                      double range, double bearing)
        {
            double EarthRadius = 6371000; // m

            double latA = Math.toRadians(point.x);
            double lonA = Math.toRadians(point.y);
            double angularDistance = range / EarthRadius;
            double trueCourse = Math.toRadians(bearing);

            double lat = Math.asin(
                    Math.sin(latA) * Math.cos(angularDistance) +
                            Math.cos(latA) * Math.sin(angularDistance)
                                    * Math.cos(trueCourse));

            double dlon = Math.atan2(
                    Math.sin(trueCourse) * Math.sin(angularDistance)
                            * Math.cos(latA),
                    Math.cos(angularDistance) - Math.sin(latA) * Math.sin(lat));

            double lon = ((lonA + dlon + Math.PI) % (Math.PI * 2)) - Math.PI;

            lat = Math.toDegrees(lat);
            lon = Math.toDegrees(lon);

            PointF newPoint = new PointF((float) lat, (float) lon);

            return newPoint;

        }


    }
