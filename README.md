Creation of Test Automation Framework using Java and Selenium: 
Covered Selenium Webdriver, Java, Page Object Model, Custom Maven set up, Custom Maven command set up and execution, Custom Reporting and Logging Mechanism, Cross-Browser compatibility testing, POM,  TestNG, Docker, GitHub Actions, Jenkins.
TestNG features such as Annotations/TestNG Listeners to customize test behavior like logging, reporting(Base-64 format), after when test fails or passes.
- Parallally executed both tests in Maven POM.xml and TestNG xml file across multiple platforms
- Docker- Installed Docker Desktop, Docker yaml setup, performed test code execution
- GitHub push/actions - executed the test code using in testng.xml(Each test with different browser)(CI/CD and manual setup)
- Executed the code in Jenkins

In this project, I worked with selenium, focusing on xpath locator strategy to be able to locate web elements. Automated the process and printed the Products Overview page Title and first product name to the console. Implied test strategy using TestNG(parameterization)
Included additional code for taking screenshots, saving to a file. 
Generated reports based on whether test fails or passes with screenshot attached.
Ran the test code in Docker Container both Standalone and in Selenium grid, creating nodes for both chrome and firefox browsers. Developed docker-compose files for both.
Handled the some of Docker and browser errors, code refactoring and debugging.


