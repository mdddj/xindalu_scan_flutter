import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:xindalu_scan_flutter/xindalu_scan_flutter.dart';

void main() {
  const MethodChannel channel = MethodChannel('xindalu_scan_flutter');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('init', () async {
  });
}
