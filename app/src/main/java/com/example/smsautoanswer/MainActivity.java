package com.example.smsautoanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button b;
    public static boolean check = false;
    public static ArrayList<SavedMessage> savedMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.INTERNET,
        };
        b = (Button) findViewById(R.id.button);
        loadData();
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }else{
            b.setText("On");
//            System.out.println(check);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 1000){
////            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
////                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
////            }else{
////                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
////                finish();
////            }
//        }
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    @SuppressLint("ResourceAsColor")
    public void openAndClose(View view){
        if(!check){
            b.setText("Off");
            b.setBackgroundColor(getResources().getColor(R.color.red));
            check = true;
//            System.out.println("Pause");
        }
        else{
            b.setText("On");
            b.setBackgroundColor(getResources().getColor(R.color.green));
            check=false;
//            System.out.println("Continue");
        }
    }
    public void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("contacts", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<SavedMessage>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        savedMessages = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (savedMessages == null) {
            // if the array list is empty
            // creating a new array list.
            savedMessages = new ArrayList<>();
        }
    }
    public void saveData() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(savedMessages);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("contacts", json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

    }
}