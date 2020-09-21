/// 一些常量
class Constant {

  /// 和原生通讯的通道别名
  ///
  /// static const EventChannel _eventChannel = EventChannel(Constant.CHANNEL_NAME);
  static const String CHANNEL_NAME = "xindalu_scan";

  /// 广播输出配置 -  默认action
  static const String ACTION = "nlscan.action.SCANNER_RESULT";

  /// 广播输出配置 -  默认 条码1Extra
  static const String EXTRA_1 = "SCAN_BARCODE1";

  /// 广播输出配置 -  默认 条码2Extra
  static const String EXTRA_2 = "SCAN_BARCODE2";

  /// 广播输出配置 -  默认 码制类型Extra
  static const String EXTRA_BARCODE_TYPE = "SCAN_BARCODE_TYPE";


  // 默认配置
  static const Map<String,String> defaultConfig = {
    "action" : ACTION,
    "extra1" : EXTRA_1,
    "extra2" : EXTRA_2,
    "codeType": EXTRA_BARCODE_TYPE,
  };
}
