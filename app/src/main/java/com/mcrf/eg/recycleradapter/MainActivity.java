package com.mcrf.eg.recycleradapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.mcrf.eg.recycleradapter.R;
import java.util.ArrayList;
import java.util.List;


/*
 * By Github MCRuiFeng
 * Email:dev.mcrf@qq.com
 * 2017.05.25
 */
public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		//RecyclerView
        initListData();
    }



	private void initListData() {
		final List<String> listDatas = new ArrayList<String>();
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        RecyclerAdapter listAdapter = new RecyclerAdapter(this, listDatas);
		
		//Datas
        listDatas.add("检查更新");
		listDatas.add("更新日志");
		listDatas.add("功能介绍");

		//点击事件
        listAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener()
			{
				@Override
				public void onItemClick(View view, int position) {
					Toast.makeText(MainActivity.this, "点击：" + listDatas.get(position), 1000).show();
				}
			});
        recyclerView.setAdapter(listAdapter);
		//分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		//动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
		//布局方向
		recyclerView.setLayoutManager(new LinearLayoutManager(this)
			{
				//不可滑动
				@Override
				public boolean canScrollVertically() {
					return false;
				}
			});
    }

	
}
