package com.example.tiagomeurer.myimc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity {

    protected static final int TIMER_RUNTIME = 10000;
    protected boolean nbActive;
    protected ProgressBar nbProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        nbProgressBar = (ProgressBar) findViewById(R.id.progressBar);
//
//        final Thread timerTread = new Thread() {
//            @Override
//            public void run() {
//                int espera = 0;
//
//                nbActive = true;
//                try (
//                     while(nbActive &&(waited<TIMER_RUNTIME)){
//                    sleep(200);
//                    if (nbActive) {
//                        espera += 200;
//                        updateProgress(espera);
//                    }
//
//                }
//                     ) catch(InterruptedException e){
//
//                }
//                finaly {
//                    onContinue();
//                }
//            }
//        };
//        timerTread.start();
    }



    public void calcular(View view) {

        TextView tvresultado = (TextView) findViewById(R.id.tvResultado);
        EditText edtpeso = (EditText) findViewById(R.id.edtPeso);
        EditText edtaltura = (EditText) findViewById(R.id.edtAltura);

        int peso = Integer.parseInt(edtpeso.getText().toString());
        float altura = Float.parseFloat(edtaltura.getText().toString());

        float resultado = peso / (altura * altura);
        if (resultado < 19) {
            tvresultado.setText("O resultado é de "+ resultado+". Você está abaixo do peso!");
        } else if (resultado > 32) {
            tvresultado.setText("O resultado é de "+ resultado+". Você está acima do peso!");
        } else {
            tvresultado.setText("O resultado é de "+ resultado+". Você está com um peso ótimo!");
        }
    }

    public void limpar (View view){
        TextView tvresultado = (TextView) findViewById(R.id.tvResultado);
        EditText edtpeso = (EditText) findViewById(R.id.edtPeso);
        EditText edtaltura = (EditText) findViewById(R.id.edtAltura);

        edtpeso.setText("");
        edtaltura.setText("");
        tvresultado.setText("");
    }

    public void encontrarAcademia (View v){

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=academia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);



    }
}





