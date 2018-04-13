$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("End2End_Test.feature");
formatter.feature({
  "line": 1,
  "name": "UMS login",
  "description": "",
  "id": "ums-login",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Successful Login with Valid Credentials",
  "description": "",
  "id": "ums-login;successful-login-with-valid-credentials",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "user is on ums loginpage",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "user enters login details",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "user clicks on Sign in button",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "user lands on dashboard page",
  "keyword": "Then "
});
formatter.match({
  "location": "Steps.user_is_on_ums_loginpage()"
});
formatter.result({
  "duration": 52325811637,
  "status": "passed"
});
formatter.match({
  "location": "Steps.user_enters_login_details()"
});
formatter.result({
  "duration": 67206388925,
  "status": "passed"
});
formatter.match({
  "location": "Steps.user_clicks_on_Sign_in_button()"
});
