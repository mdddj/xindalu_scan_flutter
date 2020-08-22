# xindalu_scan_flutter

新大陆手持设备红外扫描识别插件by_flutter

## 安装
```dart
dependencies:
  xindalu_scan_flutter: ^0.0.3
```

# 初始化

定义参数
```dart
  static const String KEY = 'xindalu_scan';//自定义key,固定值,修改会启动报错
  static const EventChannel _eventChannel = EventChannel(key);
```

然后在`initState` 初始化
```dart
await XindaluScanFlutter.init(config); // 如果不配置,将使用官方默认值
```
使用默认配置
```dart
await XindaluScanFlutter.init(Map()); 
```

`config`只能自定义这3个参数,其他修改无效,不过这也够用了
```dart
    Map<String, dynamic> config = Map();
    config["extra1"] = "code1";//对应下面的条码1Extra
    config["extra2"] = "code2"; //对应下面的条码2Extra
    config['barcodeType'] = "SCAN_BARCODE_TYPE";//对应下面的码制类型
```
![1598057630218.jpg](https://static.saintic.com/picbed/huang/2020/08/22/1598057630218.jpg)

# 监听扫描数据
在需要获取到扫描数据的页面,声明周期`initState`监听
```dart
    _eventChannel.receiveBroadcastStream().listen((value) {
      print("获取到扫描头数据>>>>>>>>>>$value");
    });
```

## 常见问题
1. 引入插件后报错
```dart
I/flutter (31823): MissingPluginException(No implementation found for method listen on channel xindalu_scan/send)
```
初始化一下就行了


# 附件
[新大陆官方文档](http://www.nlscan.com/UpLoad/Video/%E6%96%B0%E5%A4%A7%E9%99%86Android%E6%97%A0%E7%BA%BF%E6%95%B0%E6%8D%AE%E7%BB%88%E7%AB%AF%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E6%8C%87%E5%8D%97-V1.4.pdf)

待更新
