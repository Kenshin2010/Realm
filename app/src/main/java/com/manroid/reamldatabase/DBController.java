package com.manroid.reamldatabase;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBController {

    private Realm mRealm;

    public DBController(@Nonnull Realm realm) {
        this.mRealm = realm;
    }

    public void saveObj(final List<Obj> data) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(data);
            }
        });
    }

    public void saveObj(final Obj data) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Obj obj = realm.where(Obj.class).equalTo("id", data.getId()).findFirst();
                if (obj == null) {
                    obj = realm.createObject(Obj.class, data.getId());
                    return;
                }
                obj.setName(data.getName());
                mRealm.copyToRealmOrUpdate(obj);
            }
        });
    }

    public void deleteObj(final int id) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Obj> result = realm.where(Obj.class).equalTo("id", id).findAll();
                result.deleteAllFromRealm();
            }
        });
    }

    public List<Obj> getListObj(String name) {
        return mRealm.where(Obj.class).equalTo("name", name).findAll();
    }


    public List<Obj> getObj() {
        return mRealm.where(Obj.class).findAll();
    }
}

