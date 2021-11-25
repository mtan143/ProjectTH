package com.example.projectth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, pass, repass;
    Button btnSignIn, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.editText1);
        pass = findViewById(R.id.editText2);
        repass = findViewById(R.id.editText3);
        btnSignIn = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        DBHelper DB=new DBHelper(this);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty()){
                    username.setError("Username cannot be null ");
                    return;
                }
                if(pass.getText().toString().isEmpty()){
                    pass.setError("Password required");
                    return;
                }
                if(pass.getText().toString().length() < 6)
                {
                    pass.setError("Minimum 6 number");
                }
                if(repass.getText().toString().isEmpty()){
                    repass.setError("Password required");
                    return;
                }
                String user = username.getText().toString();
                String passw=pass.getText().toString();
                String repassw=repass.getText().toString();

                if(user.equals("")||passw.equals("")||repassw.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (passw.equals(repassw))
                    {
                        Boolean checkUser=DB.checkUsername(user);
                        if (!checkUser)
                        {
                            Boolean insert = DB.insertData(user,passw);
                            if (insert)
                            {
                                Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("usernamesignup",user);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "The account have exist", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}