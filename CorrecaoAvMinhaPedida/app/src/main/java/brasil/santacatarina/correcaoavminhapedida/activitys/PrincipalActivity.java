package brasil.santacatarina.correcaoavminhapedida.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import brasil.santacatarina.correcaoavminhapedida.R;
import brasil.santacatarina.correcaoavminhapedida.modelos.MyAdapter;
import brasil.santacatarina.correcaoavminhapedida.modelos.Pedido;

public class PrincipalActivity extends AppCompatActivity {

    ListView lvPedidos;
    ArrayList<Pedido> listPedidos;
    MyAdapter myAdapter;
    TextView tvTotalPagar;
    double total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tvTotalPagar = (TextView) findViewById(R.id.tvTotalPagar);

        lvPedidos = (ListView) findViewById(R.id.lvPedidos);

        listPedidos = new ArrayList<>();
        myAdapter = new MyAdapter(listPedidos, this);
        lvPedidos.setAdapter(myAdapter);

        lvPedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Pedido pedido = (Pedido) myAdapter.getItem(i);

                AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
                alerta.setTitle("Opções clique longo");
                alerta.setMessage(pedido.getItemPedido().toString());

                alerta.setPositiveButton("+1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pedido.addMais1();
                        myAdapter.notifyDataSetChanged();

                        total += pedido.getItemPedido().getValor();
                        atualizarTotal();
                    }
                });

                alerta.setNegativeButton("-1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            if(pedido.getQuantidade()>1) {
                                pedido.removMenos1();
                                myAdapter.notifyDataSetChanged();

                                total -= pedido.getItemPedido().getValor();
                                atualizarTotal();
                            }
                    }
                });

                alerta.setNeutralButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myAdapter.remover(pedido);
                        myAdapter.notifyDataSetChanged();

                        total -= pedido.getSubtotal();
                        atualizarTotal();
                    }
                });

                alerta.show();

                return true;
            }
        });

    }

    public void chamarTela(View v){
        Intent it = new Intent(this, AddProdutoActivity.class);
        startActivityForResult(it, RESULT_FIRST_USER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            Bundle param = data.getExtras();
            Pedido pedido = (Pedido) param.getSerializable("pedido");
            myAdapter.add(pedido);
            myAdapter.notifyDataSetChanged();

            total += pedido.getSubtotal();
            atualizarTotal();
        }
    }

    protected void atualizarTotal(){
        tvTotalPagar.setText("R$"+total);
    }
}
