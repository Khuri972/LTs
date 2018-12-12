package com.ltsonline.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ltsonline.MainActivity;
import com.ltsonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinishPostDialog extends DialogFragment {

    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.et_price)
    Button etPrice;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    @BindView(R.id.layout)
    LinearLayout layout;

    public FinishPostDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_DialogWhenLarge
        );
    }

    public static FinishPostDialog newInstance() {
        FinishPostDialog frag = new FinishPostDialog();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finish_post, container, false);
        ButterKnife.bind(this,view);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        etPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostAdDialog newFragment = PostAdDialog.newInstance("500", "2", "1");
                newFragment.show(getFragmentManager(), "dialog");
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss();
                Intent i = new Intent(getActivity(), MainActivity.class);
                getContext().startActivity(i);
               /// ElastiqueFileWriter
            }
        });

        return view;
    }
}
