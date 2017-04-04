package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.my_xiangmu.DetailsActivity;
import com.bwei.my_xiangmu.HomeActivity;
import com.bwei.my_xiangmu.R;
import com.bwei.my_xiangmu.RiliActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapter.CommonViewHolder;
import adapter.RecyclerViewCommonAdpter;
import adapter.RecyclerViewCommonAdpter.OnItemClickListener;
import bean.BeanResult;
import utils.OkHttpUtils;

/**
 * 1.类的用途
 * 2.@author:zhanghaisheng
 * 3.@2017/3/15
 * 历史上的今天页面
 */


public class ShangHaiFragment extends Fragment {
    private String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=10/10";
    private View view;
    private XRecyclerView shang_xrecyclerview;
    private FloatingActionButton button;
    private List<BeanResult.ResultBean> namelist;
    private RecyclerViewCommonAdpter<BeanResult.ResultBean> resultBeanRecyclerViewCommonAdpter;
    private ImageView jump;
    private TextView text_riqi;
    private Calendar mCalendar;
    String year;
    String month;
    String day;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shanghai_fragent, container, false);
        initData();
        return view;
    }

    private void initData() {
        OkHttpUtils.getInstance().getRequest(url, BeanResult.class, new OkHttpUtils.RequestJsonBeanCallBack<BeanResult>() {
            @Override
            public void success(BeanResult result) {
                Log.e("data-------", result.toString());
                namelist = result.getResult();
                intiView();
                // addData(result);
            }
            @Override
            public void error() {
                Log.e("error-------", "ggggg");
            }
        });
    }
    private void intiView() {
        shang_xrecyclerview = (XRecyclerView) view.findViewById(R.id.shang_xrecyclerview);
        shang_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        button = (FloatingActionButton) view.findViewById(R.id.button);
        text_riqi = (TextView) view.findViewById(R.id.text_riqi);
        jump = (ImageView) view.findViewById(R.id.jump);
        mCalendar=Calendar.getInstance();
        nextDay();
        OnClickListener();
        shang_xrecyclerview.setLoadingMoreEnabled(true);
        shang_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                shang_xrecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                shang_xrecyclerview.loadMoreComplete();
            }
        });
        // namelist=new ArrayList<>();
        resultBeanRecyclerViewCommonAdpter = new RecyclerViewCommonAdpter<BeanResult.ResultBean>(getActivity()
                , R.layout.shang_item, namelist) {

            @Override
            public void conver(CommonViewHolder holder, BeanResult.ResultBean resultBean) {
                holder.getTextView(R.id.text1).setText(resultBean.getDate());
                holder.getTextView(R.id.text2).setText(resultBean.getTitle());
            }
        };
        resultBeanRecyclerViewCommonAdpter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("e_id", namelist.get(position).getE_id());
                getActivity().startActivity(intent);
            }
        });
        shang_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        shang_xrecyclerview.setAdapter(resultBeanRecyclerViewCommonAdpter);
    }

    private void OnClickListener() {
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RiliActivity.class);
                startActivityForResult(intent,110);
            }
        });
    }

    private void addData(BeanResult bean) {
        namelist.addAll(bean.getResult());
        resultBeanRecyclerViewCommonAdpter.notifyDataSetChanged();
    }
    private void nextDay() {
        Date time = mCalendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(time);
        Log.e("date------------->", date);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String date = data.getStringExtra("date");
        if(date!=null) {

            String[] split = date.split("-");
            year = split[0];
            month = split[1];
            day = split[2];
            text_riqi.setText(year + "年" + month + "月" + day + "日");

            int intmonth = Integer.parseInt(month);
            int intday = Integer.parseInt(day);
            url = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=" + intmonth + "/" + intday;
            initData();
            resultBeanRecyclerViewCommonAdpter.notifyDataSetChanged();
            Log.e("date------------->", date + "///" + intmonth);
        }

    }


}
