package com.ltsonline.dialog;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ltsonline.GlobalElements;
import com.ltsonline.R;
import com.ltsonline.bottamCameraPick.ImagePickerDemo;
import com.ltsonline.custom.PathUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PriceDialog extends DialogFragment {

    public static Uri uri1;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.layout_img)
    ImageView layoutImg;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.btn_set_price)
    Button btnSetPrice;
    @BindView(R.id.layout)
    LinearLayout layout;

    public PriceDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_DialogWhenLarge
        );
    }

    public static PriceDialog newInstance(Uri uri) {
        PriceDialog frag = new PriceDialog();
        uri1 = uri;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_price_dialog, container, false);
        ButterKnife.bind(this, view);
        try {
            String path = uri1.getPath().replace("external_path", "storage/emulated/0");
            /*Glide.with(this)
                    .load(path)
                    .fitCenter()
                    .into(layoutImg);*/
            File f = new File(path);
            Drawable d = Drawable.createFromPath(f.getAbsolutePath());
            layout.setBackground(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnSetPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPrice.getText().toString().length() > 3) {
                    ChooseCategoryDialog newFragment = ChooseCategoryDialog.newInstance(etPrice.getText().toString());
                    newFragment.show(getFragmentManager(), "dialog");
                    dismiss();
                }
            }
        });

        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 1) {
                    etPrice.setText("₹");
                    Selection.setSelection(etPrice.getText(), etPrice.getText().length());
                    btnSetPrice.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                    btnSetPrice.setEnabled(false);
                } else if (charSequence.length() > 3) {
                    btnSetPrice.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
                    btnSetPrice.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
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
