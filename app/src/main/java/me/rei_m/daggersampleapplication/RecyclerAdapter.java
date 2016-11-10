package me.rei_m.daggersampleapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<String> itemList;
    private OnRecyclerViewInteraction listener;

    public RecyclerAdapter(@NonNull Context context,
                           @NonNull List<String> itemList,
                           @Nullable OnRecyclerViewInteraction listener) {
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 表示するレイアウトを設定
        return new ViewHolder(inflater.inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.textView.setText(itemList.get(i));

        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.onItemClicked(v, i));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.list_item_text);
        }
    }

    public interface OnRecyclerViewInteraction {
        void onItemClicked(View v, int position);
    }
}
