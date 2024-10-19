# 1. Abstract
This project (downloadable from git) is a very minimalistic Maven project to run a Selenium Java Test Case performing a simple login against the demo eCommerce website on:
https://www.saucedemo.com/

The project simply imports via Maven Selenium, Chrome Driver (or Gecko Driver for Firefox) and TestNG, and it consists of a signle test case LoginBasicTest.java that executes a login using the WebDriver API and verify the expected result via TestNG assertion.
The project is by purpose very simple to show a very basic setup to run Selenium automation, the pom.xml is the required Maven file with the dependencies, and the testng.xml file is the file used by TestNG to configure the test cases.


# 2. Prerequirements
- Java 1.8.x
- Maven (configured properly with Java)
- Internet connection
- Google Chrome browser
- Selenium Chrome Driver (download here: `http://chromedriver.chromium.org/downloads` select the right platform, download and unzip and store somewhere safe the executable)
- (Optional) Selenium Gecko Driver for Firefox (download here: `https://github.com/mozilla/geckodriver/releases`, select the right platform, download and unzip and store somewhere safe the executable)
- *IMPORTANT:* please provide the selenium chrome driver full path as value of the `CHROME_DRIVER_FULL_PATH` variable inside the test case file `/src/test/java/tests/LoginBasicTest.java`, before executing the tests (see the current property value for an example).
If you want alternatively to use Firefox for the testing, please uncomment rows 23, 30 and 31 and comment rows 22, 28 and 29, and assign to `GECKO_DRIVER_FULL_PATH` a value as the full path to the Gecko driver executable, previously downloaded.

# 3. Installation
- Checkout/download the project from the git repository into a root folder

# 4. To run the test cases
- Open a Command Line
- Go where this project was checked out (root folder)
- Run 
```
"mvn clean verify"
```
The command will clean any existing target folder, compile and build the project and run the test case.
The screen command line output will provide the test logs and information.

Note: on some OS like on a Mac, at the first execution please remember to give the rights from the security checkings to execute the Chrome or Gecko Driver executable file.

