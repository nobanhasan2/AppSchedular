package com.noban.appschedular.features.createSchedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.noban.appschedular.R
import com.noban.appschedular.core.platform.BaseVMFragment
import com.noban.appschedular.databinding.FragmentCreateScheduleBinding
import com.noban.appschedular.util.AppConstants
import java.util.*

class ScheduleFragment : BaseVMFragment<ScheduleViewModel, FragmentCreateScheduleBinding>()  {
    private var ids : Int = 0
    lateinit var name : String
    lateinit var packageName : String
    private lateinit var jobTag : String
    private var isDatePicked : Boolean = false
    private var isTimePicked : Boolean = false
    private var isUpdate : Boolean = false
    private val calendar: Calendar = Calendar.getInstance()

    private val scheduleViewModel by viewModels<ScheduleViewModel>()
    private lateinit var date : Date

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.fragment = this
        arguments?.get(AppConstants.NAME)?.apply {
            name = this.toString()
            mBinding.tvAppName.text = name
        }
        arguments?.get(AppConstants.PACKAGE_NAME)?.apply {
            packageName = this.toString()
            mBinding.tvPackageName.text = packageName
        }
        arguments?.get(AppConstants.ID)?.apply {
            ids = this.toString().toInt()
        }
        arguments?.get(AppConstants.JOB_TAG)?.apply {
            jobTag = this.toString()
        }
        arguments?.get(AppConstants.TIME_STAMP)?.apply {
            date = Date(this.toString().toLong())
            calendar.time = date
            isUpdate = true
            mBinding.btSchedule.text = AppConstants.RE_SCHEDULE
            mBinding.tvDate.text = "${AppConstants.DATE} ${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH)+1}/${calendar.get(Calendar.YEAR)}"
            mBinding.tvTime.text = "${AppConstants.TIME} ${calendar.get(Calendar.HOUR_OF_DAY)} : ${calendar.get(Calendar.MINUTE)}"
        }


    }
    fun pickDate(){
        val datePickerDialog =
            parentFragment?.context?.let {
                DatePickerDialog(it, { view, year, monthOfYear, dayOfMonth ->
                    isDatePicked = true
                    calendar.set(Calendar.YEAR,year)
                    calendar.set(Calendar.MONTH,monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                    mBinding.tvDate.text = "${AppConstants.DATE} $dayOfMonth/${monthOfYear+1}/$year"

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            }
        datePickerDialog?.datePicker?.minDate = Calendar.getInstance().timeInMillis
        datePickerDialog?.show()
    }
    fun pickTime(){
        val timePickerDialog = TimePickerDialog(activity,
            { _, hourOfDay, minute ->
                run {
                    val tempCalendar = calendar
                    tempCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
                    tempCalendar.set(Calendar.MINUTE,minute)
                    if(tempCalendar.timeInMillis > Calendar.getInstance().timeInMillis){
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
                        calendar.set(Calendar.MINUTE,minute)
                        isTimePicked = true
                        mBinding.tvTime.text = "${AppConstants.TIME} $hourOfDay:$minute"
                    }else{
                        notify(R.string.past_time)
                    }


                }
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false)


        timePickerDialog.show()
    }
    fun createSchedule(){
        if(!isUpdate){
            if (isDatePicked && isTimePicked){
                scheduleViewModel.startSchedule(calendar,name,packageName)
                notify(R.string.schedule_created)
                navigation.popBack()
            }else{
                notify(R.string.pick)
            }
        }else{
            scheduleViewModel.updateSchedule(calendar,name,packageName,ids,jobTag)
            notify(R.string.schedule_updated)
            navigation.popBack()
        }


    }

    override fun getViewBinding() =FragmentCreateScheduleBinding.inflate(layoutInflater)
}