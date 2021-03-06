Feature: Open the ÖBB Mobile-App, search for trains and check ticket prices

  Scenario: SC1 Search train from Wien to Linz for 01.NEXT_MONTH at 09:00
    Given date "01.NEXT_MONTH" was selected
    Given time "09:00" was selected
    Given route from "WIEN" was selected
    Given route to "LINZ" was selected
    When "One-way tickets" was pressed
    Then Router Planner shows the values for: From "Wien Hbf", To: "Linz/Donau Hbf"

  Scenario: SC2 Ticket price is 38,30 Euro
    Given date "01.NEXT_MONTH" was selected
    Given time "09:00" was selected
    Given route from "WIEN" was selected
    Given route to "LINZ" was selected
    When "One-way tickets" was pressed
    Then Router Planner shows the values for: "€ 38,30"
    Given Add to basket was pressed
    Given "Obélix" and "le Gaulois" was inserted
    Given the shopping card price shows the values for: "€ 38,30"
    When Service and Price Details was pressed
    Then the overview shows the values for: fromTo "Wien Hbf > Linz/Donau Hbf", ticketType "Standard-Ticket", adult "Adult, Obélix le Gaulois", discount "No discount", totalAmount "€ 38,30"
