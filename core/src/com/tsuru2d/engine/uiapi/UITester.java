package com.tsuru2d.engine.uiapi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.tsuru2d.engine.BaseScreen;
import com.tsuru2d.engine.LuaContext;
import com.tsuru2d.engine.PlatformApi;
import com.tsuru2d.engine.lua.ExposeToLua;
import com.tsuru2d.engine.lua.ExposedJavaClass;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class UITester extends ApplicationAdapter {

    private MBaseScreen mBaseScreen;
    private ButtonFacade mButtonFacade;
    private ButtonGroupFacade mButtonGroup;
    private HashMap<String, Object> mInstances;

    @Override
    public void create() {
		mBaseScreen = new MBaseScreen();
        mInstances = new HashMap<String, Object>();
        Gdx.input.setInputProcessor(mBaseScreen.getmStage());
        FileInputStream in = null;
        try{
            in = new FileInputStream(new File("/mnt/76A0FD4FA0FD15F9/newWorkspace/Tsuru2D/Tsuru2D-engine/core/src/com/tsuru2d/engine/uiapi/UITest.lua"));
        } catch (Exception e) {
            System.out.println("nofile");
        }
        //mBaseScreen.getmLuaEnvironment().set(LuaValue.INDEX, new ExposedJavaClass(this));
        LuaContext.load(in,
                "screen",
                mBaseScreen.getmLuaEnvironment());
        mBaseScreen.getmLuaEnvironment().get("onCreate").call(new ExposedJavaClass(this));
        //mBaseScreen.getmLuaEnvironment().get("screen").get("onCreate").call();
    }

    @ExposeToLua
    public LuaValue createButton(String id, String text, LuaFunction callBack, String zipLocation, String up, String down) {
        mButtonFacade = new ButtonFacade(mBaseScreen, mBaseScreen.getmLuaEnvironment(), zipLocation, up, down);
        mButtonFacade.setText(text);
        mButtonFacade.setOnClick(callBack);
        //mButtonFacade.setIMagewhenNotClicked();
        mBaseScreen.getmStage().addActor(mButtonFacade.getActor());
        mInstances.put(id, mButtonFacade);
        return new ExposedJavaClass(mButtonFacade);
    }

    @ExposeToLua
    public LuaValue createCheckButton(String id, String text, String zipLocation, String up, String down) {
        CheckBoxFacade checkBoxFacade = new CheckBoxFacade(mBaseScreen, mBaseScreen.getmLuaEnvironment(), text, zipLocation, up, down);
        mBaseScreen.getmStage().addActor(checkBoxFacade.getActor());
        mInstances.put(id, checkBoxFacade);
        return new ExposedJavaClass(checkBoxFacade);
    }

    @ExposeToLua
    public LuaValue createButtonGroup(String id) {
        mButtonGroup = new ButtonGroupFacade();
        mInstances.put(id, mButtonGroup);
        return new ExposedJavaClass(mButtonGroup);
    }

    @ExposeToLua
    public void addToButtonGroup(String id) {
        if(mButtonGroup != null) {
            System.out.println("added " + id);
            mButtonGroup.add((ButtonSuper)mInstances.get(id));
        }
    }

    @ExposeToLua
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(mLabel, 0, 0);
		batch.end();*/
        mBaseScreen.getmStage().draw();
    }

    private class MBaseScreen extends BaseScreen{
        public MBaseScreen() {
            super();
        }
    }
}
