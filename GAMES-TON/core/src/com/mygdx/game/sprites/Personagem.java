package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
//import com.sun.xml.internal.messaging.saaj.soap.GifDataContentHandler;

import org.w3c.dom.css.Rect;

/**
 * Created by Ewerton on 08/04/2016.
 */
public class Personagem {

    private static final int GRAVIDADE = -15;
    private static final int MOVIMENTO = 100;
    private Vector3 posicao;
    private Vector3 velocidade;
    private Rectangle limites;
    private Animacao personagemAnimacao;
    private Sound sompulo;

    private Texture jogador;

    public Personagem(int x, int y){
        posicao = new Vector3(x, y, 0);
        velocidade = new Vector3(0, 0, 0);
        // PERSONAGEM SEM ANIMAÇÃO
        //jogador = new Texture("jogador02.png");
        //PERSONAGEM ANIMADO
        Texture texture = new Texture("personagem.png");
        personagemAnimacao = new Animacao(new TextureRegion(texture), 8, 0.5f);
//        limites = new Rectangle(x, y, jogador.getWidth(), jogador.getHeight());
        limites = new Rectangle(x, y, texture.getWidth() / 8, texture.getHeight());
        sompulo = Gdx.audio.newSound(Gdx.files.internal("sompulo.wav"));
    }

    public void update(float dt){

        personagemAnimacao.update(dt);
//        if (posicao.y > 300)
//            posicao.y = 299;

        if (posicao.y > 0)
            velocidade.add(0, GRAVIDADE, 0);

        velocidade.scl(dt);
        posicao.add(MOVIMENTO * dt, velocidade.y, 0);

        if (posicao.y <= 45)
            posicao.y = 45;

        velocidade.scl(1/dt);
        limites.setPosition(posicao.x, posicao.y);
    }

    public Vector3 getPosicao() {
        return posicao;
    }

    // PERSONAGEM SEM ANIMACAO
//    public Texture getTexture() {
//        return jogador;
//    }

    // PERSONAGEM ANIMADO
    public TextureRegion getTexture() {
        return personagemAnimacao.getFrame();
    }


    public void pular(){
        if (posicao.y <= 45) {
            velocidade.y = 550;
            sompulo.play(0.5f);
            //System.out.println("Pulou");
        }

    }

    public void pular2(){
        if (posicao.y <= 300) {
            velocidade.y = 550;
            sompulo.play(0.5f);
            //System.out.println("Pulou");
        }

    }

    public void reposicionar(){
       // posicao.x = 100;
       // velocidade.x = 800;
    }

    // RETORNA LIMETES DO RETANGULO
    public Rectangle getLimites(){
        return limites;
    }

    public void dispose(){
        jogador.dispose();
        sompulo.dispose();
    }

}
