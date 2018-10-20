package com.giuseppesorce.watchersexplorer.android.ui.homesearch;

import android.content.Context;
import android.support.v7.widget.SearchView;
import com.giuseppesorce.watchersexplorer.R;


/**
 * @author Giuseppe Sorce
 */

public class MySearchView  extends SearchView {
    public MySearchView(Context context) {
        super(context, null, R.style.SearchViewMy);
    }
}
