package com.mabeijianxi.jianxiexpression;

public class ExpressionManager {

    private ExpressionGridFragment.ExpressionClickListener mExpressionClickListener;
    private ExpressionGridFragment.ExpressionaddRecentListener mExpressionaddRecentListener;
    private ExpressionGridFragment.ExpressionDeleteClickListener mExpressionDeleteClickListener;

    private volatile static ExpressionManager instance = null;

    // 私有化构造方法
    private ExpressionManager() {
    }

    public static ExpressionManager getInstance() {
        if (instance == null) {
            synchronized (ExpressionManager.class) {
                if (instance == null) {
                    instance = new ExpressionManager();
                }
            }

        }
        return instance;
    }

    public void release() {
        mExpressionClickListener = null;
        mExpressionaddRecentListener = null;
        mExpressionDeleteClickListener = null;
        instance = null;
    }


    public ExpressionGridFragment.ExpressionClickListener getExpressionClickListener() {
        return mExpressionClickListener;
    }

    public void setExpressionClickListener(ExpressionGridFragment.ExpressionClickListener expressionClickListener) {
        this.mExpressionClickListener = expressionClickListener;
    }

    public ExpressionGridFragment.ExpressionaddRecentListener getExpressionaddRecentListener() {
        return mExpressionaddRecentListener;
    }

    public void setExpressionaddRecentListener(ExpressionGridFragment.ExpressionaddRecentListener expressionaddRecentListener) {
        this.mExpressionaddRecentListener = expressionaddRecentListener;
    }

    public ExpressionGridFragment.ExpressionDeleteClickListener getExpressionDeleteClickListener() {
        return mExpressionDeleteClickListener;
    }

    public void setExpressionDeleteClickListener(ExpressionGridFragment.ExpressionDeleteClickListener expressionDeleteClickListener) {
        this.mExpressionDeleteClickListener = expressionDeleteClickListener;
    }
}
