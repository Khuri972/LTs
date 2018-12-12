package com.ltsonline.dialog;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ltsonline.R;
import com.ltsonline.adapter.ChooseCategoryAdapter;
import com.ltsonline.model.CategoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseCategoryDialog extends DialogFragment {

    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.btn_add_details)
    Button btnAddDetails;
    @BindView(R.id.layout)
    LinearLayout layout;
    Unbinder unbinder;

    ChooseCategoryAdapter homeCategoryAdapter;
    boolean checked = false;
    ArrayList<CategoryModel> data = new ArrayList<>();
    public static String price;
    private int id = 0;

    public ChooseCategoryDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_DialogWhenLarge
        );
    }

    public static ChooseCategoryDialog newInstance(String price1) {
        ChooseCategoryDialog frag = new ChooseCategoryDialog();
        price = price1;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_cat, container, false);
        unbinder = ButterKnife.bind(this, view);
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
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).ischeck()) {
                        id = data.get(i).getId();
                    }
                }
                PostAdDialog newFragment = PostAdDialog.newInstance(price, id + "", "0");
                newFragment.show(getFragmentManager(), "dialog");
            }
        });
        return view;
    }

    private void LoadData() {
        CategoryModel da = new CategoryModel();
        da.setId(1);
        da.setName("Electronic");
        da.setImage_path("");
        da.setcheck(false);
        data.add(da);

        da = new CategoryModel();
        da.setId(2);
        da.setName("Furniture");
        da.setImage_path("");
        da.setcheck(false);
        data.add(da);

        da = new CategoryModel();
        da.setId(3);
        da.setName("Properties");
        da.setImage_path("");
        da.setcheck(false);
        data.add(da);

        da = new CategoryModel();
        da.setId(4);
        da.setName("Jobs");
        da.setImage_path("");
        da.setcheck(false);
        data.add(da);

        da = new CategoryModel();
        da.setId(5);
        da.setName("Cars");
        da.setcheck(false);
        da.setImage_path("");
        data.add(da);

        da = new CategoryModel();
        da.setId(6);
        da.setName("abc");
        da.setImage_path("");
        da.setcheck(false);
        data.add(da);

        da = new CategoryModel();
        da.setId(7);
        da.setName("abc");
        da.setImage_path("");
        da.setcheck(false);
        data.add(da);

        homeCategoryAdapter = new ChooseCategoryAdapter(getActivity(), data, getFragmentManager(), price);
        recycleview.setAdapter(homeCategoryAdapter);
        recycleview.setLayoutManager(new GridLayoutManager(getActivity(), 2));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getActivity().getContentResolver().query(contentURI,
                null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
