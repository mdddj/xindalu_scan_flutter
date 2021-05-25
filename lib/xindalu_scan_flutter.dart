import 'dart:async';

import 'package:flutter/services.dart';
import 'package:xindalu_scan_flutter/constant.dart';

class XindaluScanFlutter {
  static const MethodChannel _channel =
      const MethodChannel('xindalu_scan_flutter');

  ///   初始化配置
  ///
  ///   @params config  配置广播输出配置
  ///
  ///   如果不传入参数,将使用默认配置
  ///
  ///   遵循格式,可以在扫码设置里面配置这些参数
  ///
  ///   Map<String,String> config = {
  ///     "action" : '',  广播输出action
  ///     "extra1" : '',  条码1Extra
  ///     "extra2" : '',  条码2Extra
  ///     "codeType": '',  码制类型
  ///   }
  ///
  ///   await XindaluScanFlutter.init(config);
  ///
  ///   详细文档github:https://github.com/mdddj/xindalu_scan_flutter
  ///
  static Future<void> init({Map<String, dynamic>? config}) async {
    await _channel.invokeMethod('init', config ?? Constant.defaultConfig);
  }
}
