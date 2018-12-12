package com.ltsonline.dialog;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ltsonline.GlobalElements;
import com.ltsonline.R;
import com.ltsonline.adapter.GeneralAdapter;
import com.ltsonline.model.GeneralModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostAdDialog extends DialogFragment {

    @BindView(R.id.img_close)
    ImageView imgClose;

    @BindView(R.id.ll_details)
    LinearLayout llDetails;
    @BindView(R.id.btn_post)
    Button btnAddDetails;

    Unbinder unbinder;
    public static String price, category_id;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.sp_category)
    Spinner spCategory;
    @BindView(R.id.et_price)
    EditText etPrice;

    ArrayList<GeneralModel> data = new ArrayList<>();
    GeneralAdapter homeCategoryAdapter;
    static String type;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;

    public PostAdDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_DialogWhenLarge
        );
        try {
            GlobalElements.setStatusBar(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PostAdDialog newInstance(String price1, String cat_id, String type1) {
        PostAdDialog frag = new PostAdDialog();
        price = price1;
        category_id = cat_id;
        type = type1;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_ad, container, false);

        unbinder = ButterKnife.bind(this, view);
        if (type.equals("1")) {
            llTitle.setVisibility(View.VISIBLE);
        }
        etPrice.setText(price.substring(1) + "");
        LoadData();
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinishPostDialog newFragment = FinishPostDialog.newInstance();
                newFragment.show(getFragmentManager(), "dialog");
            }
        });
        return view;
    }

    private void LoadData() {
        GeneralModel da = new GeneralModel();
        da.setId("1");
        da.setName("Electronic");
        data.add(da);

        da = new GeneralModel();
        da.setId("2");
        da.setName("Furniture");
        data.add(da);

        da = new GeneralModel();
        da.setId("3");
        da.setName("Properties");
        data.add(da);

        da = new GeneralModel();
        da.setId("4");
        da.setName("Jobs");
        data.add(da);

        da = new GeneralModel();
        da.setId("5");
        da.setName("Cars");
        data.add(da);

        da = new GeneralModel();
        da.setId("6");
        da.setName("abc");
        data.add(da);

        da = new GeneralModel();
        da.setId("7");
        da.setName("abc");
        data.add(da);
        int position = 0;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getName() + "");
            if (category_id.equals(data.get(i).getId())) {
                position = i;
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);
        spCategory.setAdapter(adapter);
        spCategory.setSelection(position);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

}
