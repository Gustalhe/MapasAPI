package com.example.mapas;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //processo assincrono, ou seja, permite fazer outras coisas enquanto o mapa carrega:
        mapFragment.getMapAsync(this);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap; //objeto retornado do metodo onMapReady

        /*muda a exibição do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);*/

        //Variavel para definir um determinado local com lat e long
        LatLng ibirapuera = new LatLng(-23.587097, -46.657635);//Onde é marcado no mapa


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) { //clique curto no mapa

                Toast.makeText(MapsActivity.this,"OnClick.Lat:"+latLng.latitude+"; long:"+latLng.longitude,
                        Toast.LENGTH_SHORT).show();
            //retorna latitude e longitude do ponto que vc clicar
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local Selecionado")
                        .snippet("Descrição")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this,"OnLong.Lat:"+latLng.latitude+"; long:"+latLng.longitude,
                        Toast.LENGTH_SHORT).show();
                //retorna latitude e longitude do ponto que vc clicar
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local Selecionado")
                        .snippet("Descrição")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
            }
        });


        //Adicionar o marcador no mapa da varial que foi inserido a lat e long
        mMap.addMarker(new MarkerOptions().position(ibirapuera).title("Parque Ibirapuera")
                /*.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))*/ //cor do marcador
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.car))); //icone como marcador

        //float de zoom varia de 2.0 até 21.0
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ibirapuera,19));


    }
}