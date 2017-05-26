package com.mcrf.eg.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mcrf.eg.recycleradapter.R;
import java.util.List;


/* 最普通的RecyclerView适配器，只能显示一个Title
 * By Github MCRuiFeng
 * Email:dev.mcrf@qq.com
 * 2017.05.25
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>
{

    private List<String> listDatas;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public RecyclerAdapter(Context context, List<String> listDatas)
	{
        this.listDatas = listDatas;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount()
	{
        return listDatas.size();
    }


	@Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i)
	{

		itemViewHolder.title.setText(listDatas.get(i));

		if (onItemClickListener != null)
		{
            if (!itemViewHolder.itemView.hasOnClickListeners())
			{
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v)
						{
							int pos = itemViewHolder.getPosition();
							onItemClickListener.onItemClick(v, pos);
						}
					});
            }
        }
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
        ItemViewHolder holder = new ItemViewHolder(layoutInflater.inflate(R.layout.list, viewGroup, false));
        return holder;
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
	{
        this.onItemClickListener = mOnItemClickListener;
    }


	interface OnItemClickListener
	{
        public void onItemClick(View view, int position);
    }



	
    class ItemViewHolder extends RecyclerView.ViewHolder
	{

        private TextView title;

        public ItemViewHolder(View itemView)
		{
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

}
