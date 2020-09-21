package com.example.xindalu_scan_flutter

import android.content.Context
import android.content.IntentFilter
import android.content.SharedPreferences
import io.flutter.plugin.common.EventChannel

// 流处理
class ScanDataStreamHandler(var context: Context,var sharedPreferences: SharedPreferences) : EventChannel.StreamHandler {



    private lateinit var xindaluScanHandleReceiver: XindaluScanHandleReceiver //广播

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        xindaluScanHandleReceiver = XindaluScanHandleReceiver(events,sharedPreferences)
        val intentFilter = IntentFilter()
        intentFilter.addAction(sharedPreferences.getString("action","nlscan.action.SCANNER_RESULT"))
        context.registerReceiver(xindaluScanHandleReceiver,intentFilter)
    }

    override fun onCancel(arguments: Any?) {
        context.unregisterReceiver(xindaluScanHandleReceiver)
    }

}