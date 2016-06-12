package com.mygdx.game.Estado;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Ewerton on 07/04/2016.
 */
public class EstadoJogoManager {

    private Stack<Estado> estados;

    public EstadoJogoManager(){

        estados = new Stack<Estado>();
    }

    public void push(Estado estado){

        estados.push(estado);
    }

    public void pop(){

        estados.pop().dispose();
    }

    public void set(Estado estado){
        estados.pop();
        estados.push(estado);
    }

    public void update(float dt){
        estados.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        estados.peek().render(sb);
    }



}
