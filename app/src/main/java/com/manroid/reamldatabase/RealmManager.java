package com.manroid.reamldatabase;

import android.app.Activity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {
    private static final String TAG = RealmManager.class.getSimpleName();
    private static Realm mRealm;
    static RealmConfiguration realmConfiguration;

    public static void initRealm(Activity activity) {
        Realm.init(activity);
        realmConfiguration = new RealmConfiguration.Builder()
                .name("ReamlManager.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static DBController recordsController() {
        checkForOpenRealm();
        return new DBController(mRealm);
    }

    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }
}
