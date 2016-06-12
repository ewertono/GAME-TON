package com.mygdx.game.Estado;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Ewerton on 07/04/2016.
 */
public abstract class Estado {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected EstadoJogoManager gsm;

    protected Estado(EstadoJogoManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}

