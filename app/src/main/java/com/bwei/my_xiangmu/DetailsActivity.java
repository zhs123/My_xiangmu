package com.bwei.my_xiangmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import bean.DetaBean;
import costomview.library.Glideimageutils;
import utils.OkHttpUtils;


public class DetailsActivity extends AppCompatActivity {
    private String url="http://v.juhe.cn/todayOnhistory/queryDetail.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&e_id=";
    private TextView deta_content;
    private ImageView deta_background;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        initData();
    }
    private void initView() {
        deta_content = (TextView) findViewById(R.id.deta_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        deta_background = (ImageView) findViewById(R.id.deta_background);

    }

    private void initData() {
        Intent intent =getIntent();
        //position = Integer.parseInt(intent.getStringExtra("e_id"));
        String eid = intent.getStringExtra("e_id");
        OkHttpUtils.getInstance().getRequest(url+eid, DetaBean.class, new OkHttpUtils.RequestJsonBeanCallBack<DetaBean>() {

            @Override
            public void success(DetaBean result) {
                deta_content.setText(result.getResult().get(0).getContent());
                toolbar.setTitle(result.getResult().get(0).getTitle());
                 Glideimageutils.Imagelode(DetailsActivity.this,result.getResult().get(0).getPicUrl().get(0).getUrl(),deta_background);
            }

            @Override
            public void error() {

            }
        });


    }
}
