package br.com.alura.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.dao.PacoteDAO;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.ui.adapter.ListaPacotesAdapter;

public class ListaPacotesActivity extends AppCompatActivity {

    // método onCreate indica o momento da criação do ciclo de vida da activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // sobreescreve os métodos de onCreate

        // conteúdo da view inserido na activity
        setContentView(R.layout.activity_lista_pacotes); // activity_lista_pacotes.xml

        // findViewById para referenciarmos nossa ListView
        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);

        // pegando a lista de pacotes da DAO
        List<Pacote> pacotes = new PacoteDAO().lista();

        // Adapter para trabalharmos com a nossa lista de forma mais simples
        listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
    }
}