import 'dart:async';

import 'package:flutter/services.dart';

class XindaluScanFlutter {
  static const MethodChannel _channel =
      const MethodChannel('xindalu_scan_flutter');

  static Future<void> init(Map<String,dynamic> map) async {
    await _channel.invokeMethod('init',map);
  }
}
