package com.noban.appschedular.features.applist


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.noban.appschedular.core.extension.appContext
import com.noban.appschedular.core.platform.BaseVMFragment
import com.noban.appschedular.core.recyclerview.base.callback.ItemClickListener
import com.noban.appschedular.databinding.FragmentApplistBinding
import com.noban.appschedular.features.applist.model.AppModel

class AppListFragment  : BaseVMFragment<AppListviewModel, FragmentApplistBinding>(){
    private var appListAdapter = AppRecyclerAdapter()

    private var appList: List<AppModel> = emptyList()

    var mHour : Int = 0
    var mMinute : Int = 0

    private val appViewModel by viewModels<AppListviewModel>()
    override fun getViewBinding()=  FragmentApplistBinding.inflate(layoutInflater)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            appListAdapter.setItemClickListener(object : ItemClickListener<AppModel> {
                override fun onItemClick(view: View, item: AppModel) {
                    navigation.showCreateSchedule(item)
//                    val c: Calendar = Calendar.getInstance()
//                    mHour = c.get(Calendar.HOUR_OF_DAY)
//                    mMinute = c.get(Calendar.MINUTE)
//
//                    val timePickerDialog = TimePickerDialog(activity,
//                        { _, hourOfDay, minute ->
//                            run {
//                                appViewModel.startSchedule(hourOfDay, minute,item)
//                            }
//                        },
//                        mHour,
//                        mMinute,
//                        false
//                    )
//                    timePickerDialog.show()
                }
            })
    }
    override fun onResume() {
        super.onResume()
        setUpViews()

    }
    private fun setUpViews(){
        appList = appViewModel.getInstalledApps(appContext)
        appListAdapter.submitList(appList)
        val layoutManager = LinearLayoutManager(context)
        mBinding.rvApps.layoutManager = layoutManager
        mBinding.rvApps.adapter = appListAdapter
    }
}