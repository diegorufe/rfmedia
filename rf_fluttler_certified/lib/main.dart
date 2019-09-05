import 'package:flutter/material.dart';
import 'com/rfFlutterCertified/widgets/pages/home/home_page_widget.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'com/rfFluttler/core/i18n/i18n.dart';

void main() => runApp(MainApp());

class MainApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      localizationsDelegates: [
        // ... app-specific localization delegate[s] here
        I18nUtilDelegate(),
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ],
      supportedLocales: [
        const Locale('en'), // English
        // ... other locales the app supports
      ],
      //title: I18nUtil.of(context).msg("i18n_title"),
      onGenerateTitle: (BuildContext context) =>
          I18nUtil.of(context).msg("i18n_title"),
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blueGrey,
      ),
      home: HomePageWidget(),
    );
  }
}
