package com.example.tiagomeurer.primeiroapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Tela1Activity extends AppCompatActivity {

    public void click(View view) {

        Intent it = new Intent(this, Tela2Activity.class);
        startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);
        Toast.makeText(this, "Seja bem vindo!", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, R.string.texto1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, R.string.texto1, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "O aplicativo foi encerrado", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Obrigado por voltar!", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBackPressed(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Presionou Voltar");
        alerta.setMessage("Deseja fechar a tela?");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Tela1Activity.this, "Volte logo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alerta.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        alerta.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Passou pelo OnStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Passou pelo onResume", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Passou pelo OnPause", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Passou pelo OnStop", Toast.LENGTH_SHORT).show();

    }
}
