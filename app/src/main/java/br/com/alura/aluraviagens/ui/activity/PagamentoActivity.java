package br.com.alura.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.MoedaUtil;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class PagamentoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "pagamento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        setTitle(TITULO_APPBAR); // coloca título na appbar

        carregaPacoteRecebido();
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent(); // pega a intent da activity anterior

        // verifica se há mais informações enviadas da outra activity
        if(intent.hasExtra(CHAVE_PACOTE)){

            // pega a informação referente à string CHAVE_PACOTE, que no caso é o pacoteClicado
            // final por ser uma inferência de classe anônima
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            mostraPreco(pacote);

            configuraBotao(pacote);
        }
    }

    private void configuraBotao(Pacote pacote) {
        Button botaoFinalizaCompra = findViewById(R.id.pagamento_botao_finaliza_compra);

        botaoFinalizaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaResumoCompra(pacote);

            }
        });
    }

    private void vaiParaResumoCompra(Pacote pacote) {
        // transferindo para a outra activity
        Intent intent = new Intent(PagamentoActivity.this,
                ResumoCompraActivity.class);

        // envia mais informações para a outra activity
        // esse pacote contém as informações do pacoteClicado da ListaPacotesActivity
        intent.putExtra(CHAVE_PACOTE, pacote);

        startActivity(intent); // abre a activity ResumoCompraActivity
    }

    private void mostraPreco(Pacote pacote) {

        // findViewById para referenciarmos nossa TextView
        TextView preco = findViewById(R.id.pagamento_preco_pacote);

        // usamos a classe util para corrigirmos o formato do preço
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacote.getPreco());

        preco.setText(moedaBrasileira); // colocamos o preço na TextView
    }
}