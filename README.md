# Fernlehre - Gruppenübung

## Aufgabenstellung

Entwickeln Sie eine Testautomatisierungslösung für eine Multi-Channelanwendung am Beispiel der ÖBB Website und ÖBB App

### Anforderungen an die Lösung

* Liest Testfälle in Gherkin-Syntax ein: (Given-When-Then)
* Führt die Testfälle entweder gegen die: Web- oder App-Variante aus
* Protokolliert das Ergebnis der:   Testdurchführung

### Technologiestack

* Testautomatisierungslösung in SDK Ihrer Wahl
* Integration Selenium für Webchannel
* Integration Appium für mobilen Channel

### Vorgehen

* 5 agile Teams, selbstorganisiert
* PO je Team als Schnittstelle zu Auftraggeber

### Ergebnis

* 2 Testfälle je Channel (Suche, Ticketbuchung)
* Demo am 29.6.., max. 15‘
* Dokumentierter Sourcecode

## Beispieltestfälle

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
