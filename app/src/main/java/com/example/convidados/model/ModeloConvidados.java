package com.example.convidados.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Convidados")
public class ModeloConvidados {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "confirmacao")
    private int confirmacao;

    public ModeloConvidados(int id, String nome, int confirmacao) {
        this.id = id;
        this.nome = nome;
        this.confirmacao = confirmacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(int confirmacao) {
        this.confirmacao = confirmacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int valeu) {
        this.id = valeu;
    }
}
