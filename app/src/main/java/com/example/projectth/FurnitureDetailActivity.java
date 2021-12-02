package com.example.projectth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FurnitureDetailActivity extends AppCompatActivity {

    TextView tvTitle, tvDetail;
    ImageView imageView;
    Furniture objFurniture;
    ImageView imBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture_detail);

        tvTitle= findViewById(R.id.tvTitle);
        tvDetail=findViewById(R.id.tvDetail);
        imageView= findViewById(R.id.ivImage);
        imBack= findViewById(R.id.imBack);

        Intent intent = getIntent();

        objFurniture= (Furniture) intent.getSerializableExtra("furniture");

        if (objFurniture!= null)
        {
            tvTitle.setText(objFurniture.name);
            tvDetail.setText(objFurniture.description);
            imageView.setImageResource(objFurniture.intImage);
        }
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
