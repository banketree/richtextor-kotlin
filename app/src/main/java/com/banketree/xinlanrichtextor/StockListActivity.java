package com.banketree.xinlanrichtextor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MryU93 on 2017/6/13.
 * Desc:
 */

public class StockListActivity extends AppCompatActivity {

    public final static String DATA = "data";

    ListView userList;

    private List<UserModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        userList = findViewById(R.id.user_list);

        for (int i = 0; i < 50; i++) {
            UserModel userModel = new UserModel();
            userModel.setName("股票代码" + i);
            userModel.setId(i * 30 + "");
            data.add(userModel);
        }

        ArrayAdapter adapter = new ArrayAdapter<UserModel>(this, R.layout.user_list_item, data);
        userList.setAdapter(adapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(DATA, data.get(position));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
