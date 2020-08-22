package com.example.xindalu_scan_flutter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.annotation.NonNull;
import androidx.core.content.edit

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

public class XindaluScanFlutterPlugin : FlutterPlugin, MethodCallHandler {
    private val TAG = "XindaluScanFlutterPlugin"

    private lateinit var channel: MethodChannel

    //上下文(lateinit表示等一下我会初始化这个属性,可以省略初始化为null)
    private lateinit var context: Context

    //安卓持久化功能
    private lateinit var sharedPreferences: SharedPreferences

    //和flutter通讯渠道,需要靠它返回数据给flutter app
    private lateinit var eventChannel : EventChannel


    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "xindalu_scan_flutter")
        channel.setMethodCallHandler(this);
        context = flutterPluginBinding.applicationContext //在这里获取上下文
        sharedPreferences = context.getSharedPreferences("scan_config", Context.MODE_PRIVATE)//本地持久化
        //获取插件和flutter app通讯的key
        val flutterAppChannelName : String = sharedPreferences.getString("flutterAppChannelName","xindalu_scan")
        Log.d(TAG,"$flutterAppChannelName")
        eventChannel = EventChannel(flutterPluginBinding.binaryMessenger,flutterAppChannelName)
        eventChannel.setStreamHandler(ScanDataStreamHandler(context,sharedPreferences))
    }

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "xindalu_scan_flutter")
            channel.setMethodCallHandler(XindaluScanFlutterPlugin())
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "init") {
            //获取参数配置
            init(call)

        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    //初始化
    fun init(call: MethodCall) {

        //获取所需参数
        var action: String? = call.argument("action") //广播输出action
        var code1: String? = call.argument("extra1")//条码1
        var code2: String? = call.argument("extra2")//条码2
        var barcodeType: String? = call.argument("codeType") //码类型
        Log.d(TAG,"action:$action , code1:$code1 , code2:$code2 , barcodeType:$barcodeType")

        //本地持久化
        sharedPreferences.edit {
          putString("action",action)
          putString("code1",code1)
          putString("code2",code2)
          putString("barcode_type",barcodeType)
        }

        Log.d(TAG,"新大陆扫描配置初始化成功")
    }
}
