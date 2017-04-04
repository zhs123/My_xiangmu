package costomview.library;

import android.os.Bundle;

/**
 * Created by zhanghaisheng on 2016/11/8.
 */
public interface  IOnCreate {
    /**
     * 返回布局文件ID
     * @return
     */
    int bindLayout();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    void initView(Bundle savedInstanceState);

    /**
     * 加载网络数据  oncreate方法自动调用
     */
    void loadData();
}
