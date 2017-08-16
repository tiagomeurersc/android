package com.example.tiagomeurer.exemplopassagemdeparametros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvNome, tvIdade, tvVotar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //resgatar elementos do layout resultado
        tvNome = (TextView) findViewById(R.id.tvResNome);
        tvIdade = (TextView) findViewById(R.id.tvResIdade);
        tvVotar = (TextView) findViewById(R.id.tvResVoltar);

        //resgatar os dados enviados
        Bundle param = getIntent().getExtras();
        String nome = param.getString("pNome");
        int idade = param.getInt("pIdade");

        //enviar para o layout os dados
        tvNome.setText(getString(R.string.nome)+ ": "+nome);
        tvIdade.setText(getString(R.string.idade)+": "+idade);
        tvVotar.setText(getString(R.string.questao_pode_votar)+": ");
            if (idade>=16)
                tvVotar.setText(getString(R.string.sim));
            else
                tvVotar.setText(tvVotar.getText()+getString(R.string.nao));
    }

    public void retornar(View v){
        finish();
    }
}
