package com.mcrf.eg.recycleradapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.mcrf.eg.recycleradapter.R;
import java.util.ArrayList;
import java.util.List;

/*
 * By Github MCRuiFeng
 * Email:dev.mcrf@qq.com
 * 2017.05.21
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mListAdapter;
    private List<String> mDatas;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.main_rv);
        mListAdapter = new RecyclerAdapter(this, mDatas);
        mListAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener()
			{
				@Override
				public void onItemClick(View view, int position)
				{
					Toast.makeText(MainActivity.this, "Click" + mDatas.get(position), Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onItemLongClick(View view, int position)
				{
					mListAdapter.remove(position); //remove the item
					Toast.makeText(MainActivity.this, "LongClick" + mDatas.get(position), Toast.LENGTH_SHORT).show();
				}
			});
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this)
			{
				//设置不可滑动
				@Override
				public boolean canScrollVertically()
				{
					return false;
				}
			});

    }
	
	

	protected void initData()
	{
        mDatas = new ArrayList<String>();
        mDatas.add("检查更新");
		mDatas.add("更新日志");

    }

	
	
	/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
        getMenuInflater().inflate(R.menu.menu_list_layout, menu);
        return true;
    }

	
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
        switch (item.getItemId())
		{
            case R.id.add_first:
                mListAdapter.add(0, "add first");
                break;
            case R.id.add_last:
                mListAdapter.add(mListAdapter.getItemCount(), "add last");
                break;
            case R.id.remove_first:
                String value = mListAdapter.remove(0);
                Toast.makeText(MainActivity.this, "remove:" + value, Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_last:
                String value1 =  mListAdapter.remove(mListAdapter.getItemCount() - 1);
                Toast.makeText(MainActivity.this, "remove:" + value1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.horizontal:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
                break;
            case R.id.vertical:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
	*/

}
