package com.manroid.reamldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private List<Obj> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        for (int i = 0; i < 10; i++) {
            Obj obj = new Obj();
            obj.setId(i + 1);
            obj.setName("123");
            data.add(obj);
        }

        RealmManager.initRealm(this);
        RealmManager.open();
        RealmManager.recordsController().saveObj(data);


    }

    public void onClickGet(View view) {
        List<Obj> list = RealmManager.recordsController().getObj();
        Log.d("RealmDatabase", list.size() + " ===========");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RealmManager.close();
    }

    public void onClickUpdate(View view) {
        Obj obj = new Obj();
        obj.setName("test");
        obj.setId(1);
        RealmManager.recordsController().saveObj(obj);
    }

    public void onClickDelete(View view) {
        RealmManager.recordsController().deleteObj(1);
    }

    public void onClickGetListName(View view) {
        List<Obj> list = RealmManager.recordsController().getListObj("123");
        for (Obj obj : list) {
            Log.d("RealmDatabase", obj.getName());
        }
    }
}
