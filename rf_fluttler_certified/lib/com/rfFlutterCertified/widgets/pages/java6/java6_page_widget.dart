import 'package:flutter/material.dart';
import 'java6_page_state.dart';
import '../../../../rfFluttler/core/widgets/pages/base_page_widget.dart';

class Java6PageWidget extends BasePageWidget {
  Java6PageWidget({Key key}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  @override
  Java6PageState createState() => Java6PageState();
}

