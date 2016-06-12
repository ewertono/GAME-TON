package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.Rectangle;
import java.util.Random;

/**
 * Created by Ewerton on 08/04/2016.
 */
public class Coluna {

    // DISTANCIA DA COLUNA A OUTRA 452
    public static final int COLUNA_WIDTH = 352; //52
    private static final int FLUTUACAO = 30; //130

    // DISTANCIA ENTRE COLUNA TOPO E INFERIOR
    private static final int COLUNA_LACUNA = 400; //100

    // ALTURA
    private static final int ABERTURA_MENOR = 100; //120
    private Texture colunaTopo, colunaInferior;
    private Vector2 posColunaTopo, posColunaInferior;
    //private Rectangle limitesTopo, limitesInferior;
    private com.badlogic.gdx.math.Rectangle limitesTopo, limitesInferior;

    private Random rand;

    public Coluna(float x){
         colunaTopo = new Texture("colunatopo.png");
         colunaInferior = new Texture("Acolunainferior.png");
        rand = new Random();

        posColunaTopo = new Vector2(x, rand.nextInt(FLUTUACAO) + COLUNA_LACUNA + ABERTURA_MENOR);
        posColunaInferior = new Vector2(x, posColunaTopo.y -  COLUNA_LACUNA -  colunaInferior.getHeight());

//        limitesTopo = new com.badlogic.gdx.math.Rectangle(posColunaTopo.x, posColunaTopo.y, colunaTopo.getWidth(), colunaTopo.getHeight());
//        limitesInferior = new com.badlogic.gdx.math.Rectangle(posColunaInferior.x, posColunaInferior.y, colunaInferior.getWidth(), colunaInferior.getHeight());

        limitesTopo = new com.badlogic.gdx.math.Rectangle(posColunaTopo.x, posColunaTopo.y, colunaTopo.getWidth(), colunaTopo.getHeight());
        limitesInferior = new com.badlogic.gdx.math.Rectangle(posColunaInferior.x, posColunaInferior.y, colunaInferior.getWidth(), colunaInferior.getHeight());
   }


    public Texture getColunaTopo() {
        return colunaTopo;
    }

    public Texture getColunaInferior() {
        return colunaInferior;
    }

    public Vector2 getPosColunaTopo() {
        return posColunaTopo;
    }

    public Vector2 getPosColunaInferior() {
        return posColunaInferior;
    }

    public void reposicionar(float x){
        posColunaTopo.set(x, rand.nextInt(FLUTUACAO) + COLUNA_LACUNA + ABERTURA_MENOR);
        posColunaInferior.set(x, posColunaTopo.y - COLUNA_LACUNA - colunaInferior.getHeight());
        limitesTopo.setPosition(posColunaTopo.x, posColunaTopo.y);
        limitesInferior.setPosition(posColunaInferior.x, posColunaInferior.y);
    }

    public boolean colide(com.badlogic.gdx.math.Rectangle jogador){
        //return jogador.overlaps(limitesTopo); || jogador.overlaps(limitesInferior);
        return jogador.overlaps(limitesInferior);
    }

    public void dispose(){
        colunaTopo.dispose();
        colunaInferior.dispose();
       // System.out.println("teste");
    }
}
