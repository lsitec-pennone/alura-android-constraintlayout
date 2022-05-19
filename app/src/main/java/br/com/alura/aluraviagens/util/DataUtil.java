package br.com.alura.aluraviagens.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {

    public static final String DIA_E_MES = "dd/MM";

    public static String periodoEmTexto(int dias) {

        Calendar dataIda = Calendar.getInstance(); // data de hoje
        Calendar dataVolta = Calendar.getInstance(); // data de hoje a ser transformada
        dataVolta.add(Calendar.DATE, dias); // transforma a data de volta
        SimpleDateFormat formatoBrasileiro =
                new SimpleDateFormat(DIA_E_MES); // informa o formato
        String dataFormatadaIda =
                formatoBrasileiro.format(dataIda.getTime()); // formata data de ida
        String dataFormatadaVolta =
                formatoBrasileiro.format(dataVolta.getTime()); // formata data de volta
        return dataFormatadaIda + " - "
                + dataFormatadaVolta + " de "
                + dataVolta.get(Calendar.YEAR); // retorna string completa formatada
    }

}
