Feature: Check Ticket Price

  Scenario: SC1 Buy ticket from Wien to Linz for 01.NEXT_MONTH at 09:00
    Given from "WIEN" was selected
    Given to "LINZ" was selected
    Given date menu button was pressed
    Given date "nextMonth" in format TT.MM.YYYY was selected
    Given date settings saved
    When route search button was pressed
    Then route Planner shows the values for: From "Wien", To: "Linz", Date: "nextMonth", Time: "09:00 (Departure)"

  Scenario: SC2 Ticket price from Wien to Linz for 01.NEXT_MONTH
    Given from "WIEN" was selected
    Given to "LINZ" was selected
    Given date menu button was pressed
    Given date "30.06.2021" in format TT.MM.YYYY was selected
    Given date settings saved
    Given ticket book button was pressed
    When ticket show tickets button was pressed
    Then ticket screen shows price "38,30"
