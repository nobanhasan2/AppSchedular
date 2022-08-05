package com.noban.appschedular.features.applist

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.noban.appschedular.core.platform.BaseViewModel
import com.noban.appschedular.data.dao.ScheduleDao
import com.noban.appschedular.features.applist.model.AppModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class AppListviewModel  @Inject constructor(val dao: ScheduleDao,context: Context) : BaseViewModel() {

    private fun isSystemPackage(packageInfo: PackageInfo): Boolean {
        return packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM !== 0
    }
     fun getInstalledApps(context: Context): List<AppModel> {
        val packageManager: PackageManager = context.packageManager
        val apps: MutableList<AppModel> = ArrayList()
        val packages: List<PackageInfo> = packageManager.getInstalledPackages(0)
        for (i in packages.indices) {
            val packageInfo = packages[i]
            if (!isSystemPackage(packageInfo)) {
                val appName =
                    packageInfo.applicationInfo.loadLabel(packageManager)
                        .toString()
                val appIcon =
                    packageInfo.applicationInfo.loadIcon(packageManager)
                val packageName = packageInfo.applicationInfo.packageName
                apps.add(AppModel(appName,packageName,appIcon))
            }
        }
        return apps
    }

}