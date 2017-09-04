package brasil.santacatarina.pesquisaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;

public class DadosPessoaisActivity extends Activity {

    EditText editNome;
    NumberPicker npIdade;
    Spinner spProfissao;
    ArrayList<String> listProfissoes;
    ArrayAdapter<String> adapterProfissoes;

    public void montarSpinner(){
        //Organizando itens a serem mostrados no spinner
        listProfissoes = new ArrayList<>();
        listProfissoes.add(new String("Programador"));
        listProfissoes.add(new String("Professor"));
        listProfissoes.add(new String("Analista"));

        //Montando relacionamento da programação com o layout
        adapterProfissoes = new ArrayAdapter<String>(this,
                                                    android.R.layout.simple_spinner_item,
                                                    listProfissoes);

        //Inserindo lista relacionada com layout dentro do spinner
        spProfissao = (Spinner) findViewById(R.id.spProfissao);
        spProfissao.setAdapter(adapterProfissoes);
    }

    public void montarNumberPicker(){
            npIdade = (NumberPicker) findViewById(R.id.npIdade);
            npIdade.setMinValue(0);
            npIdade.setMaxValue(120);
            npIdade.setValue(15);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        editNome = (EditText) findViewById(R.id.editNome);
        npIdade = (NumberPicker) findViewById(R.id.npIdade);
        spProfissao = (Spinner) findViewById(R.id.spProfissao);

        //Chamada da montagem do spinner
        montarSpinner();


        //Chamada da montagem do NumberPicker
        montarNumberPicker();
    }

    public void enviar (View v){

        String nome = editNome.getText().toString();
        int idade = npIdade.getValue();
        String profissao = (String) spProfissao.getSelectedItem();

        Intent it = new Intent();
        it.putExtra("pNome", nome);
        it.putExtra("pIdade", idade);
        it.putExtra("pProfissao", profissao);

        setResult(RESULT_OK, it);
    finish();

    }


}
