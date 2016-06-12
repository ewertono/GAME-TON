package com.mygdx.game.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Ewerton on 07/04/2016.
 */
public class MenuEstado extends Estado {

    private Texture background;
    private Texture PlayBtn;

    public MenuEstado(EstadoJogoManager gms) {
        super(gms);
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("fundojogo.png");
        PlayBtn = new Texture("botaoplay.png");

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayEstado(gsm));
           dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        // DISPOSITIVO DEFINE O TAMANHO DA TELA
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        // DEFINIR POSIÇÃO DO BOTÃO PLAY
        sb.draw(PlayBtn, camera.position.x - PlayBtn.getWidth() / 2, camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        PlayBtn.dispose();
        //System.out.println("Menu Dispose");
    }
}
