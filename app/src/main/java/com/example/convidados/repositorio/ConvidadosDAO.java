package com.example.convidados.repositorio;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.convidados.model.ModeloConvidados;

import java.util.List;

@Dao  //define consultas/acessos ao banco de dados
public interface ConvidadosDAO {

    @Insert
    long insert(ModeloConvidados convidados);

    @Update
    int update(ModeloConvidados convidados);

    @Delete
    int delete(ModeloConvidados convidados);

    @Query("SELECT * FROM convidados WHERE id = :id")            //uma instrução para o banco fazer uma ação
    ModeloConvidados load(int id);

    @Query("SELECT * FROM convidados")
    List<ModeloConvidados> getTodos();

    @Query("SELECT * FROM convidados WHERE confirmacao = :confirmacao")
    List<ModeloConvidados> getListaPorPresenca(int confirmacao);
}
