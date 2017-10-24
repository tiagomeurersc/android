package brasil.santacatarina.correcaoavminhapedida.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import brasil.santacatarina.correcaoavminhapedida.modelos.Pedido;
import brasil.santacatarina.correcaoavminhapedida.modelos.Produto;

/**
 * Created by Aluno on 27/09/2017.
 */

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME="minhapedida.db";
    private static final int DATABASE_VERSION = 3;

    private static MyORMLiteHelper mInstance;
    private Dao<Categoria, Integer> categoriaDao=null;
    private Dao<Pedido, Integer> pedidoDao=null;
    private Dao<Produto, Integer> produtoDao=null;

    public MyORMLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyORMLiteHelper getInstance(Context ctx){
        if(mInstance == null)
            mInstance = new MyORMLiteHelper(ctx);

        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Categoria.class);
            TableUtils.createTable(connectionSource, Pedido.class);
            TableUtils.createTable(connectionSource, Produto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Categoria.class, true);
//            TableUtils.dropTable(connectionSource, Pedido.class, true);
            TableUtils.dropTable(connectionSource, Produto.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase, connectionSource);
    }

    public Dao<Categoria, Integer> getCategoriaDao() throws SQLException {
        if(categoriaDao==null){
            categoriaDao = getDao(Categoria.class);
        }
        return categoriaDao;

    }
    public Dao<Pedido, Integer> getPedidoDao() throws SQLException {
        if(categoriaDao==null){
            pedidoDao = getDao(Pedido.class);
        }
        return pedidoDao;

    }

    public Dao<Produto, Integer> getProdutoDao() throws SQLException {
        if(categoriaDao==null){
            produtoDao = getDao(Produto.class);
        }
        return produtoDao;

    }
}
