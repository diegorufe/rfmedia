import 'package:flutter/material.dart';

abstract class BasePageState<T extends StatefulWidget> extends State<T> {

  String title(BuildContext context) {
    return "";
  }

  renderAppBar(BuildContext context) {
    return AppBar(
      // Here we take the value from the HomePageWidget object that was created by
      // the App.build method, and use it to set our appbar title.
      title: Text(title(context)),
    );
  }

  renderBody(BuildContext context) {}

  renderScaffold(BuildContext context) {
    return Scaffold(
      appBar: renderAppBar(context),
      body: renderBody(context),
    );
  }

  render(BuildContext context) {
    return renderScaffold(context);
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return render(context);
  }
}
