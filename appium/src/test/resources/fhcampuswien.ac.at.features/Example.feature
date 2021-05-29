Feature: Open the Browser and search in google

  Scenario: SC1 Buy ticket from Wien to Linz for 01.NEXT_MONTH at 09:00
    Given date "01.NEXT_MONTH" was selected
    Given time "09:00" was selected
    Given route from "WIEN" was selected
    Given route to "LINZ" was selected
    When "One-way tickets" was pressed
    Then Router Planner shows the values for: From "Wien Hbf", To: "Linz/Donau Hbf"

