package com.example.liam.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.util.Log.i;


public class StartActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thirdIntent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(thirdIntent, 50);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 50) {
            Log.i("StartActivity", "Returned to StartActivity.onActivityResult()");
        }

        if (resultCode == Activity.RESULT_OK){
            String messagePassed = "ListItemActivity passed: " + data.getStringExtra("Response");
            Toast.makeText(this, messagePassed.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("StartActivity", "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        i("StartActivity", "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        i("StartActivity", "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        i("StartActivity", "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        i("StartActivity", "In onDestroy()");
    }


}
