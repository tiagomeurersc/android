package brasil.santacatarina.correcaoavminhapedida.modelos;

import java.io.Serializable;

/**
 * Created by Renato on 24/09/2017.
 */

public class Pedido implements Serializable{
    private Produto itemPedido;
    private int quantidade;

    public Pedido() {
    }

    public Pedido(Produto itemPedido, int quantidade) {
        this.itemPedido = itemPedido;
        this.quantidade = quantidade;
    }

    public Produto getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(Produto itemPedido) {
        this.itemPedido = itemPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void addMais1(){
        quantidade += 1;
    }

    public void removMenos1(){
        if(quantidade>1)
            quantidade -= 1;
    }

    public double getSubtotal(){
        return getItemPedido().getValor()*getQuantidade();
    }

}
