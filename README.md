# xindalu_scan_flutter

新大陆手持设备红外扫描识别插件by_flutter

## 安装
```dart
dependencies:
  xindalu_scan_flutter: ^1.0.0
```

# 初始化
```dart

     static const EventChannel _eventChannel = EventChannel(Constant.CHANNEL_NAME);
     StreamSubscription _streamSubscription;

     Future<void> init({Map<String, String> config}) async {
       await XindaluScanFlutter.init();
     }

     @override
      void initState() {

        // 初始化
        init();

        // 监听扫描数据
        _streamSubscription = _eventChannel.receiveBroadcastStream().listen((value) {
          try{
            XindaluDataResultModel result = XindaluDataResultModel.fromJson(json.decode(value));
            print("条码值:${result.code1}");  // 123456789
            print("状态:${result.scanState}"); // ok 成功  fail 失败
            print("条码类型:${result.scanBarcodeType}"); // -1 表示未知类型
          }catch(e,s){
              print(e);
              print(s);
          }
        });
      }

      @override
      void dispose() {
        _streamSubscription.cancel();
      }
```

# 使用例子
```dart
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
  StreamSubscription _streamSubscription;

  @override
  void initState() {
    super.initState();
    init();
   _streamSubscription = _eventChannel.receiveBroadcastStream().listen((value) {
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

  //初始化
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

  @override
  void dispose() {
    super.dispose();
    _streamSubscription.cancel();
  }
}


```



# 附件
[新大陆官方文档](http://www.nlscan.com/UpLoad/Video/%E6%96%B0%E5%A4%A7%E9%99%86Android%E6%97%A0%E7%BA%BF%E6%95%B0%E6%8D%AE%E7%BB%88%E7%AB%AF%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E6%8C%87%E5%8D%97-V1.4.pdf)

待更新
