package com.ltsonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ltsonline.R;
import com.ltsonline.model.GeneralModel;

import java.util.ArrayList;

/**
 * Created by CRAFT BOX on 10/25/2016.
 */

public class GeneralAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<GeneralModel> data = null;
    ArrayList<GeneralModel> arraylist = new ArrayList<>();
    private LayoutInflater inflater1 = null;

    public GeneralAdapter(Context context, ArrayList<GeneralModel> da) {
        this.context = context;
        data = da;
        arraylist.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if (convertView == null) {
            inflater1 = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater1.inflate(R.layout.list_general, null);
        }
        TextView name;
        name = (TextView) vi.findViewById(R.id.general_name);
        name.setText(data.get(position).getName());

        return vi;
    }


}
