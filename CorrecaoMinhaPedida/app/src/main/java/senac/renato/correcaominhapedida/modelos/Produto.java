package senac.renato.correcaominhapedida.modelos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;


@DatabaseTable
public class Produto implements Serializable {


    @DatabaseField( allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "nome", canBeNull = false)
    private String nome;

    @DatabaseField(columnName = "valor", canBeNull = false)
    private double valor;

    public Produto() {
    }

    public Produto(Integer id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
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

