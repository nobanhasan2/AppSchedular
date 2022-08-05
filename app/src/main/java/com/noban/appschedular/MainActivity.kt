package com.noban.appschedular
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.noban.appschedular.core.platform.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navigationHost =  supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navigation.init(navigationHost)
    }
}