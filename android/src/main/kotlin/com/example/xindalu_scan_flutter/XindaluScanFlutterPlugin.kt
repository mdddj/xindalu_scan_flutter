package com.example.xindalu_scan_flutter

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class XindaluScanFlutterPlugin : FlutterPlugin, MethodCallHandler {
    private val TAG = "XindaluScanFlutterPlugin"

    private lateinit var channel: MethodChannel

    //上下文(lateinit表示等一下我会初始化这个属性,可以省略初始化为null)
    private lateinit var context: Context

    //安卓持久化功能
    private lateinit var sharedPreferences: SharedPreferences

    //和flutter通讯渠道,需要靠它返回数据给flutter app
    private lateinit var eventChannel: EventChannel

    private lateinit var scanDataStreamHandler: ScanDataStreamHandler

    ///监听通道的名称,唯一key
    private val CHANNEL_NAME: String = "xindalu_scan"


    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "xindalu_scan_flutter")
        channel.setMethodCallHandler(this);
        context = flutterPluginBinding.applicationContext //在这里获取上下文
        sharedPreferences = context.getSharedPreferences("scan_config", Context.MODE_PRIVATE)//本地持久化
        eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, CHANNEL_NAME)
        scanDataStreamHandler = ScanDataStreamHandler(context, sharedPreferences)
        eventChannel.setStreamHandler(scanDataStreamHandler)
    }

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "xindalu_scan_flutter")
            channel.setMethodCallHandler(XindaluScanFlutterPlugin())
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "init") {
            //获取参数配置
            init(call)

        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    //初始化
    fun init(call: MethodCall) {

        //获取所需参数
        val action: String? = call.argument("action") //广播输出action
        val code1: String? = call.argument("extra1")//条码1
        val code2: String? = call.argument("extra2")//条码2
        val barcodeType: String? = call.argument("codeType") //码类型
        //本地持久化
        sharedPreferences.edit {
            putString("action", action)
            putString("code1", code1)
            putString("code2", code2)
            putString("barcode_type", barcodeType)
        }
    }
}
