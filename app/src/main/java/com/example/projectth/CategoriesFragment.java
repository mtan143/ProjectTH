package com.example.projectth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {
    ListView listView;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;
    Utils utils;
    ImageView btnBack;

    public CategoriesFragment() {
    }

    public static CategoriesFragment newInstance(int pos) {
// Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putInt("category", pos);
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        categoriesFragment.setArguments(bundle);
        return categoriesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        listView = view.findViewById(R.id.listView);
        btnBack = view.findViewById(R.id.btnBack);

//        arrayList = utils.getFurnitureFromCategories(bundle.getInt("category"));
        arrayList = new ArrayList<>();
        getAPIFurniture();
        furnitureAdapter = new FurnitureAdapter(getContext(), arrayList);
        listView.setAdapter(furnitureAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,
                                    long l) {
                Utils.furnitureHistory.add(arrayList.get(i));
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), FurnitureDetailActivity.class);
//                intent.putExtra("furniture", arrayList.get(i));
//                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new DashboardFragment()
                );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    public void getAPIFurniture(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/ltbaotran/api/main/furniture", null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i< response.length() ; i++ ){
                        JSONObject jsonObject = response.getJSONObject(i);
                        Furniture furniture = new Furniture();
                        furniture.setId(jsonObject.getInt("furnitureId"));
                        furniture.setName(jsonObject.getString("name"));
                        furniture.setImage1(jsonObject.getString("image"));
                        furniture.setDescription(jsonObject.getString("description"));
                        furniture.setCategoriesID(jsonObject.getInt("categoriesId"));
                        arrayList.add(furniture);
                    }
                    furnitureAdapter.notifyDataSetChanged();
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
