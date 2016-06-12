package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
/**
 * Created by Ewerton on 12/06/2016.
 */
public class Bomba {

    // DISTANCIA DA BOMBA A OUTRA 452
    public static final int BOMBA_WIDTH = 155; //52
    private static final int FLUTUACAO = 20; //130

    // DISTANCIA ENTRE BOMBA
    private static final int BOMBA_LACUNA = 20; //100

    // ALTURA
    private static final int ABERTURA_MENOR = 25; //120
    private Texture bombaTopo, bombaInferior;
    private Vector2 posBombaTopo, posBombaInferior;
    // LIMITES BOMBA
    private com.badlogic.gdx.math.Rectangle limitesTopo, limitesInferior;

    private Random rand;

    public Bomba(float x){
        bombaTopo = new Texture("bomba.png");
        bombaInferior = new Texture("bomba.png");
        rand = new Random();

        posBombaTopo = new Vector2(x, rand.nextInt(FLUTUACAO) + BOMBA_LACUNA + ABERTURA_MENOR);
        posBombaInferior = new Vector2(x, posBombaTopo.y - BOMBA_LACUNA - bombaInferior.getHeight());
        // LIMITES TOPO E INFERIOR DA BOMBA
        limitesTopo = new com.badlogic.gdx.math.Rectangle(posBombaTopo.x, posBombaTopo.y, bombaTopo.getWidth(), bombaTopo.getHeight());
        limitesInferior = new com.badlogic.gdx.math.Rectangle(posBombaInferior.x, posBombaInferior.y, bombaInferior.getWidth(), bombaInferior.getHeight());
    }

    public Texture getBombaTopo() {
        return bombaTopo;
    }

    public Texture getBombaInferior() {
        return bombaInferior;
    }

    public Vector2 getPosBombaTopo() {
        return posBombaTopo;
    }

    public Vector2 getPosBombaInferior() {
        return posBombaInferior;
    }

    public void reposicionar(float x){
        posBombaTopo.set(x, rand.nextInt(FLUTUACAO) + BOMBA_LACUNA + ABERTURA_MENOR);
        posBombaInferior.set(x, posBombaTopo.x - BOMBA_LACUNA - bombaInferior.getHeight());
        limitesTopo.setPosition(posBombaTopo.x, posBombaTopo.y);
        limitesInferior.setPosition(posBombaInferior.x, posBombaInferior.y);
    }


    // RETORNA COLISAO
    public boolean colide(Rectangle jogador){
        return  jogador.overlaps(limitesTopo) || jogador.overlaps(limitesInferior);
    }

    public void dispose(){
        bombaTopo.dispose();
        bombaInferior.dispose();
    }
}
