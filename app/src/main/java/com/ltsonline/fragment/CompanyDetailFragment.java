package com.ltsonline.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ltsonline.CompanyDetailActivity;
import com.ltsonline.GlobalElements;
import com.ltsonline.R;
import com.ltsonline.custom.FontSource;
import com.ltsonline.custom.WorkaroundMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CRAFT BOX on 4/11/2018.
 */

public class CompanyDetailFragment extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.abount_us)
    TextView abountUs;
    @BindView(R.id.contact_us)
    TextView contactUs;
    @BindView(R.id.map_txt)
    TextView mapTxt;
    @BindView(R.id.main_layout)
    LinearLayout mainLayout;
    @BindView(R.id.nestedScrollview)
    NestedScrollView nestedScrollview;
    private GoogleMap map;

    //MapView mMapView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_detail, container, false);
        ButterKnife.bind(this, view);
        //SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        // mapFragment.getMapAsync(this);
        GlobalElements.overrideFonts_P_Nova_Rg_Regular(getActivity(), mainLayout);
        address.setTypeface(FontSource.process(getActivity(), R.raw.proxima_nova_bold));
        contactUs.setTypeface(FontSource.process(getActivity(), R.raw.proxima_nova_bold));
        abountUs.setTypeface(FontSource.process(getActivity(), R.raw.proxima_nova_bold));
        mapTxt.setTypeface(FontSource.process(getActivity(), R.raw.proxima_nova_bold));

        nestedScrollview = (NestedScrollView) view.findViewById(R.id.nestedScrollview);
        SupportMapFragment mMap = ((WorkaroundMapFragment) (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mMap.getMapAsync(this);

        ((WorkaroundMapFragment) (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                nestedScrollview.getParent().requestDisallowInterceptTouchEvent(true);

            }
        });


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            map = googleMap;
            map.getUiSettings().setCompassEnabled(false);
            map.getUiSettings().setMapToolbarEnabled(false);
            if (map != null) {
                LatLng camera = new LatLng(22.287585, 70.768592);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(camera, 18));
                map.animateCamera(CameraUpdateFactory.zoomIn());
                Marker current_marker = map.addMarker(new MarkerOptions().position(camera).title("")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void getReceptionHistory() {
        try {
            final ProgressDialog pd = new ProgressDialog(getActivity());
            pd.setTitle("Please Wait");
            pd.setMessage("Loading");
            pd.setCancelable(true);
            pd.show();
            RequestInterface request = RetrofitClient.getClient().create(RequestInterface.class);

            Call<ResponseBody> call = request.getReceptionHistory(myPreferences.getPreferences(MyPreferences.id),myPreferences.getPreferences(MyPreferences.cmpId), "0");

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        pd.dismiss();
                        String json_response = response.body().string();
                        JSONObject json = new JSONObject(json_response);
                        if (json.getInt("ack") == 1) {
                            JSONArray result_array = json.getJSONArray("result");
                            for (int i = 0; i < result_array.length(); i++) {
                                JSONObject result = result_array.getJSONObject(i);
                                ReceptionModel da = new ReceptionModel();
                                da.setId("" + result.getString("id"));
                                da.setTag("");
                                da.setTag_slug("");
                                da.setLabel("" + result.getString("label_id"));
                                da.setLabel_slug("" + result.getString("label_slug"));
                                da.setTitle("" + result.getString("title"));
                                da.setPerson_name(result.getString("person_name"));
                                da.setCompany_name("" + result.getString("company_name"));
                                da.setMobile_no("" + result.getString("mobile_no"));
                                da.setEmail("" + result.getString("email"));
                                da.setWhome_to_meet("" + result.getString("whom_to_call"));
                                da.setDescription("" + result.getString("reception_detail"));
                                da.setCity(result.getString("city"));
                                da.setType("" + result.getString("type"));
                                da.setDate("");
                                da.setName("" + result.getString("caller_name"));
                                receptionModels.add(da);
                            }
                            adapter.notifyDataSetChanged();
                            recycleview.setVisibility(View.VISIBLE);
                            emptyLayout.setVisibility(View.GONE);
                        } else {
                            recycleview.setVisibility(View.GONE);
                            emptyLayout.setVisibility(View.VISIBLE);
                            emptyText.setText("" + json.getString("ack_msg"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    pd.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
