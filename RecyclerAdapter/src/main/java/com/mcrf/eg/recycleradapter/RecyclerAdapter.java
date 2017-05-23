package com.mcrf.eg.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mcrf.eg.recycleradapter.R;
import java.util.List;

/*
 * By Github MCRuiFeng
 * Email:dev.mcrf@qq.com
 * 2017.05.21
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private List<String> listDatas;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;
	private String id[];

    public RecyclerAdapter(Context context, List<String> listDatas) {
        this.listDatas = listDatas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {

		//实验性功能
		setIcon(new String[] {"1", "2", "3", "4", "5"});
		if (id.length != 0) {
			itemViewHolder.title.setText(listDatas.get(i));
		}
	


		if (onItemClickListener != null) {
            if (!itemViewHolder.itemView.hasOnClickListeners()) {
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							int pos = itemViewHolder.getPosition();
							onItemClickListener.onItemClick(v, pos);
						}
					});
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
						@Override
						public boolean onLongClick(View v) {
							int pos = itemViewHolder.getPosition();
							onItemClickListener.onItemLongClick(v, pos);
							return true;
						}
					});
            }
        }


    }



	
	//实验性功能
	public void setIcon(String i[]) {
		id = new String [i.length];
		for (int a = 0; a < i.length; a++) {
			id[a] = i[a];
		}
	}


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemViewHolder holder = new ItemViewHolder(layoutInflater.inflate(R.layout.item, viewGroup, false));
        return holder;
    }



	public void add(int position, String value) {
        if (position > listDatas.size()) {
            position = listDatas.size();
        }
        if (position < 0) {
            position = 0;
        }
        listDatas.add(position, value);
        notifyItemInserted(position);
    }



	public String remove(int position) {
        if (position > listDatas.size() - 1) {
            return null;
        }
        String value = listDatas.remove(position);
        notifyItemRemoved(position);
        return value;
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
    }



	interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }



    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
		private ImageView icon;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_tv);
			icon = (ImageView) itemView.findViewById(R.id.item_iv);
        }
    }

}
