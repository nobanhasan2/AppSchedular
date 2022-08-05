package com.noban.appschedular.core.recyclerview.base.callback

import android.view.View
import com.noban.appschedular.core.recyclerview.base.BaseRecyclerAdapter

/**
 * This is a interface that contains callback methods to work with RecyclerView item clicks
 * @author Noban Hasan
 * */
interface ItemClickListener<T> {
    /**
     * This method is called when an item gets clicked.
     *
     * @param view clicked view
     * @param item model object
     */
    fun onItemClick(view: View, item: T){}

    /**
     * This method is called when an item gets clicked.
     *
     * @param view clicked view
     * @param item model object
     * @param position model object position in the list
     */
    fun onItemClick(view: View, item: T, position: Int){}

    /**
     * This method sets this click listener to the adapter
     *
     * @param recyclerAdapter RecyclerView adapter
     * */
    fun setAdapter(recyclerAdapter: BaseRecyclerAdapter<T>) {
        recyclerAdapter.setItemClickListener(this)
    }
}
