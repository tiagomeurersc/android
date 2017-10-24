package senac.renato.correcaominhapedida.modelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import senac.renato.correcaominhapedida.R;


public class MyAdapter extends BaseAdapter {

    private ArrayList<Pedido> listPedidos;
    private LayoutInflater inflater;

    public MyAdapter(ArrayList<Pedido> pedidos, Context context) {
        this.listPedidos = pedidos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listPedidos.size();
    }

    @Override
    public Object getItem(int i) {
        return listPedidos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void add(Pedido p){
        listPedidos.add(p);
    }

    public void remover(Pedido p){
        listPedidos.remove(p);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Pedido pedido = listPedidos.get(i);
        view = inflater.inflate(R.layout.item_comanda, null);

        TextView tvQuantidade = (TextView) view.findViewById(R.id.tvQuantidade);
        TextView tvNomeProduto = (TextView) view.findViewById(R.id.tvNomeProduto);
        TextView tvValorProduto = (TextView) view.findViewById(R.id.tvValorProduto);
        TextView tvSubtotal = (TextView) view.findViewById(R.id.tvSubtotal);

        tvQuantidade.setText(""+pedido.getQuantidade());
        tvNomeProduto.setText(pedido.getItemPedido().getNome());
        tvValorProduto.setText("R$ "+pedido.getItemPedido().getValor());
        tvSubtotal.setText("Subtotal: R$ "+pedido.getSubtotal());

        return view;
    }
}
