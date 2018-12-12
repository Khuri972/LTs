package com.ltsonline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ltsonline.R;
import com.ltsonline.SellerProfileActivity;
import com.ltsonline.adapter.HomeAdapter;
import com.ltsonline.custom.RecyclerViewPositionHelper;
import com.ltsonline.model.HomeModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CRAFT BOX on 4/11/2018.
 */

public class ProductFragment extends Fragment {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    ArrayList<HomeModel> models = new ArrayList<>();
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    int firstVisibleItem, visibleItemCount, totalItemCount, firstVisibleItem_feed, visibleItemCount_feed, totalItemCount_feed, count = 8, count_feed = 8;
    protected int m_PreviousTotalCount, m_PreviousTotalCount_feed;
    RecyclerViewPositionHelper mRecyclerViewHelper;
    HomeAdapter itemListDataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        ButterKnife.bind(this, view);

        HomeModel da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://i.pinimg.com/originals/54/cf/fc/54cffc04c843c9517be22822230123c0.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("http://catalog.wlimg.com/4/44468/small-images/image-18-21611.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("http://catalog.wlimg.com/4/44468/small-images/image-10-21603.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://i.pinimg.com/originals/54/cf/fc/54cffc04c843c9517be22822230123c0.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("http://catalog.wlimg.com/4/44468/small-images/image-18-21611.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("http://catalog.wlimg.com/4/44468/small-images/image-10-21603.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        da1 = new HomeModel();
        da1.setId("1");
        da1.setPrice("15,0000");
        da1.setName("Indoor furniture pe wicker sofa");
        da1.setImage_path("https://islamgreatreligion.files.wordpress.com/2012/01/allahinmyheart2.jpg");
        da1.setRating("2");
        da1.setType("1");
        models.add(da1);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        itemListDataAdapter = new HomeAdapter(getActivity(), models);
        recycleview.setLayoutManager(staggeredGridLayoutManager);
        recycleview.setAdapter(itemListDataAdapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 12) {

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
