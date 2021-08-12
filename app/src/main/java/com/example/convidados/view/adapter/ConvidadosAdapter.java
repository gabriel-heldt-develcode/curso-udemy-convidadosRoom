package com.example.convidados.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.model.ModeloConvidados;
import com.example.convidados.view.listener.OnListClick;
import com.example.convidados.view.viewholder.ConvidadosViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ConvidadosAdapter extends RecyclerView.Adapter<ConvidadosViewHolder> {

    private List<ModeloConvidados> mList = new ArrayList<>();
    private OnListClick mListener;

    @NonNull
    @Override
    public ConvidadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_linha_convidados, parent, false);

        return new ConvidadosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConvidadosViewHolder holder, int position) {
        holder.bind(this.mList.get(position), this.mListener);
    }

    @Override
    public int getItemCount() {             //quantidade de elementos
        return this.mList.size();
    }

    public void ListaDeAnexo(List<ModeloConvidados> list) {
        this.mList = list;
        notifyDataSetChanged(); //atualiza a listagem, notifica o adapter para mostrar as novas implementações
    }

    public void attachedListener(OnListClick listener) {
        this.mListener = listener;
    }
}
