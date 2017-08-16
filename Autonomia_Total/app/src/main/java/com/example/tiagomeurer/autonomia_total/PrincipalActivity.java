package com.example.tiagomeurer.autonomia_total;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    EditText carro,km,fuel;
    TextView consumoFinal;
    ArrayList<String> listCarros = new ArrayList<>();
    LinearLayout layoutDinamico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        carro = (EditText) findViewById(R.id.carro);
        km = (EditText) findViewById(R.id.km);
        fuel = (EditText) findViewById(R.id.fuel);
        consumoFinal = (TextView) findViewById(R.id.consumoFinal);
        layoutDinamico = (LinearLayout) findViewById(R.id.layoutFinal);
    }

    public void calcular(View v){
        consumoFinal.setText(" ");
        Double KmCarro =  ( Double.parseDouble( km.getText().toString())  / Double.parseDouble( fuel.getText().toString() ) );
        String nome = carro.getText().toString()+" Km/L "+KmCarro;
        listCarros.add(nome);
        Double KmMedio =  ( Double.parseDouble( km.getText().toString())  / Double.parseDouble( fuel.getText().toString() )  ) / listCarros.size();
        consumoFinal.setText( "Consumo Medio da Frota: "+String.valueOf(KmMedio));
        clear();

        for (int i = 0; i < listCarros.size(); i++) {
            TextView tx = new TextView(this);
            tx.setText(listCarros.get(i).toString());
            layoutDinamico.addView(tx);
        }
        apagar(v);
    }
    private void clear(){
        layoutDinamico.removeAllViews();
    }

    public void apagar(View view) {
        carro.setText("");
        fuel.setText("");
        km.setText("");
    }
}
