package com.example.jni;

import android.app.Activity;

public class JavaMiddleWare {
    private Activity activity;

    public JavaMiddleWare(Activity activity) {
        this.activity = activity;
    }

    public void startComputeFactorial(int n) {
        computeFactorial(n);
    }

    private void displayResult(String result) {
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).showResult(result);
        }
    }

    private native void computeFactorial(int n);
}