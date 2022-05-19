package br.com.alura.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do pacote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);

        setTitle(TITULO_APPBAR); // muda o título da appbar

        carregaPacoteRecebido();

    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent(); // pega a intent da activity anterior

        // verifica se há mais informações enviadas da outra activity
        if (intent.hasExtra(CHAVE_PACOTE)){

            // pega a informação referente à string "pacote", que no caso é o pacoteClicado
            // final por ser uma inferência de classe anônima
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);
            configuraBotao(pacote);
        }
    }

    private void configuraBotao(Pacote pacote) {
        // findViewBy id para referenciarmos o nosso Button
        Button botaoRealizaPagamento = findViewById(R.id.resumo_pacote_boao_realiza_pagamento);

        // listener para trocar de tela ao apertarmos o botão
        botaoRealizaPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaPagamento(pacote);

            }
        });
    }

    private void vaiParaPagamento(Pacote pacote) {
        // transferindo para a outra activity
        Intent intent = new Intent(ResumoPacoteActivity.this,
                PagamentoActivity.class);

        // envia mais informações para a outra activity
        // esse pacote contém as informações do pacoteClicado da ListaPacotesActivity
        intent.putExtra(CHAVE_PACOTE, pacote);

        startActivity(intent); // abre a activity PagamentoActivity
    }

    private void inicializaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraDias(pacote);
        mostraPreco(pacote);
        mostraData(pacote);
    }

    private void mostraData(Pacote pacote) {

        // findViewById para referenciarmos nossa TextView
        TextView data = findViewById(R.id.resumo_pacote_data);

        // usamos a API pronta calendar para formatarmos a data
        String dataFormatadaDaViagem = DataUtil.periodoEmTexto(pacote.getDias());

        data.setText(dataFormatadaDaViagem); // colocamos o preço na TextView
    }



    private void mostraPreco(Pacote pacote) {
        // findViewById para referenciarmos nossa TextView
        TextView preco = findViewById(R.id.resumo_pacote_preco);

        // usamos a classe util para corrigirmos o formato do preço
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());

        preco.setText(moedaBrasileira); // colocamos o preço na TextView
    }

    private void mostraDias(Pacote pacote) {
        // findViewById para referenciarmos nossa TextView
        TextView dias = findViewById(R.id.resumo_pacote_dias);

        // usamos a classe util para diferenciarmos "dia" de "dias"
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());

        dias.setText(diasEmTexto); // colocamos os dias na TextView
    }

    private void mostraImagem(Pacote pacote) {
        // findViewById para referenciarmos nossa ImageView
        ImageView imagem = findViewById(R.id.resumo_pacote_imagem);

        // com base na string, pegamos a imagem referente a ela que esta em resources
        Drawable drawableDoPacote = ResourceUtil
                .devolveDrawable(this, pacote.getImagem());

        imagem.setImageDrawable(drawableDoPacote); // colocamos a imagem na ImageView
    }

    private void mostraLocal(Pacote pacote) {

        // findViewById para referenciarmos nossa TextView
        TextView local = findViewById(R.id.resumo_pacote_local);

        local.setText(pacote.getLocal()); // colocamos o texto na TextView
    }
}