import 'dart:convert';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:xindalu_scan_flutter/constant.dart';
import 'package:xindalu_scan_flutter/xindalu_result_model.dart';
import 'package:xindalu_scan_flutter/xindalu_scan_flutter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {


  static const EventChannel _eventChannel = EventChannel(Constant.CHANNEL_NAME);

  @override
  void initState() {
    super.initState();
    init();
    _eventChannel.receiveBroadcastStream().listen((value) {
      try{
        XindaluDataResultModel result = XindaluDataResultModel.fromJson(json.decode(value));
        print("条码值:${result.code1}");
        print("状态:${result.scanState}"); // ok 成功  fail 失败
        print("条码类型:${result.scanBarcodeType}"); // -1 表示未知类型
      }catch(e,s){
          print(e);
          print(s);
      }
    });
  }

  Future<void> init({Map<String, String> config}) async {

    await XindaluScanFlutter.init();

  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: MaterialButton(
            onPressed: () {  },
            child: Text("新大陆扫码示例"),
          ),
        ),
      ),
    );
  }
}
