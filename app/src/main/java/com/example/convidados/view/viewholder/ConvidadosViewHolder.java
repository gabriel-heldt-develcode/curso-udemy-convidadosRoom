package com.example.convidados.view.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.model.ModeloConvidados;
import com.example.convidados.view.listener.OnListClick;

public class ConvidadosViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextNome;
    private Context mContext;

    public ConvidadosViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mContext = itemView.getContext();
        this.mTextNome = itemView.findViewById(R.id.text_nome);
    }

    public void bind(ModeloConvidados modelo, final OnListClick listener) {
        this.mTextNome.setText(modelo.getNome());
        this.mTextNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(modelo.getId());
            }
        });

        this.mTextNome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //mensagem de confirmação se o usuário quer ou nao fazer a ação
                new AlertDialog.Builder(mContext)
                        .setTitle("Remoção de convidado")
                        .setMessage("Deseja realmente remover este convidado?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDelete(modelo.getId());
                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();
                return false;
            }
        });
    }
}
