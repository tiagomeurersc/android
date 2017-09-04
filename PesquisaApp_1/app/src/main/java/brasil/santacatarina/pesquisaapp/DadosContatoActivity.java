package brasil.santacatarina.pesquisaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DadosContatoActivity extends AppCompatActivity {

    EditText editEmail, editTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_contato);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editTelefone = (EditText) findViewById(R.id.editTelefone);

        Bundle param = getIntent().getExtras();
        Pessoa p = (Pessoa) param.getSerializable("pPessoa");
        editEmail.setText(p.getEmail());
        editTelefone.setText(p.getTelefone());

    }

    public void enviar(View v){
        //Resgatar parâmetros da tela
        String email = editEmail.getText().toString();
        String telefone = editTelefone.getText().toString();

        if(email.equals("") && telefone.equals("")){
            setResult(RESULT_CANCELED);
        }else {
            //Montar intenção sem contexto para empacotar os dados
            Intent it = new Intent();
            it.putExtra("pemail", email);
            it.putExtra("ptelefone", telefone);

            //Enviar dados empacotados para a activity chamadora
            setResult(RESULT_OK, it);

        }

        finish();
    }


}
