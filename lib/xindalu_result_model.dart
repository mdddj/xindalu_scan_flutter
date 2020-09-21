
// To parse this JSON data, do
//
//     final xindaluDataModel = xindaluDataModelFromJson(jsonString);

import 'dart:convert';

XindaluDataResultModel xindaluDataModelFromJson(String str) => XindaluDataResultModel.fromJson(json.decode(str));

String xindaluDataModelToJson(XindaluDataResultModel data) => json.encode(data.toJson());

class XindaluDataResultModel {
  XindaluDataResultModel({
    this.scanBarcodeType,
    this.code1,
    this.scanState,
  });

  int scanBarcodeType;
  String code1;
  String scanState;

  factory XindaluDataResultModel.fromJson(Map<String, dynamic> json) => XindaluDataResultModel(
    scanBarcodeType: json["barcodeType"],
    code1: json["code1"],
    scanState: json["scanState"],
  );

  Map<String, dynamic> toJson() => {
    "barcodeType": scanBarcodeType,
    "code1": code1,
    "scanState": scanState,
  };
}
