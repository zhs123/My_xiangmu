package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwei.my_xiangmu.HomeActivity;
import com.bwei.my_xiangmu.R;


/**
 * 1.类的用途
 * 2.@author:zhanghaisheng
 * 3.@2017/3/15
 * 关于页面
 */

public class WithFragment extends Fragment {

    private View view;
    private ImageView guanyu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.with_fragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        guanyu = (ImageView) view.findViewById(R.id.guanyu);
        guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }


}
