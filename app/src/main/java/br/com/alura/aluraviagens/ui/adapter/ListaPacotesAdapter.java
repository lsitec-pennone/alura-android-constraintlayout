package br.com.alura.aluraviagens.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.util.ResourceUtil;
import br.com.alura.aluraviagens.model.Pacote;

// implementação do adapter para nos ajudar a trabalhar com a ListView
public class ListaPacotesAdapter extends BaseAdapter {

    private final List<Pacote> pacotes; // lista de pacotes
    private final Context context; // contexto

    // construtor do adapter
    public ListaPacotesAdapter(List<Pacote> pacotes, Context context) {
        this.pacotes = pacotes; // lista que pegaremos da DAO
        this.context = context; // contexto
    }

    @Override
    public int getCount() {
        return pacotes.size(); // quantidade de pacotes na lista
    }

    // get vem por padrão
    @Override
    public Pacote getItem(int posicao) {
        return pacotes.get(posicao); // pacote com base na posição
    }

    // não temos id no nosso pacote, então não precisamos nos preocupar com isso
    @Override
    public long getItemId(int i) {
        return 0;
    }

    // método responsável por implementar o layout criado no xml
    @Override
    public View getView(int posicao, View view, ViewGroup parent) {

        // o método inflate transforma o xml contendo o layout do item da lista em uma View
        // a viewCriada receberá o conteúdo trasnformado com base na ViewGroup referenciada
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_pacote, parent, false);

        Pacote pacote = pacotes.get(posicao); // pega um pacote na lista de pacotes copiada DAO

        mostraLocal(viewCriada, pacote);
        mostraImagem(viewCriada, pacote);
        mostraDias(viewCriada, pacote);
        mostraPreco(viewCriada, pacote);

        return viewCriada;
    }

    private void mostraPreco(View viewCriada, Pacote pacote) {
        // pegamos a nossa TextView com base em seu id
        TextView preco = viewCriada.findViewById(R.id.item_pacote_preco);

        // Tratamos o formato de preço usando o getPreco() para pegarmos o "preco" da DAO
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());

        preco.setText(moedaBrasileira); // coloca o "preco" da DAO na nossa View
    }



    private void mostraDias(View viewCriada, Pacote pacote) {
        // pegamos a nossa TextView com base em seu id
        TextView dias = viewCriada.findViewById(R.id.item_pacote_dias);

        // fazemos a diferenciação entre "dia" e "dias"
        // usamos os getDias() para pegarmos o "dias" da DAO
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());

        dias.setText(diasEmTexto); // coloca o "dias" da DAO na nossa View
    }

    private void mostraImagem(View viewCriada, Pacote pacote) {
        // pegamos a nossa ImageView com base em se id
        ImageView imagem = viewCriada.findViewById(R.id.item_pacote_imagem);

        // se usarmos pacote.getImagem() retornaremos uma string copiada da lista da DAO,
        // mas o que queremos é a imagem referente à string, no caso um drawable
        // usamos o getImagem() para pegarmos o "Imagem" da DAO
        Drawable drawableImagemPacote = ResourceUtil.devolveDrawable(context, pacote.getImagem());

        // colocamos esse drawable (imagem) na nossa ImageView
        imagem.setImageDrawable(drawableImagemPacote); // imagem referente à string da DAO
    }


    private void mostraLocal(View viewCriada, Pacote pacote) {
        // pegamos a nossa TextView com base em seu id
        TextView local = viewCriada.findViewById(R.id.item_pacote_local);

        local.setText(pacote.getLocal()); // coloca o "local" da DAO na nossa View
    }
}
