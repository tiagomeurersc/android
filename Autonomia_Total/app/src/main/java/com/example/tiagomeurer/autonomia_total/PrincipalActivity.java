package com.example.tiagomeurer.autonomia_total;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    EditText editNome, editKm_percorrida, editQuantidade_combustivel;
    TextView tvCarro1, tvCarro2, tvCarro3;
    LinearLayout layoutDinamico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        //Resgatar os campos do formulário
        editNome = (EditText) findViewById(R.id.editNome);
        editKm_percorrida = (EditText) findViewById(R.id.editKm_percorrida);
        editQuantidade_combustivel = (EditText) findViewById(R.id.editQuantidade_combustivel);


        //Resgatar os textview de resposta
        tvCarro1 = (TextView) findViewById(R.id.tvCarro1);
        tvCarro2 = (TextView) findViewById(R.id.tvCarro2);
        tvCarro3 = (TextView) findViewById(R.id.tvCarro3);

        //Resgatar o layout para resposta dinâmica
        layoutDinamico = (LinearLayout) findViewById(R.id.layoutResultado);
    }


    public void calcular(View v) {
        //Resgatar valores dos campos
        String nomeCarro = editNome.getText().toString();
        if(nomeCarro.equals("")){
            Toast.makeText(this, "Informe o nome do carro", Toast.LENGTH_SHORT).show();
            editNome.requestFocus();
            return;
        }

        double kmPercorrida = Double.parseDouble(editKm_percorrida.getText().toString());
        if (kmPercorrida < 0) {
            Toast.makeText(this, "Informe a quilometragem percorrida", Toast.LENGTH_SHORT).show();
            editKm_percorrida.requestFocus();
            return;
        }

        double qtdCombustivel = Double.parseDouble(editQuantidade_combustivel.getText().toString());
        if (qtdCombustivel < 0) {
            Toast.makeText(this, "Informe a quantidade de combustivel", Toast.LENGTH_SHORT).show();
            editQuantidade_combustivel.requestFocus();
            return;
        }

        double consumo = kmPercorrida / qtdCombustivel;

        //Saída de resultado fixo
        tvCarro1.setText(getString(R.string.nome_carro)+ ": "+nomeCarro+ ". Consumo: " +consumo+ " km/l");
        tvCarro2.setText(getString(R.string.nome_carro)+ ": "+nomeCarro+ ". Consumo: " +consumo+ " km/l");
        tvCarro3.setText(getString(R.string.nome_carro)+ ": "+nomeCarro+ ". Consumo: " +consumo+ " km/l");


    }
}

