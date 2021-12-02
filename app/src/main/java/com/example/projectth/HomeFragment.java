package com.example.projectth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView listView;
    ArrayList<Furniture> arrayList;
    HomeAdapter furnitureAdapter;
    Utils utils;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        arrayList = utils.getMockData();
        furnitureAdapter = new HomeAdapter(getContext(),arrayList);
        listView.setAdapter(furnitureAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long
                    l) {
                Utils.furnitureHistory.add(arrayList.get(i));
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),
                        FurnitureDetailActivity.class);
                intent.putExtra("objFurniture",arrayList.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils utils = new Utils(getContext());
        utils.WriteToFileInternal(arrayList);
    }
}
