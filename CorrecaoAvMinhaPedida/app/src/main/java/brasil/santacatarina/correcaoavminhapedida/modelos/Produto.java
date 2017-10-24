package brasil.santacatarina.correcaoavminhapedida.modelos;

import java.io.Serializable;

/**
 * Created by Renato on 24/09/2017.
 */

public class Produto implements Serializable {
    private String nome;
    private double valor;

    public Produto() {
    }

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return getNome()+" - R$ "+getValor();
    }
}

