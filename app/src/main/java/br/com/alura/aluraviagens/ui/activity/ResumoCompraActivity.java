package br.com.alura.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo da compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);

        setTitle(TITULO_APPBAR); // coloca título na appbar

        carregaPacoteRecebido();

    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent(); // pega a intent da activity anterior

        // verifica se há mais informações enviadas da outra activity
        if (intent.hasExtra(CHAVE_PACOTE)) {

            // pega a informação referente à string CHAVE_PACOTE, que no caso é o pacoteClicado
            // final por ser uma inferência de classe anônima
            Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);

        }
    }

    private void inicializaCampos(Pacote pacote) {
        mostraLocal(pacote);

        mostraImagem(pacote);

        mostraData(pacote);

        mostraPreco(pacote);
    }

    private void mostraPreco(Pacote pacote) {
        // findViewById para referenciarmos nossa TextView
        TextView preco = findViewById(R.id.resumo_compra_preco_pacote);

        // usamos a classe util para formatarmos o preço
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacote.getPreco());

        preco.setText(moedaBrasileira); // colocamos o preço na nossa TextView
    }

    private void mostraData(Pacote pacote) {
        // findViewById para referenciarmos nossa TextView
        TextView data = findViewById(R.id.resumo_compra_data_viagem);

        // usamos a classe util para formatarmos a data
        String periodoEmTexto = DataUtil.periodoEmTexto(pacote.getDias());

        data.setText(periodoEmTexto);  // colocamos a data na nossa TextView
    }

    private void mostraImagem(Pacote pacote) {
        // findViewById para referenciarmos nossa TextView
        ImageView imagem = findViewById(R.id.resumo_compra_imagem_pacote);

        // usamos a classe util para pegarmos o drawable
        Drawable drawableDoPacote = ResourceUtil
                .devolveDrawable(this, pacote.getImagem());

        imagem.setImageDrawable(drawableDoPacote); // colocamos a imagem na ImageView
    }

    private void mostraLocal(Pacote pacote) {
        // findViewById para referenciarmos nossa TextView
        TextView local = findViewById(R.id.resumo_compra_local_pacote);

        local.setText((pacote.getLocal())); // colocamos o local na TextView
    }
}