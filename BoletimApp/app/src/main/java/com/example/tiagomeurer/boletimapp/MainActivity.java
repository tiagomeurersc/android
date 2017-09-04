package com.example.tiagomeurer.boletimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void avaliacao(View v){
        Intent it = new Intent();

        switch (v.getId()){
            case R.id.ava1:
                it = new Intent(this, Avaliacao1Activity.class);
                it.putExtra("av", 1);
                startActivity(it);
                break;
            case R.id.ava2:
                it = new Intent(this, Avaliacao1Activity.class);
                it.putExtra("av", 2);
                startActivity(it);
                return;
        }
    }
}
