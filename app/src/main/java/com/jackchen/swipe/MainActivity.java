package com.jackchen.swipe;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @email :  2185134304@qq.com
 * @date :2017/12/23
 * @author : Jack-Chen
 * @Description: SwipeRefreshLayout
 *
 */

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeLayout;
    private ListView listview;

    private boolean isRefresh = false ; //默认是不刷新的
    private List<String> datas;
    private LVAdapter mLvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView() ;
        initData() ;

    }

    private void initData() {
        datas = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            datas.add("这个第" + i + "个数据") ;
        }

        mLvAdapter = new LVAdapter(this , datas);
        listview.setAdapter(mLvAdapter);

    }

    public void initView() {
        swipeLayout = (SwipeRefreshLayout)  findViewById(R.id.swipeLayout);
        listview = (ListView) findViewById(R.id.listview);

        //设置进度条的颜色主题，最多能设置四种，加载颜色是循环播放的，只要没完成刷新就会一直循环
        swipeLayout.setColorSchemeColors(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        //上边方法已经废弃，但是用这个方法的话，进度条的圆圈是没有颜色的
        swipeLayout.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);

        // 设置手指在屏幕下边 下拉多少距离会触发下拉刷新
        swipeLayout.setDistanceToTriggerSync(350);
        // 设置下拉圆圈的背景
        swipeLayout.setProgressBackgroundColorSchemeColor(Color.RED);
        // 设置下拉圆圈的大小
        swipeLayout.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉刷新的监听
        swipeLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        if (isRefresh){   //表示没有正在刷新
            return;
        }else{
            isRefresh = true ; //表示正在刷新
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    swipeLayout.setRefreshing(false);

                    //刷新时候清除数据，然后重新请求所有数据 并添加到集合
                    //下边是自己修改的
                    datas.clear();
                    for (int i = 1; i < 10; i++) {
                        datas.add("这是第" + i + "个数据");
                    }

                    mLvAdapter.notifyDataSetChanged();
                    isRefresh = false ;
                }
            } , 3000) ;
        }
    }
}
