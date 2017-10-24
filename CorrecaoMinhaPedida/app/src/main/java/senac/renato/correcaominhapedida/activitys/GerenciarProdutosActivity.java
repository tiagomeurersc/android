package senac.renato.correcaominhapedida.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import senac.renato.correcaominhapedida.R;
import senac.renato.correcaominhapedida.modelos.Pedido;
import senac.renato.correcaominhapedida.modelos.Produto;
import senac.renato.correcaominhapedida.orm.MyORMLiteHelper;

public class GerenciarProdutosActivity extends AppCompatActivity {
    EditText editNome, editValor;

    Spinner spProdutos;
    ArrayAdapter<Pedido> adapterCategorias;

    ListView lvProdutos;
    ArrayAdapter<Produto> adapterProduto;

    Dao<Pedido, Integer> pedidoDao;
    Dao<Produto, Integer> produtoDao;
    Produto produto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_produtos);
        spProdutos = (Spinner) findViewById(R.id.spProdutos);
        editNome = (EditText) findViewById(R.id.editNome);
        editNome = (EditText) findViewById(R.id.editNome);
        lvProdutos = (ListView) findViewById(R.id.lvPedidos);

        try {
            pedidoDao = MyORMLiteHelper.getInstance(this).getCategoriaDao();
            produtoDao = MyORMLiteHelper.getInstance(this).getProdutoDao();

            //Adicionar os dados da tabela de categorias no spinner
            adapterCategorias = new ArrayAdapter<Pedido>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    pedidoDao.queryForAll());
            spProdutos.setAdapter(adapterCategorias);

            //Adicionar os dados da tabela de produtos no listView
            adapterProduto = new ArrayAdapter<Produto>(this,
                    android.R.layout.simple_list_item_1,
                    produtoDao.queryForAll());
            lvProdutos.setAdapter(adapterProduto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(View v) throws SQLException {
        if(produto==null)
            produto = new Produto();

        produto.setNome(editNome.getText().toString());
//        produto.setValor(editValor.getText().toString());
//        produto.setCategoria((Pedido) spProdutos.getSelectedItem());

        Dao.CreateOrUpdateStatus res = produtoDao.createOrUpdate(produto);

        if(res.isCreated()){
            adapterProduto.add(produto);
            Toast.makeText(this, "Cadastrado", Toast.LENGTH_SHORT).show();
        }else if(res.isUpdated()){
            adapterProduto.notifyDataSetChanged();
            Toast.makeText(this, "Atualizado", Toast.LENGTH_SHORT).show();
        }

        produto = null;
    }
}
