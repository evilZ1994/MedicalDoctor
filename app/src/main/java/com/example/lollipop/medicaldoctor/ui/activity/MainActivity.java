package com.example.lollipop.medicaldoctor.ui.activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lollipop.medicaldoctor.R;
import com.example.lollipop.medicaldoctor.app.App;
import com.example.lollipop.medicaldoctor.ui.fragment.InfoFragment;
import com.example.lollipop.medicaldoctor.ui.fragment.MainFragment;
import com.example.lollipop.medicaldoctor.ui.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private Fragment mainFragment;
    private Fragment infoFragment;
    private Fragment settingFragment;

    //点击返回键时的时间，用于控制双击退出
    private long mPressedTime = 0;

    @BindView(R.id.d_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.d_drawer_layout)
    DrawerLayout drawerLayout;

    private TextView nameText;
    private TextView hospitalText;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
        setStatusBar();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

        //侧边栏显示姓名和医院
        headerView = navigationView.getHeaderView(0);
        nameText = (TextView) headerView.findViewById(R.id.doctor_name);
        hospitalText = (TextView) headerView.findViewById(R.id.hospital);
        nameText.setText(App.getCurrentUser().getName());
        hospitalText.setText(App.getCurrentUser().getHospital());

        fragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragment();
        infoFragment = new InfoFragment();
        settingFragment = new SettingFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, mainFragment);
        transaction.commit();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setStatusBar() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.home:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, mainFragment);
                transaction.commit();
                break;
            case R.id.person_info:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, infoFragment);
                transaction.commit();
                break;
            case R.id.setting:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, settingFragment);
                transaction.commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 重写onBackPressed()方法，设置双击退出，时间差500毫秒
     */
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        if (mNowTime - mPressedTime > 500){
            Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {
            //退出程序
            //JMessage登出
            JMessageClient.logout();
            //结束程序
            this.finish();
            System.exit(0);
        }
    }
}
