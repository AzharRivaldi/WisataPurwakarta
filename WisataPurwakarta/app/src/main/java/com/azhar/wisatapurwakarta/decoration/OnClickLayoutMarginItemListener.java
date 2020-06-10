package com.azhar.wisatapurwakarta.decoration;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Azhar Rivaldi on 06/04/2020.
 */

public interface OnClickLayoutMarginItemListener {
    void onClick(Context context, View v, int position, int spanIndex, RecyclerView.State state);
}
