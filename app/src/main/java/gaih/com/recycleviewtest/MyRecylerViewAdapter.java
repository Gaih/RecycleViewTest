package gaih.com.recycleviewtest;

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
    public MyRecylerViewAdapter(List<String> Data){
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = mData.get(position)+position;
        ViewGroup.LayoutParams layoutParams = holder.cardView.getLayoutParams();
        if (mHeight.size() <= position) {
            mHeight.add((int) (30 + Math.random() * 200));
        }
        layoutParams.height = mHeight.get(position);
        holder.cardView.setLayoutParams(layoutParams);
        holder.textView.setText(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textView;
        public CardView cardView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.text);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (itemClickListener !=null){
                itemClickListener.onItemClick(v,getPosition());
            }
        }
    }
}
