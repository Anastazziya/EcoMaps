package com.example.ecomap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener
{
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest request;
    private Location LastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private double latitude, longitude;

   // private  String nearbyPlace;
    private int n;
    ArrayList<LatLng>arrayList = new ArrayList<LatLng>();
    LatLng CentralLibrary = new LatLng(46.834783074340805, 29.628306499999997);
    LatLng CentralMail = new LatLng(46.83635507434484,29.624515499999994);
    LatLng Theater = new LatLng(46.83708857437312,29.633534499999982);
    LatLng DentalClinic = new LatLng(46.835042074341445,29.630013499999954);
    LatLng YouthCenter = new LatLng(46.83401857436523,29.617355999999994);
    LatLng Furshet = new LatLng(46.83485107434097,29.6191885);
    LatLng StateCommunications = new LatLng(46.83612707434423,29.595940500000008);
    LatLng Monarh = new LatLng(46.833611574364156,29.624461499999935);
    LatLng Adrenaline = new LatLng(46.833611574364156,29.624461499999935);
    LatLng TransnistrianNewspaper = new LatLng(46.83561557436932,29.622700999999907);

    LatLng JKH = new LatLng(46.82602207437099,29.480551500000004);
    LatLng  PointOfReception = new LatLng(46.83739107434748,29.471101499999914);
    LatLng  PointReception= new LatLng(46.802760074363846,29.470346999999997);

    LatLng Tera = new LatLng(46.83739107434748,29.471101499999914);
    LatLng KKKI = new LatLng(47.01847957431158,28.89426599999998);

    ArrayList<String> title = new ArrayList<String>();

    //ArrayList<LatLng> listPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       // listPoints = new ArrayList<>();

        arrayList.add(CentralLibrary);
        arrayList.add(CentralMail);
        arrayList.add(Theater);
        arrayList.add(DentalClinic);
        arrayList.add(YouthCenter);
        arrayList.add(Furshet);
        arrayList.add(StateCommunications);
        arrayList.add(Monarh);
        arrayList.add(Adrenaline);
        arrayList.add(TransnistrianNewspaper);

        arrayList.add(JKH);
        arrayList.add(PointOfReception);
        arrayList.add(PointReception);

        arrayList.add(Tera);
        arrayList.add(KKKI);

        title.add("Центральная библиотека");
        title.add("Почта");
        title.add("Театр");
        title.add("Цтоматологическая поликлиника");
        title.add("Молодёжный центр");
        title.add("Фуршет");
        title.add("Государственная служба связи");
        title.add("ТЦ Монарх");
        title.add("Фитнес-зал «Адреналин»");
        title.add("ГУ «Приднестровская газета»");

        title.add("Жилищно коммунальное хозяйство");
        title.add("Пункт приёма ламп");
        title.add("Пункт приёма ламп");

        title.add("Тера");
        title.add("Кишиневский Комбинат Картонных Изделий");
    }


    public void onClick(View v)
    {
        String battery = "Battery", lamp="Lamp", paper ="Paper", all = "Всё";
        Object transferData[] = new Object[2];
        GetNearByPlaces getNearByPlaces =new GetNearByPlaces();

        switch (v.getId())
        {
            case R.id.search_address:
                EditText addressField = (EditText) findViewById(R.id.location_search);
                String address = addressField.getText().toString();

                List<Address> addressList = null;
                MarkerOptions userMarkerOptions = new MarkerOptions();

                if (!TextUtils.isEmpty(address))
                {
                    Geocoder geocoder = new Geocoder(this);
                    try
                    {
                        addressList = geocoder.getFromLocationName(address, 6);

                        if(addressList != null)
                        {
                            for(int i=0; i<addressList.size(); i++)
                            {
                                Address userAddress = addressList.get(i);
                                LatLng latLng = new LatLng(userAddress.getLatitude(),userAddress.getLongitude());

                                userMarkerOptions.position(latLng);
                                userMarkerOptions.title(address);
                                userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                mMap.addMarker(userMarkerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng((latLng)));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                        else
                        {
                            Toast.makeText(this, "Локация не найдена",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(this, "Пожалуйста укажите локацию",Toast.LENGTH_SHORT).show();
                }
                break;



            case R.id.battery_nearby:
                mMap.clear();
                Toast.makeText(this, "Поиск пунктов приёма батареек", Toast.LENGTH_SHORT).show();
               for (int i =0; i<10;i++)
               {
                   for (int j = 0; j<10; j++)
                   {
                       mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))));
                   }
                   mMap.animateCamera(CameraUpdateFactory.zoomTo(30));
                   mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
               }

                break;

            case R.id.lamp_nearby:
                mMap.clear();
                Toast.makeText(this, "Поиск пунктов приёма ламп", Toast.LENGTH_SHORT).show();
                for (int i =10; i<13;i++)
                {
                    for (int j=10; j<13;j++)
                    {
                        mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                    }
                }
                break;

            case R.id.paper_nearby:
                mMap.clear();
                Toast.makeText(this, "Поиск пунктов приёма бумаги", Toast.LENGTH_SHORT).show();
                for (int i =13; i<arrayList.size();i++)
                {
                    for (int j=13; j<title.size();j++)
                    {
                        mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                    }
                }
                break;

            case R.id.all_nearby:
                mMap.clear();
                for (int i =0; i<arrayList.size();i++)
                {
                    for (int j=0; j<title.size();j++)
                    {
                        mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(30));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                    }
                }
                Toast.makeText(this, "Показать все", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
           if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
           {
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
           }
           else
           {
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
           }

           return false;
        }
        else
        {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if ( googleApiClient == null )
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Сбой подключения...",Toast.LENGTH_SHORT).show();
                }
                return;
        }
        /*mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                if(listPoints.size()==2)
                {
                    listPoints.clear();
                    mMap.clear();
                }
                listPoints.add(LatLng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(LatLng);

                if (listPoints.size() == 1)
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                else
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                mMap.addMarker(markerOptions);

            }
        });*/
    }

    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location)
    {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LastLocation = location;
        if(currentUserLocationMarker != null)
        {
            currentUserLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Вы здесь"); //Текущее местоположение пользователя
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        currentUserLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng((latLng)));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));
        if (googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        LocationRequest request = new LocationRequest();
        request .setInterval(1100);
        request .setFastestInterval(1100);
        request .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, request , this);
        }

    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }
}
