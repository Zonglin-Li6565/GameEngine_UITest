package com.tsuru2d.engine.uiapi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.tsuru2d.engine.BaseScreen;
import com.tsuru2d.engine.loader.AssetID;
import com.tsuru2d.engine.loader.AssetObserver;
import com.tsuru2d.engine.loader.ManagedAsset;
import com.tsuru2d.engine.lua.ExposeToLua;
import com.tsuru2d.engine.util.DrawableLoader;
import org.luaj.vm2.LuaFunction;

import org.luaj.vm2.LuaTable;

public class ButtonFacade extends ButtonSuper {
    private ManagedAsset<String> mText;
    private ManagedAsset<String> mPthtoPrsBttn;
    private ManagedAsset<String> mPthtoNotPrsBttn;
    private TextObserver mObserver;
    private ChangeHandler mChangeHandler;
    private LuaFunction mCallBack;
    private TextButton.TextButtonStyle mTextButtonStyle;
    private BitmapFont mFont;
    private DrawableLoader mDrawableLoader;             //For test
    private final TextButton mButton;

    public ButtonFacade(BaseScreen screen, LuaTable luaTable, String zipLocation, String upImage, String downImage) {
        super(screen, luaTable);
        try {
            mDrawableLoader = new DrawableLoader(zipLocation);
        } catch (Exception e) {
            System.out.println("no Zip file found");
        }

        System.out.println("button created");
        mFont = new BitmapFont();
        mObserver = new TextObserver();
        mTextButtonStyle = new TextButton.TextButtonStyle();
        //Drawable drawable = mDrawableLoader.getDrawable("image/checkboxes_notChecked.jpg");
        Drawable drawableUp = mDrawableLoader.getDrawable(upImage);
        mTextButtonStyle.up = drawableUp;
        Drawable drawableDown = mDrawableLoader.getDrawable(downImage);
        mTextButtonStyle.down = drawableDown;
        mTextButtonStyle.font = mFont;
        mButton = new TextButton(null, mTextButtonStyle);
        mChangeHandler = new ChangeHandler();

        //test
        mTextButtonStyle.fontColor = Color.BLUE;
    }

    @ExposeToLua
    //public void setText(AssetID text) {
    public void setText(String text) {
        dispose();
        //mText = mScreen.getAssetLoader().getText(text);
        //mText.addObserver(mObserver);
        //mButton.setText(mText.get());
        mButton.setText(text);
    }

    @ExposeToLua
    public void setOnClick(LuaFunction callBack) {
        mCallBack = callBack;
        if(mCallBack != null) {
            System.out.println("call back function added");
            mButton.addListener(mChangeHandler);
        }else {
            //mButton.removeListener(mChangeHandler);
        }

    }

    @ExposeToLua
    public void setSize(float width, float height) {
        mButton.setSize(width, height);
    }

    @ExposeToLua
    //public void setImagewhenClicked(AssetID asset) {
    public void setImagewhenClicked(String location) {
        //mPthtoPrsBttn = mScreen.getAssetLoader().getText(asset);
        //Drawable drawable = mDrawableLoader.getDrawable(mPthtoPrsBttn.get());
        Drawable drawable = mDrawableLoader.getDrawable(location);
        mTextButtonStyle.down = drawable;
    }

    @ExposeToLua
    //public void setIMagewhenNotClicked(AssetID asset) {
    public void setIMagewhenNotClicked() {
        //mPthtoNotPrsBttn = mScreen.getAssetLoader().getText(asset);
        //Drawable drawable = mDrawableLoader.getDrawable(mPthtoNotPrsBttn.get());
        Drawable drawable = mDrawableLoader.getDrawable("image/checkboxes_notChecked.jpg");
        mTextButtonStyle.up = drawable;
    }


    @Override
    public TextButton getActor() {
        return mButton;
    }

    @Override
    public void dispose() {
        if (mText != null && mObserver != null) {
            mText.removeObserver(mObserver);
            //mScreen.getAssetLoader().freeAsset(mText);
        }
    }

    private class ChangeHandler extends ChangeListener {
        /*@Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("clicked");
            if(mCallBack != null) {
                mCallBack.call();
            }
        }*/

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            //System.out.println("clicked");
            if(mCallBack != null) {
                mCallBack.call();
            }
        }
    }

    private class TextObserver implements AssetObserver<String> {

        @Override
        public void onAssetUpdated(ManagedAsset<String> asset) {
            mButton.setText(asset.get());
        }
    }
}
