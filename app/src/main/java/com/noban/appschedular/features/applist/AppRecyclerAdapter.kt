package com.noban.appschedular.features.applist


import android.view.View
import android.view.ViewGroup
import com.noban.appschedular.R
import com.noban.appschedular.core.recyclerview.base.BaseRecyclerAdapter
import com.noban.appschedular.core.recyclerview.base.BaseItemCallback
import com.noban.appschedular.core.recyclerview.base.BaseViewHolder
import com.noban.appschedular.databinding.ItemApplistBinding
import com.noban.appschedular.features.applist.model.AppModel

class AppRecyclerAdapter  : BaseRecyclerAdapter<AppModel>(AppDiffCallback)  {

    inner class AppViewHolder(private val binding: ItemApplistBinding) :
        BaseViewHolder<AppModel>(binding) {
        override fun bind(item: AppModel) {
            binding.app = item
            binding.root.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            super.onClick(view)
            mItemClickListener?.onItemClick(view, getItem(layoutPosition))
        }

    }
    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AppModel> {
        return AppViewHolder(inflate(parent, R.layout.item_applist))
    }
}


object AppDiffCallback : BaseItemCallback<AppModel>() {
    override fun areContentSame(oldItem: AppModel, newItem: AppModel): Boolean {
        return oldItem.packageName == newItem.packageName
    }

}