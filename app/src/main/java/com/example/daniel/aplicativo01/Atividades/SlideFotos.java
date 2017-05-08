package com.example.daniel.aplicativo01.Atividades;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.example.daniel.aplicativo01.R;

import static com.example.daniel.aplicativo01.R.id.slides;

/**
 * Created by Daniel on 29/04/2017.
 */

public class SlideFotos extends Activity implements ViewSwitcher.ViewFactory{

    private int[] imagens = {R.drawable.cachorrinhos, R.drawable.coelhinhos,
            R.drawable.gatinho, R.drawable.girafas, R.drawable.golfinho,
            R.drawable.leao, R.drawable.onca, R.drawable.panda};
    private ImageSwitcher imageSwitcher;

    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.galeria);

        LinearLayout slide = (LinearLayout) findViewById(slides);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imagem_selecionada);
        imageSwitcher.setFactory(this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        for(int i = 0; i < imagens.length; i++) {
            ImageView imagem = new ImageView(this);
            Bitmap imagemBit = decodeSampledBitmapFromResource(getResources(), imagens[i], 200,150);
            imagemBit = Bitmap.createScaledBitmap(imagemBit, 300, 300, true);
            imagem.setImageBitmap(imagemBit);
            imagem.setPadding(10, 20, 10, 20);
            imagem.setOnClickListener(new AparecerImagem(imagens[i], imageSwitcher));
            /*imagem.setImageResource(imagens[i]);
            imagem.setAdjustViewBounds(true);
            imagem.setMaxHeight(400);*/
            slide.addView(imagem);
        }

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    @Override
    public View makeView() {
        ImageView img = new ImageView(this);
        img.setBackgroundColor(Color.WHITE);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        img.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return img;
    }
}
