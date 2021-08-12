package com.example.convidados.view;

import static com.example.convidados.constantes.ConstantesConvidados.GUESTID;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.constantes.ConstantesConvidados;
import com.example.convidados.model.Feedback;
import com.example.convidados.model.ModeloConvidados;
import com.example.convidados.view.adapter.ConvidadosAdapter;
import com.example.convidados.view.listener.OnListClick;
import com.example.convidados.viewmodel.TodosConvidadosViewModel;

import java.util.List;

public class TodosConvidadosFragment extends Fragment {

    private TodosConvidadosViewModel mViewModel;
    private ViewHolder mViewHolder = new ViewHolder();
    private ConvidadosAdapter mAdapter = new ConvidadosAdapter();
    private int mFilter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.mViewModel = new TodosConvidadosViewModel(requireActivity().getApplication());
        View root = inflater.inflate(R.layout.fragment_todos_convidados, container, false);

        this.mViewHolder.recyclerConvidados = root.findViewById(R.id.recycler_list);
        this.mViewHolder.recyclerConvidados.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mViewHolder.recyclerConvidados.setAdapter(this.mAdapter);

        OnListClick listener = new OnListClick() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(GUESTID, id);
                Intent intent = new Intent(getContext(), FormularioConvidadosActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            @Override
            public void onDelete(int id) {
                mViewModel.delete(id);
                mViewModel.getList(mFilter);
            }
        };
        this.mAdapter.attachedListener(listener);
        if (getArguments() != null) {
            this.mFilter = getArguments().getInt(ConstantesConvidados.FILTER);
        }
        this.observers();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mViewModel.getList(this.mFilter);
    }

    private void observers() {
        this.mViewModel.modeloList.observe(getViewLifecycleOwner(), new Observer<List<ModeloConvidados>>() {
            @Override
            public void onChanged(List<ModeloConvidados> list) {
                mAdapter.ListaDeAnexo(list);
            }
        });

        this.mViewModel.feedback.observe(getViewLifecycleOwner(), new Observer<Feedback>() {
            @Override
            public void onChanged(Feedback feedback) {
                Toast.makeText(getContext(), feedback.getmMansagem(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class ViewHolder {
        RecyclerView recyclerConvidados;
    }
}