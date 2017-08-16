package com.example.tiagomeurer.mediadisciplinas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class TelaDisciplina2Activity extends AppCompatActivity {
    EditText disciplina;
    NumberPicker nota1, nota2, nota3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_disciplina2);

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
        Bundle param = getIntent().getExtras();
        String nomeDisciplina1 = param.getString("nomeDisciplina");
        int nota1disciplina1 = param.getInt("nota1");
        int nota2disciplina1 = param.getInt("nota2");
        int nota3disciplina1 = param.getInt("nota3");

        String nomeDisciplina = disciplina.getText().toString();
        int num1 = nota1.getValue();
        int num2 = nota2.getValue();
        int num3 = nota3.getValue();
        Intent it = new Intent(this, ResultadoActivity.class);
        // criando o bundle de parametros <chave,valor>
        it.putExtra("nomedisciplina2",nomeDisciplina);
        it.putExtra("num1", num1);
        it.putExtra("num2", num2);
        it.putExtra("num3", num3);
        it.putExtra("nomedisciplina1",nomeDisciplina1);
        it.putExtra("nota1discipina1",nota1disciplina1);
        it.putExtra("nota2disciplina1",nota2disciplina1);
        it.putExtra("nota3disciplina1",nota3disciplina1);

        startActivity(it);
    }
}
