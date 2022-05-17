package br.com.alura.aluraviagens.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {

    public static final String DRAWABLE = "drawable";

    public static Drawable devolveDrawable(Context context, String drawableEmTexto) {

        Resources resources = context.getResources(); // a partir do context pegamos os resources

        // desses resources queremos um identificador do drawable
        int idDoDrawable = resources.getIdentifier(drawableEmTexto, // nome do recurso (String)
                DRAWABLE,  // tipo do recurso
                context.getPackageName()); // pacote em que ele está

        // por fim, pegamos o drawable (imagem) com base em nosso identificador
        return resources.getDrawable(idDoDrawable);
    }
}
