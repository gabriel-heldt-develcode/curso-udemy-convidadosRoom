package com.example.convidados.repositorio;

import android.content.Context;

import com.example.convidados.constantes.ConstantesConvidados;
import com.example.convidados.model.ModeloConvidados;

import java.util.List;

public class RepositorioConvidados {

    private ConvidadosDAO convidadosDAO;

    public RepositorioConvidados(Context context) {
        ConvidadoDatabase db = ConvidadoDatabase.getDatabase(context);
        this.convidadosDAO = db.convidadosDAO();
    }

    public List<ModeloConvidados> getTodos() {
        return this.convidadosDAO.getTodos();
    }

    public List<ModeloConvidados> getConfirmado() {
        return this.convidadosDAO.getListaPorPresenca(ConstantesConvidados.confirmacao.confimado);
    }

    public List<ModeloConvidados> getAusente() {
        return this.convidadosDAO.getListaPorPresenca(ConstantesConvidados.confirmacao.ausente);
    }

    public ModeloConvidados load(int id) {
        return this.convidadosDAO.load(id);
    }

    public boolean insere(ModeloConvidados modelo) {
        return this.convidadosDAO.insert(modelo) > 0;
    }

    public boolean update(ModeloConvidados modelo) {
        return this.convidadosDAO.update(modelo) > 0;
    }


    public boolean delete(int id) {
        ModeloConvidados modeloConvidados = this.load(id);
        return this.convidadosDAO.delete(modeloConvidados) > 0;
    }
}