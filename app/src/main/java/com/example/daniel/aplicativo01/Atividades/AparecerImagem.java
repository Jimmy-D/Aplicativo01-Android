package com.example.daniel.aplicativo01.Atividades;

import android.view.View;
import android.widget.ImageSwitcher;

/**
 * Created by Daniel on 01/05/2017.
 */

public class AparecerImagem implements View.OnClickListener {
    private int imagem;
    private ImageSwitcher imageSwitcher;
    public AparecerImagem(int imagem, ImageSwitcher imageSwitcher) {
        this.imagem = imagem;
        this.imageSwitcher = imageSwitcher;
    }
    @Override
    public void onClick(View view) {
        imageSwitcher.setImageResource(imagem);
    }
}
