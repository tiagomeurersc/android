package com.example.tiagomeurer.boletimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Avaliacao2Activity extends AppCompatActivity {

    Disciplina disciplina;

    EditText edit_professor, edit_nota;
    Spinner sp_disciplina;
    ArrayList<Disciplina> list_disciplinas;
    ArrayAdapter<Disciplina> adapter_disciplinas;


    public void montarSpinner(){

        list_disciplinas = new ArrayList<>();
        list_disciplinas.add(new Disciplina("Lógica",40));
        list_disciplinas.add(new Disciplina("Desenvolmimento Mobile", 80));
        list_disciplinas.add(new Disciplina("SGBD", 20));
        list_disciplinas.add(new Disciplina("POO", 20));

        adapter_disciplinas = new ArrayAdapter<Disciplina>(this, android.R.layout.simple_spinner_item, list_disciplinas);
        sp_disciplina = (Spinner) findViewById(R.id.sp_disciplina);
        sp_disciplina.setAdapter(adapter_disciplinas);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao1);

        edit_professor = (EditText) findViewById(R.id.edit_professor);
        edit_nota = (EditText) findViewById(R.id.edit_nota);
        sp_disciplina = (Spinner) findViewById(R.id.sp_disciplina);

        montarSpinner();
    }

    public void enviar(){

        String professor = edit_professor.getText().toString();
        Disciplina disciplina = (Disciplina) sp_disciplina.getSelectedItem();
        String nota = edit_nota.getText().toString();

        Intent it = new Intent();
        it.putExtra("pProfessor", professor);
        it.putExtra("pNota", nota);
        it.putExtra("pDisciplina", disciplina);

        setResult(RESULT_OK, it);

        finish();
    }
}
