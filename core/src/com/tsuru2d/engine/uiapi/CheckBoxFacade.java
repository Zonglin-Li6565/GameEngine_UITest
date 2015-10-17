package com.tsuru2d.engine.uiapi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.tsuru2d.engine.BaseScreen;
import com.tsuru2d.engine.loader.AssetID;
import com.tsuru2d.engine.loader.AssetObserver;
import com.tsuru2d.engine.loader.ManagedAsset;
import com.tsuru2d.engine.lua.ExposeToLua;
import com.tsuru2d.engine.util.DrawableLoader;
import org.luaj.vm2.LuaTable;

public class CheckBoxFacade extends ButtonSuper {
    private ManagedAsset<String> mText;
    private TextObserver mObserver;
    private BitmapFont mFont;
    private DrawableLoader mDrawableLoader;             //For test
    private CheckBox.CheckBoxStyle mCheckBoxStyle;
    private final CheckBox mCheckBox;

    public CheckBoxFacade(BaseScreen screen, LuaTable data, String text, String zipLocation, String upImage, String downImage) {
        super(screen, data);
        try {
            mDrawableLoader = new DrawableLoader(zipLocation);
        } catch (Exception e) {
            System.out.println("no Zip file found");
        }
        System.out.println("check box created");
        mFont = new BitmapFont();
        mCheckBoxStyle = new CheckBox.CheckBoxStyle();
        mCheckBoxStyle.checkboxOff = mDrawableLoader.getDrawable(upImage);
        mCheckBoxStyle.checkboxOn = mDrawableLoader.getDrawable(downImage);
        mCheckBoxStyle.font = mFont;
        mObserver = new TextObserver();
        mCheckBox = new CheckBox(text, mCheckBoxStyle);

        //test
        mCheckBoxStyle.fontColor = Color.BLUE;
    }

    @ExposeToLua
    //public void setText(AssetID text) {
    public void setText(String text) {
        //dispose();
        //mText = mScreen.getAssetLoader().getText(text);
        //mCheckBox.setText(mText.get());
        //mText.addObserver(mObserver);
        mCheckBox.setText(text);
    }

    @ExposeToLua
    public boolean isChecked() {
        return mCheckBox.isChecked();
    }

    @Override
    @ExposeToLua
    public void setPosition(float x, float y) {
        mCheckBox.setPosition(x, y);
    }

    @ExposeToLua
    public void setChecked(boolean checked) {
        mCheckBox.setChecked(checked);
    }

    @Override
    public CheckBox getActor() {
        return mCheckBox;
    }

    @Override
    public void dispose() {
        if (mText != null && mObserver != null) {
            mText.removeObserver(mObserver);
            //mScreen.getAssetLoader().freeAsset(mText);
        }
    }

    private class TextObserver implements AssetObserver<String> {

        @Override
        public void onAssetUpdated(ManagedAsset<String> asset) {
            mCheckBox.setText(asset.get());
        }
    }
}