package com.restoorders.View.BaseViews;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by akshayborgave on 30/07/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private String progressMessage = "Loading...";


    public String getProgressMessage() {
        return progressMessage;
    }

    public void setProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
    }

    public void showProgress() {

        if (mProgressDialog == null) {

            mProgressDialog = new ProgressDialog(this);

        }
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getProgressMessage());
        mProgressDialog.show();


    }

    public void hideProgress() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {

            mProgressDialog.dismiss();
        }

    }


}
