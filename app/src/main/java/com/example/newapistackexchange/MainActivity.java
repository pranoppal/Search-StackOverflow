package com.example.newapistackexchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static String API_REQUEST_URL;
    EditText medittext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overflow_main);
        medittext= (EditText) findViewById(R.id.edittext_query);
        String tagged=medittext.getText().toString();
        API_REQUEST_URL="https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=activity&tagged="+tagged+"&site=stackoverflow";
        findViewById(R.id.search_it_button).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_it_button) {
            Intent intent = new Intent(this, StartOverflowActivity.class);
            intent.putExtra("API",API_REQUEST_URL);
            startActivity(intent);
        }
    }

}
