[travis-image]: https://travis-ci.org/sasfeld-thesis/question-generator.svg?branch=master
[travis-url]: https://travis-ci.org/sasfeld-thesis/question-generator
[codecov-image]: https://codecov.io/gh/sasfeld-thesis/question-generator/coverage.svg?branch=master
[codecov-url]: https://codecov.io/gh/sasfeld-thesis/question-generator?branch=master
[codecov-report]: https://codecov.io/gh/sasfeld-thesis/question-generator/branch.svg?branch=master

# Question Generator [![Travis Build Status][travis-image]][travis-url] [![codecov.io][codecov-image]][codecov-url] [Testing System](http://groupelite.de:8085/web/guest/question-generator)
=======================

Introduction
------------

The question generator is the central software that I create during my master thesis. It targets to automatically process e-learning material from several inputs (raw text, PDF,...), trigger Natural Language Processing on it, detect and combine semantic concepts and generate questions using Natural Language Generation technology as last step.


Build Pipeline
------------

- A push will trigger a build on Travis CI which uses maven to run all tests.
- If the tests were successful, a code coverage report is generated using the CoCo library and pushed to codecov.io
- After that, the generated portlet is pushed to the testing system http://groupelite.de:8085/web/guest/question-generator .

Development - Getting started
------------

1. I recommend the following directory structure to aim for some kind of development standard and avoid any communication issues between developers:

	C:/Masterthesis
  - liferay-portal-server (folder with liferay installation)
  - question-generator (question generator software sources)
2. Clone the question generator software sources:
	```cd C:/Masterthesis```
	```git clone https://github.com/sasfeld-thesis/question-generator.git```
3. Clone the liferay server
  ```git clone https://github.com/sasfeld-thesis/liferay-portal-server.git```
4. Create MySQL user liferay with PW liferay and an identically named db by running 

  ```CREATE USER 'liferay'@'localhost' IDENTIFIED BY 'liferay';```
  
  ```GRANT USAGE ON * . * TO 'liferay'@'localhost' IDENTIFIED BY '***' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;```
  
  ```CREATE DATABASE IF NOT EXISTS `liferay` ;```
  
  ```GRANT ALL PRIVILEGES ON `liferay` . * TO 'liferay'@'localhost';```
  
5. Start the liferay server via CMD (change to liferay directory and run startup.sh in tomcat-x subfolder).

6. View localhost:8080 in your browser, now you are asked to do the setup.

7. Use any admin user you desire, but I recommend 
  - first name: Masterthesis
  - last name: Masterthesis
  - Email: your email address
  - At database, click change, do not use the default hypersonic database!
    - Database type: MySQL
    - JDBC URL: jdbc:mysql://localhost:3306/liferay?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    - JDBC Driver Class Name: com.mysql.jdbc.Driver
    - User name: liferay
    - Password: liferay
    
8. Use “masterthesis” as password.

9. Add the e learning question generator portlet using the "Add" -> Application -> Vaadin -> Question Generator Portlet functionality in liferay.




