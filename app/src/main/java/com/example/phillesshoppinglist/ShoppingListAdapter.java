package com.example.phillesshoppinglist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder> {
    private final ArrayList<ShoppingListItem> arrayList;
    private final LayoutInflater mInflater;

    public ShoppingListAdapter(Context context, ArrayList<ShoppingListItem> shoppingItemList){
        mInflater = LayoutInflater.from(context);
        this.arrayList = shoppingItemList;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.ShoppingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.shoppinlist_item, parent, false);
        return new ShoppingItemViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ShoppingItemViewHolder holder, int position) {
        String mCurrent = arrayList.get(position).item;
        holder.shoppingItem.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void removeItem(int position){
        arrayList.remove(position);
        notifyDataSetChanged();
    }

    class ShoppingItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView shoppingItem;
        private boolean isChecked;

        private ShoppingItemViewHolder(View itemView) {
            super(itemView);
            shoppingItem = itemView.findViewById(R.id.textViewItem);

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
