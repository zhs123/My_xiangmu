package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
*1.类的用途
*2.zhanghaisheng
*3.2017/2/27
**/

public abstract  class RecyclerViewCommonAdpter<T> extends RecyclerView.Adapter<CommonViewHolder>{
    //上下文
    Context context;
    //布局的id
    int layoutId;
    //数据的集合，因为我们不知道是什么数据，所以用泛型T
   List<T> list;
    //万能适配器所关联的viewholder
    CommonViewHolder commonViewHolder;

    public RecyclerViewCommonAdpter(Context context, int layoutId, List<T> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        commonViewHolder=CommonViewHolder.getViewHolder(context,layoutId,parent);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
            //我们不知道怎么赋值,所以写一个抽象方法
        conver(holder,list.get(position));
        if(onItemClickListener!=null){
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onItemClickListener.setOnItemClickListener(position);
               }
           });
        }
    }

    public abstract void conver(CommonViewHolder holder, T t);
    @Override
    public int getItemCount() {
        return list.size();
    }
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
   public interface  OnItemClickListener{
      void setOnItemClickListener(int position);
   }
}
