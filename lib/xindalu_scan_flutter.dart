import 'dart:async';

import 'package:flutter/services.dart';

class XindaluScanFlutter {
  static const MethodChannel _channel =
      const MethodChannel('xindalu_scan_flutter');

  static Future<void> get init async {
    await _channel.invokeMethod('init');
  }
}
