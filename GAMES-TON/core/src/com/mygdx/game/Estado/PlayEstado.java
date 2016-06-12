package com.mygdx.game.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Bomba;
import com.mygdx.game.sprites.Coluna;
import com.mygdx.game.sprites.Personagem;

import java.lang.reflect.Array;

import javax.swing.text.Position;

/**
 * Created by Ewerton on 07/04/2016.
 */
public class PlayEstado extends Estado {

    private static final int COLUNA_ESPACO = 125;
    private static final int COLUNA_COUNT = 4;

    private static final int BOMBA_ESPACO = 125;
    private static final int BOMBA_COUNT = 4;

    private static final int ESTRADA_Y_DESVIO = -50; // ESTRADA
    private static final int NUVEM_Y_DESVIO = 350; // NUVEM
    private static final int NUVEM_Y_DESVIO1 = 330; // NUVEM01


    private Personagem personagem;
    private Texture telafundo;
    private Texture estrada;
    private Vector2 estradaPosicao1, estradaPosicao2;
    int numero = 150;

    private Texture nuvem;
    private Vector2 nuvemPosicao1;
    private Vector2 nuvemPosicao2;



    private com.badlogic.gdx.utils.Array<Coluna> colunas;
    private com.badlogic.gdx.utils.Array<Bomba> bombas;

    public PlayEstado(EstadoJogoManager gsm) {
        super(gsm);
        personagem = new Personagem(50, 200);
        //jogador = new Texture("jogador02.png");
        camera.setToOrtho(false, MyGdxGame.HEIGHT , MyGdxGame.WIDTH / 2);
        telafundo = new Texture("testefundojogo.png");
        //coluna = new Coluna(100);

        //ESTRADA
        estrada = new Texture("chao.png");
        estradaPosicao1 = new Vector2(camera.position.x - camera.viewportWidth / 2, ESTRADA_Y_DESVIO);
        estradaPosicao2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + estrada.getWidth(), ESTRADA_Y_DESVIO);

        // NUVEM
        nuvem = new Texture("nuvem2.png");
        nuvemPosicao1 = new Vector2(camera.position.x - camera.viewportWidth / 2, NUVEM_Y_DESVIO);
        nuvemPosicao2 = new Vector2((camera.position.x - camera.viewportWidth / 2), NUVEM_Y_DESVIO1);


        // ARRAY DE COLUNAS
        colunas = new com.badlogic.gdx.utils.Array<Coluna>();

        for (int i = 1; i <= COLUNA_COUNT; i++){
            colunas.add(new Coluna(i * (COLUNA_ESPACO + Coluna.COLUNA_WIDTH)));
        }


        // ARRAY DE BOMBAS
        bombas = new com.badlogic.gdx.utils.Array<Bomba>();

        for (int j = 1; j <= BOMBA_COUNT; j++){
            bombas.add(new Bomba(j * (BOMBA_ESPACO + Bomba.BOMBA_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        // TOQUE NA TELA
        if (Gdx.input.justTouched()){
            //if (Gdx.input.getY() < 300) {
                personagem.pular();
            //}
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateEstrada();
        updateNuvem();
        personagem.update(dt);
       camera.position.x = personagem.getPosicao().x + numero;
//        camera.position.x = personagem.getPosicao().x + Gdx.input.getX();


        for (Bomba bomba : bombas){
            if (camera.position.x - (camera.viewportWidth / 2) > bomba.getPosBombaTopo().x + bomba.getBombaTopo().getWidth()){
                bomba.reposicionar(bomba.getPosBombaTopo().x + ((Bomba.BOMBA_WIDTH + BOMBA_ESPACO) * BOMBA_COUNT));
            }

            // VERIFICA SE HOUVE COLISAO COM BOMBA
            if (bomba.colide(personagem.getLimites())) {
                //   System.out.println("COLIDIU");
                // Volta jogo no inicio
                gsm.set(new MenuEstado(gsm));
            }
        }


        for (Coluna coluna : colunas){
            if (camera.position.x - (camera.viewportWidth / 2) > coluna.getPosColunaTopo().x + coluna.getColunaTopo().getWidth()){
                coluna.reposicionar(coluna.getPosColunaTopo().x + ((Coluna.COLUNA_WIDTH + COLUNA_ESPACO) * COLUNA_COUNT));
            }

//            // COLISAO COM COLUNA
            if (coluna.colide(personagem.getLimites()))
                personagem.pular2();
              //gsm.set(new PlayEstado(gsm));
              //  System.out.println("foi");




        }



        // LADO INFERIOR PERSONAGEM NO TOPO DA ESTRADA
//        if (personagem.getPosicao().y <= estrada.getHeight() + ESTRADA_Y_DESVIO)
//           gsm.set(new PlayEstado(gsm));



        camera.update();

    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(telafundo, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(personagem.getTexture(), personagem.getPosicao().x, personagem.getPosicao().y);

        for (Coluna coluna : colunas) {
            sb.draw(coluna.getColunaTopo(), coluna.getPosColunaTopo().x, coluna.getPosColunaTopo().y);
            sb.draw(coluna.getColunaInferior(), coluna.getPosColunaInferior().x, coluna.getPosColunaInferior().y);
        }

        for (Bomba bomba : bombas){
            sb.draw(bomba.getBombaTopo(), bomba.getPosBombaTopo().x, bomba.getPosBombaTopo().y);
            sb.draw(bomba.getBombaInferior(), bomba.getPosBombaInferior().x, bomba.getPosBombaInferior().y);
        }

        sb.draw(estrada, estradaPosicao1.x, estradaPosicao1.y);
        sb.draw(estrada, estradaPosicao2.x, estradaPosicao2.y);
        sb.draw(nuvem, nuvemPosicao1.x, nuvemPosicao1.y);
        sb.draw(nuvem, nuvemPosicao2.x, nuvemPosicao2.y);

        sb.end();
    }

    @Override
    public void dispose() {
        telafundo.dispose();
        personagem.dispose();
        estrada.dispose();
        nuvem.dispose();
        for (Coluna coluna : colunas)
                coluna.dispose();
        for (Bomba bomba : bombas)
            bomba.dispose();
       // System.out.println("Play Estado Dispose");
    }

    private void updateEstrada(){
         if (camera.position.x - (camera.viewportWidth / 2) > estradaPosicao1.x + estrada.getWidth())
             estradaPosicao1.add(estrada.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > estradaPosicao2.x + estrada.getWidth())
            estradaPosicao2.add(estrada.getWidth() * 2, 0);
    }

    private void updateNuvem() {
        if (camera.position.x - (camera.viewportWidth / 2) > nuvemPosicao1.x + nuvem.getWidth())
            nuvemPosicao1.add(nuvem.getWidth() * 8, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > nuvemPosicao2.x + nuvem.getWidth())
            nuvemPosicao2.add(nuvem.getWidth() * 10, 0);
    }



}
