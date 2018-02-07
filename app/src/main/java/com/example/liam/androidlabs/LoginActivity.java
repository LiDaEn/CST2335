package com.example.liam.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    SharedPreferences prefs;
    private EditText Uname;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Uname = findViewById(R.id.etLogin);
//
        prefs = getSharedPreferences("UserInput", Context.MODE_PRIVATE);

        Uname.setText(prefs.getString("UserInput", "email@domain.com"));


        Login = (Button)findViewById(R.id.logbutton);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = prefs.edit();
                //System.out.print("We get here.");
                edit.putString("UserInput", Uname.getText().toString());
                edit.commit();


                Intent secondIntent = new Intent(LoginActivity.this,
                        StartActivity.class);
                //startActivity(secondIntent);
                secondIntent.putExtra("UserInput", Uname.getText().toString());
                startActivityForResult(secondIntent, 275);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LoginActivity", "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LoginActivity", "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LoginActivity", "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LoginActivity", "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LoginActivity", "In onDestroy()");
    }
}
