package costomview.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import okhttp3.Callback;

/**
 * Created by zhanghaisheng on 2016/11/8.
 */
public abstract class BaseActivity extends AppCompatActivity implements IOnCreate{
    private View MRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(bindLayout()!=0){
            MRootView=View.inflate(this,bindLayout(),null);
            setContentView(MRootView);
            initData();
            initView(savedInstanceState);
            loadData();

        }else{
            Log.e("Activity","bindLayout return 0 !");
        }
    }
    public View getMRootView(){
        return MRootView;
    }
    public void getAyn(String url, Callback callback){
        OkHttpUtils.get(url,callback);

    }
    public void getpost(String url, Callback callback){
        OkHttpUtils.get(url,callback);

    }
    public void showTost(CharSequence text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
    public void startAct(Class<BaseActivity> cls){
        Intent intent=new Intent(this,cls);
        startActivity(intent);
    }
    public void startAct(Class<? extends BaseActivity> cls,Bundle bundle){
        Intent intent=new Intent(this,cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void addFragment(){

    }
    public void removeFragment(){

    }
    public void replaceFragment(){

    }

    @Override
    protected void onDestroy() {
        MRootView=null;
        super.onDestroy();
    }
}
