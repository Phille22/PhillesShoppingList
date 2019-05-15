package com.example.phillesshoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoppingListItem implements Parcelable {
    final String item;

    ShoppingListItem(String item){
        this.item = item;
    }

    private ShoppingListItem(Parcel in) {
        item = in.readString();
    }

    public static final Creator<ShoppingListItem> CREATOR = new Creator<ShoppingListItem>() {
        @Override
        public ShoppingListItem createFromParcel(Parcel in) {
            return new ShoppingListItem(in);
        }

        @Override
        public ShoppingListItem[] newArray(int size) {
            return new ShoppingListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item);
    }
}
