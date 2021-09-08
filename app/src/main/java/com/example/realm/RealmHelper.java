package com.example.realm;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;

    }

    //Untuk Menyimpan data
    public void save(final SportModel sportModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(SportModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    sportModel.setId(nextId);
                    SportModel model = realm.copyToRealm(sportModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    //Untuk Memanggil semua data
    public List<SportModel> getAllMahasiswa(){
        RealmResults<SportModel> results = realm.where(SportModel.class).findAll();
        return results;
    }

    // untuk meng-update data
    public void update(final Integer id, String s, final String sportname, final String formatsport){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SportModel model = realm.where(SportModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setSportName(sportname);
                model.setFormatSport(formatsport);
                model.setSportDescription(spordescription);
                model.setSportPicture(sportpic);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<SportModel> model = realm.where(SportModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }





}
