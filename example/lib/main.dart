import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:xindalu_scan_flutter/xindalu_scan_flutter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  static const EventChannel _eventChannel = EventChannel('111');
  @override
  void initState() {
    super.initState();
    Map<String, dynamic> map = Map();
    map["extra1"] = "code1";
    map["extra2"] = "code2";
    map["flutterAppChannelName"] = "111";
    init(map);
    _eventChannel.receiveBroadcastStream().listen((value) {
      print("获取到扫描头数据>>>>>>>>>>$value");
    });
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> init(Map<String, dynamic> config) async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      await XindaluScanFlutter.init(config);
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion\n'),
        ),
      ),
    );
  }
}
