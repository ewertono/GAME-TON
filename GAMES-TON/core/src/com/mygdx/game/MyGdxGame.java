package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Estado.EstadoJogoManager;
import com.mygdx.game.Estado.MenuEstado;

public class MyGdxGame extends ApplicationAdapter {

	// DEFINIR ALTURA E LARGURA
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;


	public static final String TITLE = "GAME-EWERTON";
	private EstadoJogoManager gsm;
	private SpriteBatch batch;
	private Music musica;
	//Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new EstadoJogoManager();
		musica = Gdx.audio.newMusic(Gdx.files.internal("somjogo.wav"));
		musica.setLooping(true);
		musica.setVolume(0.1f);
		musica.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuEstado(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		musica.dispose();
	}
}
