package com.dotinfiny.banglesystem.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dotinfiny.banglesystem.R
import com.dotinfiny.banglesystem.Utils.Utils
import com.dotinfiny.banglesystem.app.BaseActivity
import com.dotinfiny.banglesystem.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
        binding.main = viewModel
        binding.lifecycleOwner = this
        init()
    }

    private fun init() {

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_main_fragment) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(toolbar, navController)

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
//            val WifiStaExt = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
            if (Utils.isConnected(this@MainActivity)) {
            } else {
                showSnackBar(binding.root)
            }
//            when (WifiStaExt) {
//                WifiManager.WIFI_STATE_ENABLED -> Toast.makeText(
//                    p0,
//                    "Wifi Enabled",
//                    Toast.LENGTH_SHORT
//                ).show()
//                WifiManager.WIFI_STATE_DISABLED -> {
//                    showSnackBar(binding.root)
//                }
//                WifiManager.WIFI_STATE_UNKNOWN -> {
//                    showSnackBar(binding.root)
//                }
//            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(broadcastReceiver, intentFilter)
    }
}