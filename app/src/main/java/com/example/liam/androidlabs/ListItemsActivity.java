package com.example.liam.androidlabs;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class ListItemsActivity extends Activity {
    public static final int CAMERA_REQUEST_CODE = 10;

    private AlertDialog.Builder builder;
    private AlertDialog ad;

    private ImageButton pics;
    private Switch toggle;
    private CheckBox cb;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);


        pics = findViewById(R.id.imageButton);
        toggle = findViewById(R.id.switch4);
        cb = findViewById(R.id.checkBox6);

        pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dispatchTakePictureIntent();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (toggle.isChecked()) {
                    Toast.makeText(ListItemsActivity.this, getString(R.string.checked), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListItemsActivity.this, getString(R.string.notchecked), Toast.LENGTH_LONG).show();
                }
            }

        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb.isChecked()) {
                    builder = new AlertDialog.Builder(ListItemsActivity.this);
                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialog_message); //Add a dialog message to strings.xml

                            builder.setTitle(R.string.dialog_title);
                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                    finish();
                                }
                            });
                            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                     ad.cancel();
                                }
                            });
                            ad = builder.create();
                            ad.show();
            }
        }
        });
    }

    /*private void dispatchTakePictureIntent() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            takePicture();

        } else {
            String[] permissionRequested ={Manifest.permission.CAMERA};
            requestPermissions(permissionRequested, CAMERA_REQUEST_CODE);
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            pics.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            } else
                Toast.makeText(this, getString(R.string.cameraErr), Toast.LENGTH_LONG).show();
        }
    }

    private void takePicture() {
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ListItemsActivity", "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ListItemsActivity", "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ListItemsActivity", "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //onActivityResult(1, 2, takePictureIntent);
        Log.i("ListItemsActivity", "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ListItemsActivity", "In onDestroy()");
    }
}
