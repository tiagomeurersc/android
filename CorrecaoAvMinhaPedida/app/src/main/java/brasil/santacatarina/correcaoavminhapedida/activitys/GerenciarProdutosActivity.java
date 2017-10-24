package brasil.santacatarina.correcaoavminhapedida.activitys;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import brasil.santacatarina.correcaoavminhapedida.R;
import brasil.santacatarina.correcaoavminhapedida.orm.Categoria;
import brasil.santacatarina.correcaoavminhapedida.orm.MyORMLiteHelper;

public class GerenciarProdutosActivity extends AppCompatActivity {

    Dao<Categoria, Integer> categoriaDao;

    ArrayList<Categoria> listCategoria;
    ArrayAdapter<Categoria> adapterCategoria;

    ListView lvCategorias;
    EditText editNome;
    Categoria categoria = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_produtos);
        lvCategorias = (ListView) findViewById(R.id.lvCategorias);
        editNome = (EditText) findViewById(R.id.editNome);

        //Chamar o Dao de categoria
        try {
            categoriaDao = MyORMLiteHelper.getInstance(this).getCategoriaDao();
            //listCategoria = (ArrayList<Categoria>) categoriaDao.queryForAll();
            adapterCategoria = new ArrayAdapter<Categoria>(this,
                    android.R.layout.simple_list_item_1,
                    categoriaDao.queryForAll());
            lvCategorias.setAdapter(adapterCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        lvCategorias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                categoria = adapterCategoria.getItem(position);
                editNome.setText(categoria.getNome());
                Toast.makeText(GerenciarProdutosActivity.this, "Editando "+categoria.toString(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        //Clique curto no listview
        lvCategorias.setOnItemClickListener(cliqueCurto());
    }

    private AdapterView.OnItemClickListener cliqueCurto(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoria = adapterCategoria.getItem(position);
                AlertDialog.Builder alerta= new AlertDialog.Builder(GerenciarProdutosActivity.this);
                alerta.setTitle("Deletando categoria");
                alerta.setMessage("Confirmar exclusão de " + categoria.toString());
                alerta.setNegativeButton("Não", null);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            categoriaDao.delete(categoria);
                            adapterCategoria.remove(categoria);
                            categoria = null;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alerta.show();
            }
        };
    }

    public void salvar(View v) throws SQLException {

        if(categoria==null)
            categoria = new Categoria();

        categoria.setNome(editNome.getText().toString());
        Dao.CreateOrUpdateStatus res = categoriaDao.createOrUpdate(categoria);

        if(res.isCreated()) {
            adapterCategoria.add(categoria);
            Toast.makeText(this, "Cadastrado", Toast.LENGTH_SHORT).show();
        }else {
            adapterCategoria.notifyDataSetChanged();
            Toast.makeText(this, "Editado", Toast.LENGTH_SHORT).show();
        }

        categoria = null;
        editNome.setText("");
    }
}
