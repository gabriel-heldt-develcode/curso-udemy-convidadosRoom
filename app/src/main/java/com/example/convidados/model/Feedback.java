package com.example.convidados.model;

public class Feedback {

    public Feedback(String mensagem) {
        this.mMansagem = mensagem;
    }

    public Feedback(String mensagem, boolean sucesso) {
        this.mMansagem = mensagem;
        this.mSucesso = sucesso;
    }

    public boolean deuSucesso() {
        return this.mSucesso;
    }

    public String getmMansagem() {
        return this.mMansagem;
    }




    private boolean mSucesso = true;   //isso da a possibilidade de nos passar true ou false
    private String mMansagem = "";   //nos mostra/indica qual é o status
}
