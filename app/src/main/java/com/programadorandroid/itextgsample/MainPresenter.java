package com.programadorandroid.itextgsample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class MainPresenter {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 111;
    private View view;
    private Activity activity;

    MainPresenter(View view){
        this.view = view;
    }

    void onClickCreatePdfButton(Activity activity){

        //Check permissions before create pdf
        int writeExternalStorageCurrentPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //If the user hasn't given us permissions we must ask
        if (writeExternalStorageCurrentPermission != PackageManager.PERMISSION_GRANTED) {
            // Request the permission.
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            createPdf();
        }
    }

    private void createPdf(){
        CreateSamplePDF createSamplePDF = new CreateSamplePDF();
        if(createSamplePDF.execute()){
            view.showMessage(R.string.createPdfOk);
        } else{
            view.showMessage(R.string.createPdfError);
        }
    }

    public void writeExternalStoragePermissionsGranded() {
        createPdf();
    }

    interface View {
        void showMessage(int messageReference);
    }
}
