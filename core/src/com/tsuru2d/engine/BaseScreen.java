package com.tsuru2d.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tsuru2d.engine.loader.AssetLoader;
import org.luaj.vm2.LuaTable;

public abstract class BaseScreen implements Screen {
    //protected final EngineMain mGame;
    protected final Stage mStage;
    protected final LuaTable mLuaEnvironment;

    /*public BaseScreen(EngineMain game) {
        mGame = game;
        mStage = new Stage(game.getViewport(), game.getSpriteBatch());
        mLuaEnvironment = new LuaTable();
    }*/

    public BaseScreen() {
        //mGame = game;
        //mStage = new Stage(game.getViewport(), game.getSpriteBatch());
        mStage = new Stage();
        mLuaEnvironment = new LuaTable();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mStage);
    }

    @Override
    public void render(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public float getWidth() {
        return mStage.getWidth();
    }

    public float getHight() {
        return mStage.getHeight();
    }

    public Stage getmStage() {
        return mStage;
    }

    public LuaTable getmLuaEnvironment() {
        return mLuaEnvironment;
    }

    /*public AssetLoader getAssetLoader() {
        return mGame.getAssetLoader();
    }*/
}