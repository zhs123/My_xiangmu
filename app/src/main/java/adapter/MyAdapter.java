package adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwei.my_xiangmu.GirlActivity;
import com.bwei.my_xiangmu.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import bean.girlBean;
import costomview.library.Glideimageutils;

/**
 * 1.类的用途
 * 2.@author:zhanghaisheng
 * 3.@2017/3/16
 */


public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<girlBean.ResultsBean> namelist;
    private Context context;

    public MyAdapter(List<girlBean.ResultsBean> namelist, Context context) {
        this.namelist = namelist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gire_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glideimageutils.Imagelode(context,namelist.get(position).getUrl(),holder.imageview1);
        holder.imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GirlActivity.class);
                intent.putExtra("image", namelist.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return namelist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview1;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageview1 = (ImageView) itemView.findViewById(R.id.imageview1);
        }
    }
}
