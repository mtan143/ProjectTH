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
import java.util.List;

public class FurnitureAdapterGrid extends BaseAdapter {

    LayoutInflater inflater;
    TextView tvName;
    ImageView imvCategories;
    Context context;
    List<Categories> arrayList;

    public FurnitureAdapterGrid(Context context, List<Categories> list) {
        // arraylist chưa có
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
    public View getView(int i, View contentView, ViewGroup viewGroup) {

        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.sub_item_gridview, null);
        // Thiết lập thông tin hiển thị
        tvName = view.findViewById(R.id.tvName);
        tvName.setText(arrayList.get(i).name);
        imvCategories = view.findViewById(R.id.imvIcon);
        imvCategories.setImageBitmap(Furniture.convertStringToBitmapFromAccess(context, arrayList.get(i).getImage1()));
        return view;
    }
}
