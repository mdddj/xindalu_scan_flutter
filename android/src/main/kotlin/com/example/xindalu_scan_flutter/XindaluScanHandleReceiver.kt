package com.example.xindalu_scan_flutter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import io.flutter.plugin.common.EventChannel

//当手机红外扫描到数据时执行onReceive方法
class XindaluScanHandleReceiver(private val event: EventChannel.EventSink?,private val sharedPreferences: SharedPreferences) : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        //获取key配置
        val CODE1_KEY = sharedPreferences.getString("code1", "SCAN_BARCODE1")
        val CODE2_KEY = sharedPreferences.getString("code2", "SCAN_BARCODE2")
        val BARCODE_KEY = sharedPreferences.getString("barcode_type", "SCAN_BARCODE_TYPE")
        var SCAN_STATE_KEY = "SCAN_STATE" //扫描结果状态 fail或ok

        //取值

        if (intent != null && event != null) {
            val code1: String = intent.getStringExtra(CODE1_KEY)
            val code2: String = intent.getStringExtra(CODE2_KEY)
            val barcodeType: Int = intent.getIntExtra(BARCODE_KEY, -1)
            val scanState: String = intent.getStringExtra(SCAN_STATE_KEY)
            if (scanState == "ok") {
                //成功
               val map =  mapOf("code1" to code1,"code2" to code2,"barcodeType" to barcodeType,"scanState" to scanState)
            } else {
                //失败
                event.error("10000", "扫描超时", "请重新扫描");
            }
        }
        //失败
        event?.error("10000", "扫描超时", "请重新扫描");
    }


}