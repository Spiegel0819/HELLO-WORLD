package com.example.jacob_ke.myapplication;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public final static String EXTRA_MESSAGE = "com.example.jacob_ke.myapplication.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        CharSequence text = "onCreate!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onCreate!!!");
    }
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Context context = getApplicationContext();
        CharSequence text = "onStart!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onStart!!!");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Context context = getApplicationContext();
        CharSequence text = "onRestart!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onRestart!!!");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Context context = getApplicationContext();
        CharSequence text = "onResume!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onResume!!!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Context context = getApplicationContext();
        CharSequence text = "onPause!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onPause!!!");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Context context = getApplicationContext();
        CharSequence text = "onStop!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onStop!!!");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Context context = getApplicationContext();
        CharSequence text = "onDestroy!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onDestroy!!!");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Context context = getApplicationContext();
        CharSequence text = "onSaveInstanceState!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onSaveInstanceState!!!");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Context context = getApplicationContext();
        CharSequence text = "onRestoreInstanceState!!!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
        toast.show();
        Log.v(TAG, "onRestoreInstanceState!!!");
    }
}
