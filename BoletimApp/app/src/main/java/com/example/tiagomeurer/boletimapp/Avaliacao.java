package com.example.tiagomeurer.boletimapp;

import java.io.Serializable;

/**
 * Created by tiagomeurer on 30/08/17.
 */

public class Avaliacao implements Serializable {

    String professor;
    Double nota;
    Disciplina disciplina;

    public Avaliacao(String professor, double nota, Disciplina disciplina) {
        this.professor = professor;
        this.nota = nota;
        this.disciplina = disciplina;
    }

    public Avaliacao() {
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "professor='" + professor + '\'' +
                ", nota=" + nota +
                ", disciplina=" + disciplina +
                '}';
    }
}
