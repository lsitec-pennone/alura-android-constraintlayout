package br.com.alura.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.dao.PacoteDAO;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.ui.adapter.ListaPacotesAdapter;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class ListaPacotesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pacotes";

    // método onCreate indica o momento da criação do ciclo de vida da activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState); // sobreescreve os métodos de onCreate

        // conteúdo da view inserido na activity
        setContentView(R.layout.activity_lista_pacotes); // activity_lista_pacotes.xml

        setTitle(TITULO_APPBAR); // muda o título da appbar

        configuraLista();

    }

    private void configuraLista() {

        // findViewById para referenciarmos nossa ListView
        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);

        // pegando a lista de pacotes da DAO
        // final por ser uma inferência de classe anônima
        final List<Pacote> pacotes = new PacoteDAO().lista();

        // Adapter para trabalharmos com a nossa lista de forma mais simples
        listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));

        // listener para trocar de tela ao apertarmos o botão
        listaDePacotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                // pega um pacote a partir da posição
                Pacote pacoteClicado = pacotes.get(posicao);

                vaiParaResumoPacote(pacoteClicado);

            }
        });
    }

    private void vaiParaResumoPacote(Pacote pacoteClicado) {
        // transferindo para a outra activity
        Intent intent = new Intent(ListaPacotesActivity.this,
                ResumoPacoteActivity.class);

        // envia mais informações para a outra activity
        intent.putExtra(CHAVE_PACOTE, pacoteClicado); // envia o pacoteClicado

        startActivity(intent); // abre a activity ResumoPacoteActivity
    }
}