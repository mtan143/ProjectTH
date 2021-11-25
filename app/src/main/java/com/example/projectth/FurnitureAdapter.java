package com.example.projectth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FurnitureAdapter extends BaseAdapter {

    LayoutInflater inflater;
    TextView tvName;
    TextView tvDescription;
    ImageView imgLogo;
    Context context;
    ArrayList<Furniture> arrayList;

    public FurnitureAdapter(Context context, ArrayList<Furniture> list) {
        arrayList = list;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.sub_item_listview, null);

        tvName = view.findViewById(R.id.tvName);
        tvDescription = view.findViewById(R.id.tvDesc);
        tvName.setText(arrayList.get(i).name);
        tvDescription.setText(arrayList.get(i).description);
        imgLogo = view.findViewById(R.id.imgHinh);
        //    imgLogo.setImageBitmap(arrayList.get(i).image);
        imgLogo.setImageResource(arrayList.get(i).intImage);
        return view;
    }



}
