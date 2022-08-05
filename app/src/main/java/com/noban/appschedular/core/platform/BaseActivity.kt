package com.noban.appschedular.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.noban.appschedular.R
import com.noban.appschedular.core.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var navigation: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
    }
    override fun onBackPressed() {
        /**
         Navigation back stack handle from fragment stack
         */
        navigation.popBack()


    }
}
