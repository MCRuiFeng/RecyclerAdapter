package com.mcrf.eg.recycleradapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mcrf.eg.recycleradapter.R;
import java.util.List;

/*
 * By Github MCRuiFeng
 * Email:dev.mcrf@qq.com
 * 2017.05.21
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

	
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        itemViewHolder.mTextView.setText(mDatas.get(i));
        if (mOnItemClickListener != null) {
            if (!itemViewHolder.itemView.hasOnClickListeners()) {
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							int pos = itemViewHolder.getPosition();
							mOnItemClickListener.onItemClick(v, pos);
						}
					});
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
						@Override
						public boolean onLongClick(View v) {
							int pos = itemViewHolder.getPosition();
							mOnItemClickListener.onItemLongClick(v, pos);
							return true;
						}
					});
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(R.layout.item, viewGroup, false));
        return holder;
    }





	public void add(int position, String value) {
        if (position > mDatas.size()) {
            position = mDatas.size();
        }
        if (position < 0) {
            position = 0;
        }
        mDatas.add(position, value);
        notifyItemInserted(position);
    }






	public String remove(int position) {
        if (position > mDatas.size() - 1) {
            return null;
        }
        String value = mDatas.remove(position);
        notifyItemRemoved(position);
        return value;
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }



	interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

}
