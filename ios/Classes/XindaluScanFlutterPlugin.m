#import "XindaluScanFlutterPlugin.h"
#if __has_include(<xindalu_scan_flutter/xindalu_scan_flutter-Swift.h>)
#import <xindalu_scan_flutter/xindalu_scan_flutter-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "xindalu_scan_flutter-Swift.h"
#endif

@implementation XindaluScanFlutterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftXindaluScanFlutterPlugin registerWithRegistrar:registrar];
}
@end
