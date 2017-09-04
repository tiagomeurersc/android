package brasil.santacatarina.pesquisaapp;

import android.widget.ArrayAdapter;

import java.io.Serializable;


public class Pessoa implements Serializable {
    private String nome;
    private int idade;
    private String profissao;
    private String email;
    private String telefone;
    private String rua;
    private String cep;
    private String cidade;

    public Pessoa() {

    }

    public Pessoa(String nome, int idade, String profissao, String email, String telefone, String rua, String cep, String cidade) {
        this.nome = nome;
        this.idade = idade;
        this.profissao = profissao;
        this.email = email;
        this.telefone = telefone;
        this.rua = rua;
        this.cep = cep;
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+"\nIdade: "+idade;
    }
}
