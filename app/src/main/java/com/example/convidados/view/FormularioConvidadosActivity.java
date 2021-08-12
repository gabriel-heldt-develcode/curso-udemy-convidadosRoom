package com.example.convidados.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.convidados.R;
import com.example.convidados.constantes.ConstantesConvidados;
import com.example.convidados.model.Feedback;
import com.example.convidados.model.ModeloConvidados;
import com.example.convidados.viewmodel.FormularioConvidadosViewModel;

public class FormularioConvidadosActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private FormularioConvidadosViewModel mViewModel;
    private int mConvidadoId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_convidados);

        this.mViewModel = new ViewModelProvider(this).get(FormularioConvidadosViewModel.class);

        this.mViewHolder.edNome = findViewById(R.id.edit_nome);
        this.mViewHolder.rbConfirmado = findViewById(R.id.radio_confirmado);
        this.mViewHolder.rbNaoConfirmado = findViewById(R.id.radio_nao_confirmado);
        this.mViewHolder.rbAusente = findViewById(R.id.radio_ausente);
        this.mViewHolder.btSalvar = findViewById(R.id.button_salvar);

        this.setListeners();
        this.setObservers();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.mConvidadoId = bundle.getInt(ConstantesConvidados.GUESTID);
            this.mViewModel.load(this.mConvidadoId);
        }
    }

    private void setObservers() {
        this.mViewModel.convidado.observe(this, new Observer<ModeloConvidados>() {
            @Override //método resposnável por executar a ação do "convidado"
            public void onChanged(ModeloConvidados modeloConvidados) {
                mViewHolder.edNome.setText(modeloConvidados.getNome());

                int confirmacao = modeloConvidados.getConfirmacao();
                mViewHolder.rbNaoConfirmado.setChecked(confirmacao == ConstantesConvidados.confirmacao.nao_confirmado);
                mViewHolder.rbConfirmado.setChecked(confirmacao == ConstantesConvidados.confirmacao.confimado);
                mViewHolder.rbAusente.setChecked(confirmacao == ConstantesConvidados.confirmacao.ausente);

            }
        });

        this.mViewModel.feedback.observe(this, new Observer<Feedback>() {
            @Override
            public void onChanged(Feedback feedback) {
                Toast.makeText(getApplicationContext(), feedback.getmMansagem(),Toast.LENGTH_SHORT).show();
                if (feedback.deuSucesso()) {
                    finish();
                }
            }
        });
    }

    private void setListeners() {
        this.mViewHolder.btSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_salvar) {
            this.handleSave();
        }
    }

    private void handleSave() {
        String nome = this.mViewHolder.edNome.getText().toString();
        int confirmacao = 2;

        if (this.mViewHolder.rbConfirmado.isChecked()) {
            confirmacao = ConstantesConvidados.confirmacao.confimado;
        } else if (this.mViewHolder.rbAusente.isChecked()) {
            confirmacao = ConstantesConvidados.confirmacao.ausente;
        }

        ModeloConvidados modelo = new ModeloConvidados(this.mConvidadoId, nome, confirmacao);
        this.mViewModel.salvar(modelo);
    }

    private static class ViewHolder {
        EditText edNome;
        RadioButton rbConfirmado;
        RadioButton rbNaoConfirmado;
        RadioButton rbAusente;
        Button btSalvar;
    }
}