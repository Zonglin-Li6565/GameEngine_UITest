package com.tsuru2d.engine.uiapi;

import com.tsuru2d.engine.BaseScreen;

import java.util.HashMap;

public class RelativeLayout {

    /*The way to convenient the game designer to define the distance between each elements */
    private float mTempWidth, mTempHeight;
    private float mWindowWidth, mWindowHeight;
    private HashMap<UIWrapper, RelativePosition> mActors;



    public RelativeLayout(BaseScreen baseScreen) {
        mTempWidth = -1;
        mTempHeight = -1;
        mActors = new HashMap<UIWrapper, RelativePosition>();
        mWindowWidth = baseScreen.getWidth();
        mWindowHeight = baseScreen.getHight();
    }

    public void setBaseElement(UIWrapper ui, float toLeft, float toBottom) {
        mActors.put(ui, new RelativePosition(toLeft, toBottom));
        ui.getActor().setPosition(toLeft * mWindowWidth, toBottom * mWindowHeight);
    }

    public void setTemporaryScreenSize(float width, float height) {
        mTempWidth = width;
        mTempHeight = height;
    }

    public void setRelativeLocation(UIWrapper base, UIWrapper relative, float xproportion) {
        RelativePosition rpBase = mActors.get(base);
    }

    private class RelativePosition {
        public float mXproportion;
        public float mYproportion;

        public RelativePosition(float xproportion, float yproportion) {
            mXproportion = xproportion;
            mYproportion = yproportion;
        }
    }
}
