package com.noban.appschedular.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.noban.appschedular.core.extension.observe
import com.noban.appschedular.core.platform.BaseVMFragment
import com.noban.appschedular.data.model.ScheduleEntity
import com.noban.appschedular.databinding.FragmentHomeBinding
import com.noban.appschedular.features.home.adapter.ScheduleRecyclerAdapter
import com.noban.appschedular.features.home.adapter.ScheduleCallback


class HomeFragment : BaseVMFragment<HomeViewModel, FragmentHomeBinding>(),ScheduleCallback{

    private var scheduleAdapter = ScheduleRecyclerAdapter(this)
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun getViewBinding() =  FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        with(homeViewModel){
            observe(scheduleList,::onGetListSuccess)
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getAllSchedules()
    }
    private fun onGetListSuccess(scheduleList : List<ScheduleEntity>?) {
        if(scheduleList?.isEmpty() == true){
          mBinding.tvEmpty.visibility = View.VISIBLE
        }else{ mBinding.tvEmpty.visibility = View.GONE }
        scheduleAdapter.submitList(scheduleList)
    }

    private fun setUpViews(){
        val layoutManager = LinearLayoutManager(context)
        mBinding.rvSchedule.layoutManager = layoutManager
        mBinding.rvSchedule.adapter = scheduleAdapter
    }

    override fun onClickEdit(schedule: ScheduleEntity) {
       navigation.showUpdateSchedule(schedule)
    }

    override fun onClickDelete(schedule: ScheduleEntity) {
        homeViewModel.deleteSchedule(schedule)
        homeViewModel.getAllSchedules()
    }

}