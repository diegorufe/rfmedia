import 'package:flutter/material.dart';
import '../../../widgets/pages/home/home_page_widget.dart';
import '../../../../rfFluttler/core/widgets/pages/base_page_state.dart';
import '../../../../rfFluttler/core/i18n/i18n.dart';
import './../../../beans/app_certified.dart';
import '../../../widgets/pages/java6/java6_page_widget.dart';

class HomePageState extends BasePageState<HomePageWidget> {
  List<AppCertified> _listOfAppCertified = new List();

  @override
  String title(BuildContext context) {
    return I18nUtil.of(context).msg("i18n_title");
  }

  _onTabAppCertified(BuildContext context, int index) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => Java6PageWidget()),
    );
  }

  _createCard(BuildContext context, int index) {
    AppCertified appCertified = _listOfAppCertified[index];

    return new GestureDetector(
      onTap: () {
        _onTabAppCertified(context, index);
      },
      child: Container(
        decoration: BoxDecoration(
          color: Color.fromRGBO(0, 0, 0, 0.7),
          image: DecorationImage(
            image: AssetImage(appCertified.imagePath),
            fit: BoxFit.cover,
          ),
        ),
        height: 60.0,
        margin: const EdgeInsets.symmetric(
          vertical: 10.0,
          horizontal: 10.0,
        ),
        child: new Stack(
          fit: StackFit.expand,
          children: <Widget>[
            FittedBox(
              fit: BoxFit.fitWidth,
              alignment: Alignment.bottomCenter,
              child: Container(
                padding: const EdgeInsets.all(10.0),
                color: Color.fromRGBO(0, 0, 0, 0.4),
                child: Text(
                  _listOfAppCertified[index].nameCertified,
                  style: TextStyle(
                    fontSize: 12.0,
                    color: Color.fromRGBO(255, 255, 255, 1),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  renderBody(BuildContext context) {
    _listOfAppCertified.add(new AppCertified(
        I18nUtil.of(context).msg("i18n_apps_java_6_certified"),
        "assets/images/java.png"));

    return GridView.count(
      // Create a grid with 2 columns. If you change the scrollDirection to
      // horizontal, this produces 2 rows.
      crossAxisCount: 2,
      // Generate 100 widgets that display their index in the List.

      children: List.generate(1, (index) {
        return this._createCard(context, index);
      }),
    );
  }
}
