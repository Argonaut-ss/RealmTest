package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLOutput;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSimpan, btnTampil;
    EditText sportNametxt, sportFormattxt, sportDescriptiontxt;
    ImageView sportPic;
    String sportNamestr, sportFormatstr, sportDescriptionstr;
    Realm realm;
    RealmHelper realmHelper;
    SportModel sportModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi
        btnSimpan = findViewById(R.id.btnSimpan);
        btnTampil = findViewById(R.id.btnTampil);
        sportNametxt = findViewById(R.id.namaSport);
        sportFormattxt = findViewById(R.id.formatSport);
        sportDescriptiontxt = findViewById(R.id.deskripsiSport);
        sportPic = findViewById(R.id.gambarSport);

        //set up Realm
        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            sportNamestr = sportNametxt.getText().toString();
            sportFormatstr = sportFormattxt.getText().toString();
            sportDescriptionstr = sportDescriptiontxt.getText().toString();

            if (!sportNamestr.isEmpty()){
                sportModel = new SportModel();

                sportModel.setSportName(sportNamestr);
                sportModel.setFormatSport(sportFormatstr);
                sportModel.setSportDescription(sportDescriptionstr);


                realmHelper = new RealmHelper(realm);
                realmHelper.save(sportModel);

                Toast.makeText(MainActivity.this, "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                sportNametxt.setText("");
                sportFormattxt.setText("");
                sportDescriptiontxt.setText("");
            }else {
                Toast.makeText(MainActivity.this, "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
            }
        }else if (v == btnTampil){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }
    }
}