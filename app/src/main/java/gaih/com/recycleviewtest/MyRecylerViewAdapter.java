package gaih.com.recycleviewtest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */

public class MyRecylerViewAdapter extends RecyclerView.Adapter<MyRecylerViewAdapter.ViewHolder> {

    private List<String> mData;
    private List<Integer> mHeight;
    private LayoutInflater mInflater;
    public MyRecylerViewAdapter(Context context, List<String> Data){
        mInflater = LayoutInflater.from(context);
        mData = Data;
        mHeight = new ArrayList<>();
    }
    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String data = mData.get(position)+position;
        ViewGroup.LayoutParams layoutParams = holder.cardView.getLayoutParams();
        if (mHeight.size() <= position) {
            mHeight.add((int) (30 + Math.random() * 200));
        }
        layoutParams.height = mHeight.get(position);
        holder.cardView.setLayoutParams(layoutParams);
        holder.textView.setText(data);
        if (itemClickListener!=null){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public CardView cardView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.text);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }
}
