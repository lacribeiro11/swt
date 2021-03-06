# Fernlehre - Gruppenübung

## Aufgabenstellung

Entwickeln Sie eine Testautomatisierungslösung für eine Multi-Channelanwendung am Beispiel der ÖBB Website und ÖBB App

### Anforderungen an die Lösung

* Liest Testfälle in Gherkin-Syntax ein: (Given-When-Then)
* 2 Testfälle je Channel ([Testfälle](#test-cases))
* Führt die Testfälle gegen ÖBB Web- und App-Variante aus
* Gesammelte Protokolliert der Ergebnisse

### Technologiestack

* Java/Maven Projekt
* Integration Selenium für Webchannel
* Integration Appium für mobilen Channel
* Web Driver for Mac, Linux and Windows

### Abgabe

* Demo am 29.6.., max. 15‘
* Dokumentierter Sourcecode

## <a name="test-cases"></a>Beispieltestfälle

### Westbahnzüge werden nicht buchbar angezeigt

```Given StarteWebApp
When SucheZugverbindung
|Von |Nach| Datum |Uhrzeit| Ab |An
|Wien |Linz| Montag in 3 Wochen |09:00| 1 | 0
Then ErgebnislisteEnthält
|Uhrzeit| Von Bahnhof | Zug | buchbar
|09:42| Wien Hbf| Westbahn | nein
|10:06| Wien Westbahnhof| Westbahn | nein
```

### Zug von Wien nach Linz kostet 38,50€

```Given StarteWebApp AND NavigiereZuTicketBuchung
When Wähle
|Von |Nach| Datum |Uhrzeit| Anzahl | Ermäßigung
|Wien |Linz| Montag in 3 Wochen |1| keine
Then TicketKostet €38,50
```

## Setup

### What we need

* Java JDK https://adoptopenjdk.net/ 11 LTS
* Maven http://maven.apache.org/download.cgi Binary zip or tar.gz archive
* Appium Desktop https://github.com/appium/appium-desktop/releases/tag/v1.21.0
* Android Emulator https://developer.android.com/studio
* ChromeDriver https://chromedriver.storage.googleapis.com/index.html?path=91.0.4472.19/
* FirefoxDriver https://github.com/mozilla/geckodriver/releases/tag/v0.29.1

### Android Virtual Device

* Phone: Pixel 2
* API: 30
* Release Name: R
* ABI: x86 or x86_64
* Target: Android 11.0 (Google APIs)

#### Install OEBB App

* https://m.apkpure.com/de/%C3%B6bb-%E2%80%93-train-tickets-more/at.oebb.ts

### Appium Capabilities

Start Appium Server
Start Inspector Session and insert the following in the desired Capabilities:

```json
{
  "deviceName": "emulator-5554",
  "platformName": "android",
  "appPackage": "at.oebb.ts",
  "appActivity": "at.oebb.ts.SplashActivity"
}
```
## How to generate the report

Inside the **swt** directory

1. Run the tests: `mvn test`
1. Create the report: `mvn cluecumber-report:reporting`

To generate the reports, this plugin https://github.com/trivago/cluecumber-report-plugin was used
