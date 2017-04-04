package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwei.my_xiangmu.HomeActivity;
import com.bwei.my_xiangmu.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import bean.girlBean;
import costomview.library.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 1.妹纸页面
 * 2.@author:zhanghaisheng
 * 3.@2017/3/15
 */
public class GirlFragment extends Fragment {
    private View view;
    private XRecyclerView recyclerview;
    private girlBean bean;
    private List<girlBean.ResultsBean> namelist;
    private MyAdapter myAsder;
    private int page = 1;
    private GridLayoutManager grid;
    private String url="http://gank.io/api/data/福利/20/1";
    private FloatingActionButton mFloatBtn;
    private List<girlBean.ResultsBean> list=new ArrayList<>();
    Handler hand=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= (String) msg.obj;
            setData(str);
        }
    };
    private ImageView imageviewgirl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.girl_fragment,container,false);
        initView();
        initData(url);
        return view;
    }

    private void initData(String url) {
        OkHttpUtils.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str=response.body().string();
                Message message=Message.obtain();
                message.obj=str;
                hand.sendMessageDelayed(message,100);
            }
        });
    }
    private void initView() {
        recyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);
        recyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mFloatBtn = (FloatingActionButton) view.findViewById(R.id.floating_btn_main);
        imageviewgirl = (ImageView) view.findViewById(R.id.imageviewgirl);
        grid = new GridLayoutManager(getActivity(),2);
        recyclerview.setLayoutManager(grid);
        recyclerview.setLoadingMoreEnabled(true);
        imageviewgirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                page++;
                url="http://gank.io/api/data/福利/20/"+page;
                initData(url);
                recyclerview.refreshComplete();
                myAsder.notifyDataSetChanged();
            }
            @Override
            public void onLoadMore() {
                page++;
                url="http://gank.io/api/data/福利/19/"+page;
                initData(url);
                recyclerview.loadMoreComplete();
                myAsder.notifyDataSetChanged();
            }
        });
        //悬浮按钮的点击事件的监听
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recyclerview返回到顶部
                recyclerview.smoothScrollToPosition(0);
            }
        });
    }
    private void setData(String str) {
        Gson gson=new Gson();
        bean=gson.fromJson(str, girlBean.class);
        namelist=bean.getResults();
        list.addAll(namelist);
        if (myAsder==null)
        {
            Log.e("TAG","111111");
            myAsder = new MyAdapter(list,getContext());
            recyclerview.setAdapter(myAsder);
        }
    }
}
