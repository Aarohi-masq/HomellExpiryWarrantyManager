package com.homell.Homell;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingAlertDialog {
    private Activity activity;
    private AlertDialog dialog;
    LoadingAlertDialog(Activity myActivity)
    {
        activity=myActivity;

    }
    void loadingdialog()
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.customdialog,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();

    }
    void dismissDialog()
    {
        dialog.dismiss();
    }
}
