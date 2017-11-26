package com.programadorandroid.itextgsample;

import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{

    private MainPresenter presenter;
    private View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views
        mainLayout = findViewById(R.id.mainLayout);

        //Presenter
        presenter = new MainPresenter(this);
    }

    public void onClickCreatePdfButton(View view){
        presenter.onClickCreatePdfButton(this);
    }

    @Override
    public void showMessage(int messageReference) {
        Snackbar.make(mainLayout, getString(messageReference), Snackbar.LENGTH_LONG).show();
    }

    /**
     Recibimos la respuesta a la solicitud de permisos
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MainPresenter.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                //If the user gives us permissions
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.writeExternalStoragePermissionsGranded();
                } else {
                    showMessage(R.string.writeExternalStoragePermissisonsNedded);
                }
            }
        }
    }

}
