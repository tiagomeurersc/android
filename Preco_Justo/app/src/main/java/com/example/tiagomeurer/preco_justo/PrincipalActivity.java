package com.example.tiagomeurer.preco_justo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    EditText editNome, editValor, editParcelas, editJuros;
    TextView tvNomeProduto, tvValorInicial, tvValorParcelas, tvValorTotal, tvTotalJuros;
    LinearLayout layoutDinamico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Resgatar os campos do formulário
        editNome = (EditText) findViewById(R.id.editNome);
        editValor = (EditText) findViewById(R.id.editValor);
        editParcelas = (EditText) findViewById(R.id.editParcelas);
        editJuros = (EditText) findViewById(R.id.editJuros);


        //Resgatar os textview de resposta
        tvNomeProduto = (TextView) findViewById(R.id.tvNomeProduto);
        tvValorInicial = (TextView) findViewById(R.id.tvValorInicial);
        tvValorParcelas = (TextView) findViewById(R.id.tvValorParcelas);
        tvValorTotal = (TextView) findViewById(R.id.tvValorTotal);
        tvTotalJuros = (TextView) findViewById(R.id.tvTotalJuros);

        //Resgatar o layout para resposta dinâmica
        layoutDinamico = (LinearLayout) findViewById(R.id.layoutResultado);
    }

    public void calcular(View v){
        //Resgatar valores dos campos
        String nomeProduto = editNome.getText().toString();
        if(nomeProduto.equals("")){
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
            editNome.requestFocus();
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(editValor.getText().toString());
            if(valor<0){
                Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                editValor.requestFocus();
                return;
            }
        }catch (Exception e){
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        double qtdParcelas;
        try {
            qtdParcelas = Double.parseDouble(editParcelas.getText().toString());
            if(qtdParcelas<0){
                Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                editParcelas.requestFocus();
                return;
            }
        }catch (Exception e){
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        //calcular parcelas
        double valorParcelas = valor/qtdParcelas;

        //Calcular total juros
        double juros = Double.parseDouble(editJuros.getText().toString());
        juros = valor*juros/1000;

        //Saída de resultado fixo
        tvNomeProduto.setText(getString(R.string.nome_produto)+": " + nomeProduto);
        tvValorInicial.setText(getString(R.string.valorIni)+": "+valor);
        tvValorParcelas.setText(getString(R.string.valorParc)+": "+valorParcelas);
        tvValorTotal.setText(getString(R.string.valorTotal)+": "+(valor+juros));
        tvTotalJuros.setText(getString(R.string.juros)+": "+juros);

        //Chamada do método resultado dinamico
//        resultadoDinamico();

        //Limpar campos
        editNome.setText("");
        editValor.setText("");
        editParcelas.setText("");
        editJuros.setText("");

    }

    public void limparDados(View v){
        editNome.setText("");
        editValor.setText("");
        editParcelas.setText("");
        editJuros.setText("");
        editNome.requestFocus();

        //Limpar os resultados fixos
        tvNomeProduto.setText("");
        tvValorInicial.setText("");
        tvValorParcelas.setText("");
        tvValorTotal.setText("");
        tvTotalJuros.setText("");

        //Limpar resultado dinamico
        layoutDinamico.removeAllViews();

    }
    

}