package com.michael.yeslaundry.services;

import android.view.View;

public interface ItemLongClickListener<T> {
    void onItemLongClick(View view, T data, int position);


}
