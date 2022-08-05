package com.noban.appschedular.core.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.noban.appschedular.data.AppDatabase
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import com.noban.appschedular.R
import kotlin.random.Random


class AppScheduleWorker(val context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters) {
   companion object{
       fun createSchedule(duration: Long, data: Data, tag: String?) {
           val absDuration = abs(duration) / 1000
           Log.e("Duration:::",absDuration.toString())
           val notificationWork = OneTimeWorkRequest.Builder(AppScheduleWorker::class.java)
               .setInitialDelay(absDuration, TimeUnit.SECONDS).addTag(tag!!)
               .setInputData(data).build()
           val instaWorkManager = WorkManager.getInstance()
           instaWorkManager.enqueue(notificationWork)

       }
    }

    override fun doWork(): Result {
        Log.e("Do:::",inputData.getString("packageName").toString())
        val intent = context.packageManager.getLaunchIntentForPackage(inputData.getString("packageName").toString())
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        AppDatabase.getInstance(context).scheduleDao().updateComplete(inputData.getString("uuid").toString())
        createNotificationChannel()
        val builder = NotificationCompat.Builder(context, "my_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(inputData.getString("appName"))
            .setContentText("App Launch Executed!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)) {
            notify(Random.nextInt(0,999999), builder.build())
        }
        return Result.success();
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "my_channel"
            val descriptionText = "app notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("my_channel", name, importance)
            mChannel.description = descriptionText
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}