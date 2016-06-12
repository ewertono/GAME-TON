package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Ewerton on 13/04/2016.
 */
public class Animacao {
   // private Array<TextureRegion> frames;
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float atualFrameTime;
    private int frameCount;
    private int frame;

    public Animacao(TextureRegion region, int frameCount, float cicloTime){
        frames = new Array<TextureRegion>();
        int frameWidht = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidht, 0, frameWidht, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cicloTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        atualFrameTime += dt;
        if (atualFrameTime > maxFrameTime){
            frame++;
            atualFrameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
