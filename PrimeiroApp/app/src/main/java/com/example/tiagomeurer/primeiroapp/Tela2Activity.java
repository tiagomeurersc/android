package com.example.tiagomeurer.primeiroapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tela2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
    }


    public void click2(View view) {

        Intent it = new Intent(this, Tela3Activity.class);
        startActivity(it);
    }
}
