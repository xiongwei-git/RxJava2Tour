package com.tedxiong.rxjava2.action;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 */
public abstract class BaseAction {
    protected final String TAG = this.getClass().getSimpleName();

    private ActionUiResult mUiResult;

    public BaseAction(ActionUiResult uiResult) {
        this.mUiResult = uiResult;
    }

    protected void updateUI(String result) {
        if (null != mUiResult)
            mUiResult.show(result);
    }

    public interface ActionUiResult {
        void show(String result);
    }

    public abstract void run();
}
