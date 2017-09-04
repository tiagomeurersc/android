package brasil.santacatarina.pesquisaapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DadosEnderecoActivity extends AppCompatActivity {

    EditText editCep, editRua, editCidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_endereco);

        editCep = (EditText) findViewById(R.id.editCep);
        editCidade = (EditText) findViewById(R.id.editCidade);
        editRua = (EditText) findViewById(R.id.editRua);

    }

    public void enviar (View v){
        String cep = editCep.getText().toString();
        String cidade = editCidade.getText().toString();
        String rua = editRua.getText().toString();

        if (cep.equals("") && rua.equals("") && cidade.equals("")){
            setResult(RESULT_CANCELED);
        }else{
            //montagem da intent para enviar dados
            Intent it = new Intent();
            it.putExtra("pCep", cep);
            it.putExtra("pRua", rua);
            it.putExtra("pCidade", cidade);

             //enviar dados para a activity camadora:
            setResult(RESULT_OK, it);
        }

        finish();
    }
}
