package brasil.santacatarina.correcaoavminhapedida.orm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aluno on 27/09/2017.
 */
@DatabaseTable(tableName = "categorias")
public class Categoria {

    @DatabaseField( allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "nome", canBeNull = false)
    private String nome;

    public Categoria() {
    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getId()+" - "+getNome();
    }
}
