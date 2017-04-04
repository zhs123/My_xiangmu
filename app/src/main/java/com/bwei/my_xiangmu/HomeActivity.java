package com.bwei.my_xiangmu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fragment.CollectFragment;
import fragment.GirlFragment;
import fragment.ShangHaiFragment;
import fragment.WithFragment;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static DrawerLayout drawerLayout;
    private ListView drawerlist;
    private List<String> menulist;
    private ArrayAdapter<String> adapter;
    private GirlFragment girlFragment;
    private ShangHaiFragment shangHaiFragment;
    private WithFragment withFragment;
    private CollectFragment collectFragment;
    private FragmentTransaction transaction;
    private boolean isExit = false;
    //创建Handler对象，用来处理消息
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//处理消息
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerlist = (ListView) findViewById(R.id.left_drawer);
        menulist = new ArrayList<>();

        menulist.add("历史上的今天");
        menulist.add("妹纸");
        menulist.add("收藏");
        menulist.add("关于");


        collectFragment = new CollectFragment();
        girlFragment = new GirlFragment();
        shangHaiFragment = new ShangHaiFragment();
        withFragment = new WithFragment();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, menulist);
        drawerlist.setAdapter(adapter);
        drawerlist.setOnItemClickListener(this);
        drawerLayout.closeDrawer(Gravity.LEFT);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, shangHaiFragment);
        transaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, shangHaiFragment);
                drawerLayout.closeDrawer(Gravity.LEFT);
                transaction.commit();
                break;
            case 1:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, girlFragment);
                drawerLayout.closeDrawer(Gravity.LEFT);
                transaction.commit();
                break;
            case 2:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, collectFragment);
                drawerLayout.closeDrawer(Gravity.LEFT);
                transaction.commit();
                break;
            case 3:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, withFragment);
                drawerLayout.closeDrawer(Gravity.LEFT);
                transaction.commit();
                break;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ic_launcher)
                        .setTitle("提示！")
                        .setMessage("确认退出程序？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
        }
        return false;
    }
}
