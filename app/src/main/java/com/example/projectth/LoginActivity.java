package com.example.projectth;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin, btnRegister, btnOK, btnCancel;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.name);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        DB=new DBHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty())
                {
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.dialog_cutom);
                    btnOK = dialog.findViewById(R.id.btnOK);
                    btnCancel = dialog.findViewById(R.id.btnCancel);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(LoginActivity.this,
                                    RegisterActivity.class);
                            LaunchSomeActivity.launch(intent);
                            dialog.dismiss();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                }
                else if(edtPassword.getText().toString().length() < 6)
                {
                    edtPassword.setError("Minimum 6 number");
                }
                else
                    {
                        String user=edtUsername.getText().toString();
                        String pass=edtPassword.getText().toString();
                        if (user.equals("")||pass.equals("")) {
                            Toast.makeText(LoginActivity.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Boolean checkuserpass=DB.checkUsernamePassword(user,pass);
                            if(checkuserpass==true)
                            {
                                Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("username",user);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(LoginActivity.this, "Username or password are not correct, please try again ", Toast.LENGTH_SHORT).show();
                        }

                }
//                else {
//                    Intent intent = new Intent(LoginActivity.this,
//                            InfoActivity.class);
//                    intent.putExtra("Username", edtUsername.getText().toString());
//                    startActivity(intent);
//                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                LaunchSomeActivity.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> LaunchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 100)
                    {
                        Intent data = result.getData();
                        edtUsername.setText(data.getStringExtra("username"));
                        edtPassword.setText(data.getStringExtra("password"));
                    }
                }
            }
    );
}