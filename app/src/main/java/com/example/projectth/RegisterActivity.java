package com.example.projectth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText  usname, pass, repass;
    Button btnSignIn, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usname = findViewById(R.id.editText1);
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
                if(usname.getText().toString().isEmpty()){
                    usname.setError("Username cannot be null ");
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
                String user = usname.getText().toString();
                String passw=pass.getText().toString();
                String repassw=repass.getText().toString();

                if(user.equals("")||passw.equals("")||repassw.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else
                {
                    if (passw.equals(repassw))
                    {
                        Boolean checkuser=DB.checkusername(user);
                        if (checkuser==false)
                        {
                            Boolean insert = DB.inserData(user,passw);
                            if (insert==true)
                            {
                                Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("usernamesignup",user);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(RegisterActivity.this, "Failled", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "the account have exist", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}