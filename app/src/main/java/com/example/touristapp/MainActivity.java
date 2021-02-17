package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Transition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //@TODO Remember Me

    final String TAG = "Tourist-Login";

    ArrayList<User> users = new ArrayList<User>();
    SharedPreferences sharedPreferences;
    String  userEmailSP;
    String  userPasswordSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // @TODO Delete  this  User test
        users.add(new User("q", "q", false));

        users.add(new User("thanos@gmail.com", "1234", false));
        users.add(new User("wonderwoman@yahoo.com", "abc!!", false));

    }

    public void loginPressed(View view) {
        Log.d(TAG, "Login started");

        EditText etEmail = findViewById(R.id.etEmail);
        String email = etEmail.getText().toString();

        EditText etPassword = findViewById(R.id.etPassword);
        String password = etPassword.getText().toString();

        // Shared preferences
//        sharedPreferences = getSharedPreferences("LoginPreference", Context.MODE_PRIVATE);
//        CheckBox check = findViewById(R.id.cbRemember);
//
//     //   check.setOnCheckedChangeListener(new View.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(new View.OnClickListener,boolean isChecked) {
//                if (isChecked)
//                {
//                    check.setChecked(isChecked);
//                    Toast.makeText(getApplicationContext(), "checked" , Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    check.setChecked(isChecked);
//                    Toast.makeText(getApplicationContext(), "unChecked", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

//        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                EditText a = (EditText)view;
//                if (hasFocus) {
//                      a.setText("");
//                          //     Toast.makeText(getApplicationContext(), "Got the focus", Toast.LENGTH_LONG).show();
//
//                } else {
//
//                    Toast.makeText(getApplicationContext(), "Lost the focus", Toast.LENGTH_LONG).show();
//
//                }
//
//            }
//
//        });

        boolean find = false;
        int i = 0;
        while (i < users.size() && !find) {
            if (email.equals(users.get(i).email) && password.equals(users.get(i).password)) {
                find = true;
            }
            i++;
        }
        if (find) {
            Intent intent = new Intent(this, AttractionList.class);
            intent.putExtra("email", email);
            Log.d(TAG, "Successfully Login");
            startActivity(intent);
        }
        else {
            TextView tvMessage = findViewById(R.id.tvErrorMessage);
            String errorMessage = "User/Password Invalid";
            tvMessage.setText(errorMessage);
            etEmail.requestFocus();
            Toast.makeText(this,errorMessage, Toast.LENGTH_LONG).show();
           // tvMessage.setVisibility(View.VISIBLE);
           // editview.setError
          //   editText.setError(“Errorr message”);
            //@TODO  Clear email and password
        }
    }
}
