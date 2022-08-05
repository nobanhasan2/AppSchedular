package com.noban.appschedular.core.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.noban.appschedular.R
import com.noban.appschedular.data.model.ScheduleEntity
import com.noban.appschedular.features.applist.model.AppModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class  Navigator  @Inject constructor() {

    lateinit var navController : NavController

    fun init(navHostFragment:NavHostFragment){
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.homeFragment
        navController = navHostFragment.navController
        navController.graph = graph

    }
    fun popBack(){
        if (!navController.popBackStack()) {
            navController.navigate(R.id.homeFragment)

        }else{ navController.popBackStack() }
    }
    fun showAppList(){
        navController.navigate(R.id.applistFragment)
    }
    fun showCreateSchedule(appModel : AppModel){
        val bundle = bundleOf("name" to appModel.name,
                             "packageName" to appModel.packageName)
        navController.navigate(R.id.createSchedule,bundle) }
    fun showUpdateSchedule(scheduleEntity: ScheduleEntity){

        val bundle = bundleOf("name" to scheduleEntity.scheduleName,
            "packageName" to scheduleEntity.packageName,
            "timeStamp"  to scheduleEntity.scheduleTime,
            "jobTag"  to scheduleEntity.jobTag,
            "id" to scheduleEntity.id

          )

        navController.navigate(R.id.createSchedule,bundle) }




}