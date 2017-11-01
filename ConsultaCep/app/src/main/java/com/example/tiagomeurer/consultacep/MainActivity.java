package com.example.tiagomeurer.consultacep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText etBuscaCep;
    EditText etCepProcurado;
    EditText etLogradouro;
    EditText etComplemento;
    EditText etBairro;
    EditText etLocalidade;
    EditText etUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void consulta (View v) {

        etBuscaCep = (EditText) findViewById(R.id.etCep);
        // Toast.makeText(this, etBuscaCep.getText(), Toast.LENGTH_SHORT).show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://viacep.com.br/ws/" + etBuscaCep.getText() + "/json/",
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] response) {

                        Endereco endereco = new Endereco();

                        String responseString = new String(response);

                        Gson g = new Gson();
                        endereco = g.fromJson(responseString, Endereco.class);

                        etCepProcurado = (EditText) findViewById(R.id.etCepProcurado);
                        etCepProcurado.setText(endereco.getCep());

                        etLogradouro = (EditText) findViewById(R.id.etLogradouro);
                        etLogradouro.setText(endereco.getLogradouro());

                        etComplemento = (EditText) findViewById(R.id.etComplemento);
                        etComplemento.setText(endereco.getComplemento());

                        etBairro = (EditText) findViewById(R.id.etBairro);
                        etBairro.setText(endereco.getBairro());

                        etLocalidade = (EditText) findViewById(R.id.etLocalidade);
                        etLocalidade.setText(endereco.getLocalidade());

                        etUf = (EditText) findViewById(R.id.etUf);
                        etUf.setText(endereco.getUf());

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });



    }


}
