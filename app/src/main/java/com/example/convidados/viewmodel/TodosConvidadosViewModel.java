package com.example.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.convidados.constantes.ConstantesConvidados;
import com.example.convidados.model.Feedback;
import com.example.convidados.model.ModeloConvidados;
import com.example.convidados.repositorio.RepositorioConvidados;

import java.util.List;

public class TodosConvidadosViewModel extends AndroidViewModel {

    private RepositorioConvidados mRepositorio;
    private MutableLiveData<List<ModeloConvidados>> mModeloList = new MutableLiveData<>();
    public LiveData<List<ModeloConvidados>> modeloList = this.mModeloList;

    private MutableLiveData<Feedback> mFeedback = new MutableLiveData<>();
    public LiveData<Feedback> feedback = this.mFeedback;

    public TodosConvidadosViewModel(@NonNull Application application) {
        super(application);
        this.mRepositorio = new RepositorioConvidados(application.getApplicationContext());
    }

    public void getList(int filter) {
        if (filter == ConstantesConvidados.confirmacao.nao_confirmado) {
            this.mModeloList.setValue(this.mRepositorio.getTodos()); //atribui a listagem ao MUtableLivaData
        } else if (filter == ConstantesConvidados.confirmacao.confimado) {
            this.mModeloList.setValue(this.mRepositorio.getConfirmado());
        } else if (filter == ConstantesConvidados.confirmacao.ausente) {
            this.mModeloList.setValue(this.mRepositorio.getAusente());
        }
    }

    public void delete(int id) {
        if (this.mRepositorio.delete(id)) {
            this.mFeedback.setValue(new Feedback("Convidado removido com sucesso"));
        } else {
            this.mFeedback.setValue(new Feedback("Erro inesperado", false));
        }

    }
}

