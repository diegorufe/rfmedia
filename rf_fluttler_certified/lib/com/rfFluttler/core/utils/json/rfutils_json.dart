import 'dart:convert';

class RFUtilsJson {
  static parseJsonToMap(String jsonString) {
    return jsonDecode(jsonString);
  }
}
