package brasil.santacatarina.correcaoavminhapedida.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;

import brasil.santacatarina.correcaoavminhapedida.R;
import brasil.santacatarina.correcaoavminhapedida.modelos.Pedido;
import brasil.santacatarina.correcaoavminhapedida.modelos.Produto;

public class AddProdutoActivity extends AppCompatActivity {

    ArrayList<Produto> listProdutos;
    Spinner spProduto;
    ArrayAdapter<Produto> adapterProduto;
    NumberPicker npQuantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        listProdutos = new ArrayList<>();
        listProdutos.add(new Produto("Batata frita", 12.00));
        listProdutos.add(new Produto("Peixe", 18.00));
        listProdutos.add(new Produto("Camarão", 30.00));

        spProduto = (Spinner) findViewById(R.id.spProdutos);
        adapterProduto = new ArrayAdapter<Produto>(this, android.R.layout.simple_spinner_item, listProdutos);
        spProduto.setAdapter(adapterProduto);

        npQuantidade = (NumberPicker) findViewById(R.id.npQuantidade);
        npQuantidade.setMinValue(1);
        npQuantidade.setMaxValue(10);

    }


    public void enviar(View v){
        Pedido pedido = new Pedido();
        pedido.setItemPedido((Produto) spProduto.getSelectedItem());
        pedido.setQuantidade(npQuantidade.getValue());

        Intent it = new Intent();
        it.putExtra("pedido", pedido);

        setResult(RESULT_OK, it);
        finish();
    }

    public void gerenciarProdutos(View v){
        Intent it = new Intent(this, GerenciarProdutosActivity.class);
        startActivity(it);

    }
    public void voltar(View v){
        finish();
    }

}
