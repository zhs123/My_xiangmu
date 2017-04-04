package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
*1.类的用途
*2.zhanghaisheng
*3.2017/2/27
**/

public class CommonViewHolder extends RecyclerView.ViewHolder{
    /**
     * 上下文
     */
    private final Context mContext;
    /**
     * 使用集合来存储item上的控件
     */
    private SparseArray<View> mViewList;

    /**
     * 加载item的布局
     */
    private View mConvertView;


    private CommonViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        mViewList = new SparseArray<View>();
    }

    /**
     *
     * 获取ViewHolder的方法
     *
     * @param context 上下文
     * @param layoutID 布局ID
     * @param parent 父控件
     * @return 返回当前的布局ID所对应的ViewHolder的实例
     */
    public static CommonViewHolder getViewHolder(Context context, int layoutID, ViewGroup parent){
        //将布局ID转化为视图
       View itemView = LayoutInflater.from(context).inflate(layoutID,parent,false);
        //实例化当前ViewHolder
        CommonViewHolder viewHolder = new CommonViewHolder(context, itemView);
        //返回
        return viewHolder;
    }
    public static  CommonViewHolder createViewHolder(Context context,View itemView){
        CommonViewHolder viewHolder = new CommonViewHolder(context,itemView);
        return viewHolder;
    }
    /**
     *
     * 通过ID取控件的方法 对ItemView的重用已经进行了缓存
     *
     * @param viewID 控件的ID
     * @return 返回id所对应的控件
     */
    public <T extends View>T getView(int viewID){
        //从集合中取控件
        View view = mViewList.get(viewID);
        //如果没有就通过findViewById创建一个,并缓存到集合中
        if(view == null){
            view = mConvertView.findViewById(viewID);
            mViewList.put(viewID,view);
        }
        return (T) view;
    }
    public TextView getTextView(int viewId){
        TextView textView=getView(viewId);

        return textView;
    }
    public CommonViewHolder setText(int viewId,String s){
        TextView textView=getView(viewId);
        textView.setText(s);
        return  this;
    }
    public CommonViewHolder setimageResouse(int viewID,int resID){
        ImageView imageView = getView(viewID);
        imageView.setImageResource(resID);
        return this;
    }

    public CommonViewHolder setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public CommonViewHolder setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public CommonViewHolder setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public CommonViewHolder setTextColorRes(int viewId, int textColorRes)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public CommonViewHolder setOnClickLisenter(int viewID,View.OnClickListener listener){
        View view = getView(viewID);
        view.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public CommonViewHolder setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }
}
