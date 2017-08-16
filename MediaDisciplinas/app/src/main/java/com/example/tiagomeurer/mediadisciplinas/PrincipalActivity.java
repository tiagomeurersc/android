package com.example.tiagomeurer.mediadisciplinas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class PrincipalActivity extends AppCompatActivity {

    EditText disciplina;
    NumberPicker nota1, nota2, nota3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        disciplina = (EditText) findViewById(R.id.editDisciplina);
        nota1 = (NumberPicker) findViewById(R.id.npNota1);
        nota2 = (NumberPicker) findViewById(R.id.npNota2);
        nota3 = (NumberPicker) findViewById(R.id.npNota3);

        nota1.setMaxValue(10);
        nota1.setValue(5);
        nota1.setMinValue(0);
        nota2.setMaxValue(10);
        nota2.setValue(5);
        nota2.setMinValue(0);
        nota3.setMaxValue(10);
        nota3.setValue(5);
        nota3.setMinValue(0);

    }

    public void enviar (View v){

        String nomeDisciplina = disciplina.getText().toString();
        int num1 = nota1.getValue();
        int num2 = nota2.getValue();
        int num3 = nota3.getValue();

        Intent it = new Intent(this, TelaDisciplina2Activity.class);
        it.putExtra("nomeDisciplina", nomeDisciplina);
        it.putExtra("num1", num1);
        it.putExtra("num2", num2);
        it.putExtra("num3", num3);

        startActivity(it);
    }
}
