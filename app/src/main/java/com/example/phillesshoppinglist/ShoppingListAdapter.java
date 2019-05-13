package com.example.phillesshoppinglist;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder> {
    private ArrayList<ShoppingListItem> arrayList;
    private LayoutInflater mInflater;
    private MainActivity m;

    public ShoppingListAdapter(Context context, ArrayList shoppingItemList){
        mInflater = LayoutInflater.from(context);
        this.arrayList = shoppingItemList;
    }

    @Override
    public ShoppingListAdapter.ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.shoppinlist_item, parent, false);
        return new ShoppingItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ShoppingItemViewHolder holder, int position) {
        String mCurrent = arrayList.get(position).item;
        holder.shoppingItem.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void deleteItem(int position) {
       arrayList.remove(position);
       notifyItemRangeChanged(position, arrayList.size() - position);
       Log.d("DELETEITEM", "" + position);
    }

    class ShoppingItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView shoppingItem;
        public boolean isChecked;

        final ShoppingListAdapter mAdapter;

        public ShoppingItemViewHolder(View itemView, ShoppingListAdapter adapter) {
            super(itemView);
            shoppingItem = itemView.findViewById(R.id.textViewItem);

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(!isChecked){
                shoppingItem.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                isChecked = true;
            }else{
                shoppingItem.setPaintFlags(0);
                isChecked = false;
            }
        }
    }
}
