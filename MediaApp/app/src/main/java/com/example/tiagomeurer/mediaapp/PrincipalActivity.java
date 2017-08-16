package com.example.tiagomeurer.mediaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    EditText editNome, editNota1, editNota2;
    TextView tvNome, tvMedia, tvSituacao;
    LinearLayout layoutDinamico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Resgatar os campos do formulário
        editNome = (EditText) findViewById(R.id.editNome);
        editNota1 = (EditText) findViewById(R.id.editNota1);
        editNota2 = (EditText) findViewById(R.id.editNota2);

        //Resgatar os textview de resposta
        tvNome = (TextView) findViewById(R.id.tvNomeAluno);
        tvMedia = (TextView) findViewById(R.id.tvMedia);
        tvSituacao = (TextView) findViewById(R.id.tvSituacao);

        //Resgatar o layout para resposta dinâmica
        layoutDinamico = (LinearLayout) findViewById(R.id.layoutResultado);
    }

    public void calcular(View v){
        //Resgatar valores dos campos
        String nomeAluno = editNome.getText().toString();
        if(nomeAluno.equals("")){
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
            editNome.requestFocus();
            return;
        }

        double nota1;
        try {
            nota1 = Double.parseDouble(editNota1.getText().toString());
            if(nota1<0 || nota1>10){
                Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                editNota1.requestFocus();
                return;
            }
        }catch (Exception e){
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        double nota2;
        try {
            nota2 = Double.parseDouble(editNota2.getText().toString());
            if(nota2<0 || nota2>10){
                Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                editNota2.requestFocus();
                return;
            }
        }catch (Exception e){
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        //Calcular média
        double media = (nota1+nota2)/2;

        //Saída de resultado fixo
        tvNome.setText(getString(R.string.aluno)+": " + nomeAluno);
        tvMedia.setText(getString(R.string.media)+": "+media);
        if(media>=7)
            tvSituacao.setText(getString(R.string.situacao)+": "+getString(R.string.situacao_ap));
        else
            tvSituacao.setText(getString(R.string.situacao)+": "+getString(R.string.situacao_rp));


        //Chamada do método resultado dinamico
        resultadoDinamico();

        //Limpar campos
        editNome.setText("");
        editNota1.setText("");
        editNota2.setText("");
    }

    public void limparDados(View v){
        editNome.setText("");
        editNota1.setText("");
        editNota2.setText("");
        editNome.requestFocus();

        //Limpar os resultados fixos
        tvNome.setText("");
        tvMedia.setText("");
        tvSituacao.setText("");

        //Limpar resultado dinamico
        layoutDinamico.removeAllViews();

    }

    public void resultadoDinamico(){

        //Resgatar valores dos campos
        String nomeAluno = editNome.getText().toString();
        double nota1 = Double.parseDouble(editNota1.getText().toString());
        double nota2 = Double.parseDouble(editNota2.getText().toString());
        double media = (nota1+nota2)/2;

        //Criar textView
        TextView tvNomeDin = new TextView(this);
        TextView tvMediaDin = new TextView(this);
        TextView tvSituacaoDin = new TextView(this);

        //Adicionar textos de saída
        tvNomeDin.setText(getString(R.string.nome_aluno)+": "+nomeAluno);
        tvMediaDin.setText(getString(R.string.media)+": "+media);
        if(media>=7)
            tvSituacaoDin.setText(getString(R.string.situacao)+": "+getString(R.string.situacao_ap));
        else
            tvSituacaoDin.setText(getString(R.string.situacao)+": "+getString(R.string.situacao_rp));

        //Adionar os textViews criados no layoutResultado
        layoutDinamico.addView(tvNomeDin);
        layoutDinamico.addView(tvMediaDin);
        layoutDinamico.addView(tvSituacaoDin);
    }

}