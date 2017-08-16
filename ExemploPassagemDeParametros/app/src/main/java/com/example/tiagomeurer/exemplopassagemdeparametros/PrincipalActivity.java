package com.example.tiagomeurer.exemplopassagemdeparametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class PrincipalActivity extends AppCompatActivity {

    //Variaveis para resgatar os elementos
    EditText editNome;
    NumberPicker npIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Resgatar o elemento do formulario
        editNome = (EditText) findViewById(R.id.editNome);
        npIdade = (NumberPicker) findViewById(R.id.npIdade);

        //Configurar o numberPicker
        npIdade.setMinValue(0);
        npIdade.setMaxValue(100);
        npIdade.setValue(20);
    }

    public void enviar (View v){
        //resgatar os dados do form
        String nome = editNome.getText().toString();
        int idade = npIdade.getValue();


        //chamar a acivity para o resultado
        Intent it = new Intent(this, ResultadoActivity.class);
        it.putExtra("pNome", nome); //enviando uma String
        it.putExtra("pIdade", idade);

        //Executar a  intent
        startActivity(it);


    }
}
