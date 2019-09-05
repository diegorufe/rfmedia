import 'package:flutter/material.dart';
import 'package:rf_fluttler_certified/com/rfFlutterCertified/beans/answer.dart';
import 'package:rf_fluttler_certified/com/rfFlutterCertified/beans/question.dart';
import '../../../widgets/pages/java6/java6_page_widget.dart';
import '../../../../rfFluttler/core/widgets/pages/base_page_state.dart';
import '../../../../rfFluttler/core/i18n/i18n.dart';
import '../../../../rfFluttler/core/utils/json/rfutils_json.dart';

class Java6PageState extends BasePageState<Java6PageWidget> {
  List<dynamic> _jsonData = null;
  List<Question> _questions = null;
  List<Question> _questionsTest = null;
  int _indexQuestion = 0;
  bool _started = false;
  bool _random = false;
  List<bool> _answerscheked;

  _loadJsonData(AsyncSnapshot snapshot) {
    _loadJson(snapshot);

    if (_jsonData != null) {
      _questions = List();
      Question question = null;
      Answer answer = null;

      for (var item in _jsonData) {
        question = new Question();
        question.number = item['number'];
        question.text = item['text'];

        for (String succesAnswer in item['sucessAnswers']) {
          question.succesAnswers.add(succesAnswer);
        }

        for (Map answerMap in item['answers']) {
          answer = new Answer();
          answer.text = answerMap['text'];
          answer.key = answerMap['key'];
          question.answers.add(answer);
        }

        _questions.add(question);
      }
    }
  }

  _loadJson(AsyncSnapshot snapshot) {
    if (_jsonData == null && snapshot.data != null) {
      _jsonData = RFUtilsJson.parseJsonToMap(snapshot.data);
    }
  }

  _createCard(BuildContext context, AsyncSnapshot snapshot) {
    _loadJsonData(snapshot);

    if (_jsonData != null) {
      if (_started) {
        return this._loadQuestion(context);
      } else {
        return this._loadStart(context);
      }
    } else {
      return new Text("");
    }
  }

  void onStartJava6CertifiedPressed() {
    setState(() {
      _random = false;
      this._baseStart();
    });
  }

  void onNextJava6CertifiedPressed() {
    setState(() {
      _answerscheked = null;
      _indexQuestion++;
      if (_indexQuestion >= _questionsTest.length) {
        _indexQuestion = 0;
      }
    });
  }

  void onStartRandomJava6CertifiedPressed() {
    setState(() {
      _random = true;
      this._baseStart();
      _questionsTest.shuffle();
    });
  }

  void _baseStart() {
    _started = true;
    _indexQuestion = 0;
    _questionsTest = _questions;
  }

  _loadStart(BuildContext context) {
    return new Center(
      child: new Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          new Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              new RaisedButton(
                padding: const EdgeInsets.all(8.0),
                textColor: Colors.white,
                color: Colors.blue,
                onPressed: onStartJava6CertifiedPressed,
                child: new Text(
                  I18nUtil.of(context).msg("i18n_apps_java_6_start"),
                ),
              ),
              new RaisedButton(
                onPressed: onStartRandomJava6CertifiedPressed,
                textColor: Colors.white,
                color: Colors.red,
                padding: const EdgeInsets.all(8.0),
                child: new Text(
                  I18nUtil.of(context).msg("i18n_apps_java_6_start_random"),
                ),
              ),
            ],
          )
        ],
      ),
    );
  }

  _loadQuestion(BuildContext context) {
    return new Container(
      child: new Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: this._loadDataQuestion(),
      ),
    );
  }

  _loadDataQuestion() {
    List<Widget> widgets = new List();

    // next button
    widgets.add(
      new Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          new RaisedButton(
            padding: const EdgeInsets.all(8.0),
            textColor: Colors.white,
            color: Colors.blue,
            onPressed: onNextJava6CertifiedPressed,
            child: new Text(
              I18nUtil.of(context).msg("i18n_apps_java_6_next"),
            ),
          ),
        ],
      ),
    );

    // Question text
    widgets.add(
      new Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          new Container(
            padding: const EdgeInsets.all(8.0),
            child: new Text(
              _questionsTest[_indexQuestion].text,
            ),
          ),
        ],
      ),
    );

    Question _question = _questionsTest[_indexQuestion];

    if (_answerscheked == null) {
      _answerscheked = new List();

      if (_random) {
        _question.answers.shuffle();
      }

      for (Answer answer in _question.answers) {
        _answerscheked.add(false);
      }
    }

    // answers text
    int _indexAnswer = 0;
    for (Answer answer in _question.answers) {
      widgets.add(
        new Expanded(
          child: new Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              new Row(
                  children: this._checkboxAnswer(
                      answer.text, _answerscheked[_indexAnswer], _indexAnswer)),
            ],
          ),
        ),
      );
      _indexAnswer++;
    }

    return widgets;
  }

  List<Widget> _checkboxAnswer(String title, bool boolValue, int index) {
    return <Widget>[
      new Container(
        child: Checkbox(
          value: boolValue,
          onChanged: (bool value) {
            setState(() {
              _answerscheked[index] = value;
            });
          },
        ),
      ),
      new Container(
        child: new Flexible(
          child: new Padding(
            padding: const EdgeInsets.only(top: 15.0),
            child: new Text(
              title,
            ),
          ),
        ),
      ),
    ];
  }

  @override
  String title(BuildContext context) {
    return I18nUtil.of(context).msg("i18n_apps_java_6_certified");
  }

  @override
  renderBody(BuildContext context) {
    return new FutureBuilder(
      future: DefaultAssetBundle.of(context)
          .loadString("assets/json/testKiller6.json"),
      builder: (context, snapshot) {
        return _createCard(context, snapshot);
      },
    );
  }
}
