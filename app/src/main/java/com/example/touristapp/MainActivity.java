package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Transition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    //@TODO Remember Me

    final String TAG = "Tourist-Login";

    ArrayList<User> users = new ArrayList<User>();
    CheckBox rememberMeCheck;
    SharedPreferences sharedPreferences;
    String  userEmailSP;
    String  userPasswordSP;
    boolean checkSP;

    EditText etEmail;
    EditText etPassword;
    String email;
    String password;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // @TODO Delete  this  User test
        users.add(new User("q", "q", false));

        users.add(new User("thanos@gmail.com", "1234", false));
        users.add(new User("wonderwoman@yahoo.com", "abc!!", false));

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        rememberMeCheck = findViewById(R.id.cbRemember);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Recovery SharedPreferences
                SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
                checkSP = prefs.getBoolean("Check", false);
                userEmailSP = prefs.getString("email",null);
                userPasswordSP = prefs.getString("password",null);

                if (checkSP) {
                    //Toast.makeText(getApplicationContext(),"Have Email", Toast.LENGTH_LONG).show();
                    email = etEmail.getText().toString();
                    password = etPassword.getText().toString();
                }
                else {
                   // Toast.makeText(getApplicationContext(),"Need Email", Toast.LENGTH_LONG).show();
                    email = userEmailSP;
                    password =  userPasswordSP;
                }

                boolean find = false;
                int i = 0;
                while (i < users.size() && !find) {
                    if (email.equals(users.get(i).email) && password.equals(users.get(i).password)) {
                        find = true;
                    }
                    i++;
                }
                if (find) {
                    Log.d(TAG, "Successfully Login");
                    String username = email.split(" @")[0];
                    Log.d(TAG, "User " + username);
                    // create an Intent to pass user - Rating
                    Intent intent = new Intent(getApplicationContext(), AttractionList.class);
                    intent.putExtra("u", username);
                    startActivity(intent);
                }
                else {
                    TextView tvMessage = findViewById(R.id.tvErrorMessage);
                    String errorMessage = "User/Password Invalid";
                    tvMessage.setText(errorMessage);
                    etEmail.requestFocus();
                    Toast.makeText(getApplicationContext(),errorMessage, Toast.LENGTH_LONG).show();
                }
            }

        });

        rememberMeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Login rememberMe");
                // Shared preferences
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                Log.d(TAG, "Remember (Email and password) " + email + password);
                sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                rememberMeCheck = (CheckBox)findViewById(R.id.cbRemember);
                checkSP = sharedPreferences.getBoolean("Check", false);
                userEmailSP = sharedPreferences.getString("email", "");
                userPasswordSP = sharedPreferences.getString("password", "");

                SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                boolean checked  = ((CheckBox)rememberMeCheck).isChecked();
                if(checked){
                    Log.d(TAG, "Remember Me Checked: "+true+" Save shared preference.");
                    preferencesEditor.putString("username", email);
                    preferencesEditor.putString("password", password);
                    preferencesEditor.putBoolean("Check",true);
                    Log.d(TAG, "email and password set: " + email + password);
                }else{
                    Log.d(TAG, "Remember Me Unchecked: "+false+"  Save shared preference.");
                    preferencesEditor.putBoolean("Check",false);
                }
                preferencesEditor.apply();
                Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Checked shared preference saved!");

            }
        });
    }
}