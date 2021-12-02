package com.example.projectth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {
    GridView gridView;
    List<Categories> arrayList;
    FurnitureAdapterGrid furnitureAdapterGrid;
    Utils utils;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        AndroidNetworking.initialize(getActivity());

        gridView = view.findViewById(R.id.gridView);
        arrayList = new ArrayList<>();

        getAPICategories();

        furnitureAdapterGrid = new FurnitureAdapterGrid(getContext(),arrayList);

        gridView.setAdapter(furnitureAdapterGrid);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,CategoriesFragment.newInstance(i)
                );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void getAPICategories(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/ltbaotran/api/main/category", null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i< response.length() ; i++ ){
                        JSONObject jsonObject = response.getJSONObject(i);
                        Categories categories = new Categories();
                        categories.setName(jsonObject.getString("name"));
                        categories.setImage1(jsonObject.getString("image"));
                        arrayList.add(categories);
                    }
                    furnitureAdapterGrid.notifyDataSetChanged();
                    Log.i("APIHelper", arrayList.size()+"") ;
                } catch (JSONException e) {
                    Log.i("APIHelper", e.getMessage()) ;
                }
            }
        }
                , error -> Log.i("APIHelper", error.getMessage()));
        requestQueue.add(jsonArrayRequest);
        Log.i("APIHelper", arrayList.size()+"") ;
    }
}
