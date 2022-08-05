package com.noban.appschedular.features.home.adapter

import android.view.View
import android.view.ViewGroup
import com.noban.appschedular.R
import com.noban.appschedular.core.recyclerview.base.BaseRecyclerAdapter
import com.noban.appschedular.core.recyclerview.base.BaseItemCallback
import com.noban.appschedular.core.recyclerview.base.BaseViewHolder
import com.noban.appschedular.data.model.ScheduleEntity
import com.noban.appschedular.databinding.ItemSchedulesBinding

interface ScheduleCallback {
    fun onClickEdit(schedule : ScheduleEntity)
    fun onClickDelete(schedule : ScheduleEntity)
}

class ScheduleRecyclerAdapter(val scheduleCallback: ScheduleCallback) : BaseRecyclerAdapter<ScheduleEntity>(ScheduleDiffCallback)   {

    inner class ScheduleViewHolder(private val binding: ItemSchedulesBinding) :
        BaseViewHolder<ScheduleEntity>(binding) {
        override fun bind(item: ScheduleEntity) {
            binding.schedule = item
            binding.root.setOnClickListener(this)
            binding.ivCancel.setOnClickListener {
                scheduleCallback.onClickDelete(item)
            }
            binding.tvEdit.setOnClickListener {
                scheduleCallback.onClickEdit(item)
            }

        }


        override fun onClick(view: View) {
            super.onClick(view)
            mItemClickListener?.onItemClick(view, getItem(layoutPosition))
        }

    }
    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ScheduleEntity> {
        return ScheduleViewHolder(inflate(parent, R.layout.item_schedules))
    }
}

object ScheduleDiffCallback : BaseItemCallback<ScheduleEntity>() {

    override fun areContentSame(oldItem: ScheduleEntity, newItem: ScheduleEntity): Boolean {
        return oldItem.id == newItem.id
    }

}