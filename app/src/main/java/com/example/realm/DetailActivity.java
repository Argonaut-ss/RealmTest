package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSportName, etSportFormat, etSportDescription;
    String detailSportName, detailSportFormat, detailSportDescription;
    int id;
    Button btnupdate, btnhapus, btncancel;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set up
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        // Inisialisasi
        etSportName = findViewById(R.id.etSportName);
        etSportFormat = findViewById(R.id.etSportFormat);
        etSportDescription = findViewById(R.id.etDescriptionSport);
        btnupdate = findViewById(R.id.btnUpdate);
        btnhapus = findViewById(R.id.btnHapus);
        btncancel = findViewById(R.id.btnCancel);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        detailSportName = getIntent().getStringExtra("Nama");
        detailSportFormat = getIntent().getStringExtra("Format");
        detailSportDescription = getIntent().getStringExtra("Deskripsi");

        etSportName.setText(detailSportName);
        etSportFormat.setText(detailSportFormat);
        etSportDescription.setText(detailSportDescription);

        btnupdate.setOnClickListener(this);
        btnhapus.setOnClickListener(this);
        btncancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnupdate){
            realmHelper.update(id, etSportFormat.getText().toString(),etSportName.getText().toString(), etSportDescription.getText().toString());
            Toast.makeText(DetailActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
            etSportName.setText("");
            etSportFormat.setText("");
            etSportDescription.setText("");

            finish();
        }else if (v == btnhapus) {
            realmHelper.delete(id);
            Toast.makeText(DetailActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
            finish();
        }else if (v == btncancel) {
            startActivity(new Intent(DetailActivity.this, SportActivty.class));
            finish();
        }
    }


}