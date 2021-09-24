package com.homer.dialogmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import homer.dialogmanager.DialogManager;
import homer.dialogmanager.MyDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDialog dialog = new MyDialog(1);
        MyDialog dialog1 = new MyDialog(2);
        dialog1.setCanShow(false);
        MyDialog dialog2 = new MyDialog(3);
        MyDialog dialog3 = new MyDialog(4);

        DialogManager.getInstance().addDialog(dialog, this);
        DialogManager.getInstance().addDialog(dialog1, this);
        DialogManager.getInstance().addDialog(dialog2, this);
        DialogManager.getInstance().addDialog(dialog3, this);
        ConstraintLayout container = findViewById(R.id.cl_contianer);
        container.postDelayed(() -> {
            DialogManager.getInstance().show(this);
        }, 1000);

        container.postDelayed(() -> {
            dialog1.setCanShow(true);
        }, 1000 * 2);

    }
}