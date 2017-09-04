package brasil.santacatarina.pesquisaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pessoa = new Pessoa();
    }

    public void dados(View v){
        Intent it = null;
        int codRequisicao=0;

        switch (v.getId()){
            case R.id.btnDadosContato:
                it = new Intent(this, DadosContatoActivity.class);
                codRequisicao = Constantes.REQUEST_DADOS_CONTATO;
                it.putExtra("pPessoa", pessoa);
                startActivity(it);
                break;

            case R.id.btnDadosEndereco:
                it = new Intent(this, DadosEnderecoActivity.class);
                codRequisicao = Constantes.REQUEST_DADOS_ENDERECO;
                it.putExtra("pPessoa", pessoa);
                startActivity(it);
                break;

            case R.id.btnDadosPessoais:
                it = new Intent(this, DadosPessoaisActivity.class);
                codRequisicao = Constantes.REQUEST_DADOS_PESSOAIS;
                it.putExtra("pPessoa", pessoa);
                startActivity(it);
                break;

            case R.id.btnFinalizar:
                it = new Intent(this, ResultadoActivity.class);
                it.putExtra("pPessoa", pessoa);
                startActivity(it);
                return;
        }
        if(it != null)
            startActivityForResult(it, codRequisicao);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle param;
        if(resultCode==RESULT_OK){

            if(requestCode==Constantes.REQUEST_DADOS_CONTATO){
                param = data.getExtras();
                pessoa.setEmail(param.getString("pemail"));
                pessoa.setTelefone(param.getString("ptelefone"));

            }else if(requestCode==Constantes.REQUEST_DADOS_ENDERECO){
                param = data.getExtras();
                pessoa.setCep(param.getString("pCep"));
                pessoa.setCidade(param.getString("pCidade"));
                pessoa.setRua(param.getString("pRua"));

            }else if(requestCode==Constantes.REQUEST_DADOS_PESSOAIS){
                param = data.getExtras();
                pessoa.setNome(param.getString("pNome"));
                pessoa.setIdade(param.getInt("pIdade"));
                pessoa.setProfissao(param.getString("pProfissao"));
            }

        }else if(resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Usuário cancelou ou não preencheu.", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, ""+pessoa.toString(), Toast.LENGTH_SHORT).show();
    }
}
