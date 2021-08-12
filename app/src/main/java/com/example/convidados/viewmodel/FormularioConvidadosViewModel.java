package com.example.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.convidados.model.Feedback;
import com.example.convidados.model.ModeloConvidados;
import com.example.convidados.repositorio.RepositorioConvidados;

public class FormularioConvidadosViewModel extends AndroidViewModel {

    private final RepositorioConvidados mRepositorio;
    private MutableLiveData<ModeloConvidados> mConvidado = new MutableLiveData();
    public LiveData<ModeloConvidados> convidado = this.mConvidado; //esse metodo convidado vai ser observado pela activity

    private MutableLiveData<Feedback> mFeedback = new MutableLiveData();
    public LiveData<Feedback> feedback = this.mFeedback;

    public FormularioConvidadosViewModel(@NonNull Application application) {
        super(application);
        this.mRepositorio = new RepositorioConvidados(application.getApplicationContext());
    }

    public void salvar(ModeloConvidados modelo) {

        //regra de negócio: convidado nao pode ser salvo se nao tiver nome

        if ("".equals(modelo.getNome())) {
            this.mFeedback.setValue(new Feedback("Nome obrigatório!", false));
            return;
        }

        if (modelo.getId() == 0) {
            if (this.mRepositorio.insere(modelo)) {
                this.mFeedback.setValue(new Feedback("Convidado inserido com sucesso!"));
            }
             else {
                this.mFeedback.setValue(new Feedback("Erro inesperado", false));
            }
        } else {
            if (this.mRepositorio.update(modelo)) {
                this.mFeedback.setValue(new Feedback("Convidado atualizado com sucesso!"));
            }
            else {
                this.mFeedback.setValue(new Feedback("Erro inesperado", false));
            }
        }
    }

    public void load(int id) {
        this.mConvidado.setValue(this.mRepositorio.load(id));
    }
}