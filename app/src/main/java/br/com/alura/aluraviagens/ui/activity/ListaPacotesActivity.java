package br.com.alura.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.alura.aluraviagens.R;

public class ListaPacotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // conteúdo da view inserido na activity
        setContentView(R.layout.activity_lista_pacotes); // activity_lista_pacotes.xml
    }
}