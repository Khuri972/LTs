package com.ltsonline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ltsonline.R;
import com.ltsonline.adapter.PostAdapter;
import com.ltsonline.model.PostModel;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CRAFT BOX on 4/11/2018.
 */

public class PostFragment extends Fragment {

    ArrayList<PostModel> data = new ArrayList<>();
    PostAdapter adapter;

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        ButterKnife.bind(this, view);

        PostModel da = new PostModel();
        for (int i = 0; i < 20; i++) {
            if(i==0)
            {
                da = new PostModel();
                da.setId("");
                da.setTitle("Apple");
                da.setDesc("Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A A1466  (13.3 inch, Silver, 1.35 kg)");
                Random rand = new Random();
                int n = rand.nextInt(5000) + 1;
                da.setPrice("" + n);
                da.setImage_path("https://rukminim1.flixcart.com/image/832/832/jmwch3k0/computer/j/8/c/apple-na-thin-and-light-laptop-original-imaf9ph3tusztfbc.jpeg");
                data.add(da);
            }
            else if(i==2)
            {
                da = new PostModel();
                da.setId("");
                da.setTitle("Alto");
                da.setDesc("Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A A1466  (13.3 inch, Silver, 1.35 kg)");
                Random rand = new Random();
                int n = rand.nextInt(5000) + 1;
                da.setPrice("" + n);
                da.setImage_path("https://rukminim1.flixcart.com/image/832/832/jnamvm80/vehicle-pull-along/c/5/r/5-1965-shelby-cobra-427-s-c-blue-miss-chief-original-imafaygufdjwkaqd.jpeg");
                data.add(da);
            }else if(i==2)
            {
                da = new PostModel();
                da.setId("");
                da.setTitle("Apple");
                da.setDesc("Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A A1466  (13.3 inch, Silver, 1.35 kg)");
                Random rand = new Random();
                int n = rand.nextInt(5000) + 1;
                da.setPrice("" + n);
                da.setImage_path("https://rukminim1.flixcart.com/image/832/832/jmwch3k0/computer/j/8/c/apple-na-thin-and-light-laptop-original-imaf9ph3tusztfbc.jpeg");
                data.add(da);
            }else if(i==3)
            {
                da = new PostModel();
                da.setId("");
                da.setTitle("Apple");
                da.setDesc("Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A A1466  (13.3 inch, Silver, 1.35 kg)");
                Random rand = new Random();
                int n = rand.nextInt(5000) + 1;
                da.setPrice("" + n);
                da.setImage_path("https://rukminim1.flixcart.com/image/832/832/jmwch3k0/computer/j/8/c/apple-na-thin-and-light-laptop-original-imaf9ph3tusztfbc.jpeg");
                data.add(da);
            }else if(i==4)
            {
                da = new PostModel();
                da.setId("");
                da.setTitle("Apple");
                da.setDesc("Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A A1466  (13.3 inch, Silver, 1.35 kg)");
                Random rand = new Random();
                int n = rand.nextInt(5000) + 1;
                da.setPrice("" + n);
                da.setImage_path("https://rukminim1.flixcart.com/image/832/832/jmwch3k0/computer/j/8/c/apple-na-thin-and-light-laptop-original-imaf9ph3tusztfbc.jpeg");
                data.add(da);
            }else if(i==5)
            {
                da = new PostModel();
                da.setId("");
                da.setTitle("Apple");
                da.setDesc("Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A A1466  (13.3 inch, Silver, 1.35 kg)");
                Random rand = new Random();
                int n = rand.nextInt(5000) + 1;
                da.setPrice("" + n);
                da.setImage_path("https://rukminim1.flixcart.com/image/832/832/jmwch3k0/computer/j/8/c/apple-na-thin-and-light-laptop-original-imaf9ph3tusztfbc.jpeg");
                data.add(da);
            }

        }

        adapter = new PostAdapter(getActivity(),data);
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycleview.setAdapter(adapter);

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
