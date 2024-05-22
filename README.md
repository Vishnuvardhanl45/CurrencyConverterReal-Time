# CurrencyConverterReal-Time

Project Name: Real Time Currency Converter

Technologies Used:
Java Servlets
JSP (JavaServer Pages)
JSTL (JavaServer Pages Standard Tag Library)
HTTPURLConnection
JSONObject (org.json)
Apache Tomcat
Maven
HTML, CSS

Description:
This repository contains a web-based currency converter application built using Java Servlets and JSP for the backend, and HTML/CSS for the frontend. It allows users to convert an amount from one currency to another using realtime exchange rates.

The repository utilizes an API provided by "exchangerate-api.com" to obtain real-time exchange rates. This API is accessed through the URL specified in the API_URL constant within the ConvertCurrency class with API_KEY

private static final String API_KEY = "6ce5850849e0471ca4727ff2";
private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

How to Run:
1.Download the repository
https://github.com/Vishnuvardhanl45/CurrencyConverterReal-Time

2.Open Visual Studio Code and extract the downloaded repository.
Add the folder to VS Code and then open the terminal.
Run the command-->
mvn clean install

3.Deploy the project to Apache Tomcat:
If using the embedded Tomcat plugin in Maven, you can run 
mvn tomcat7:run

Alternatively, you can manually deploy the generated WAR file (target/demo.war) to your local Tomcat server.

4.Access the application
Open a web browser and navigate to http://localhost:8080/demo

How to Contribute
1.Fork the repository to your own GitHub account.
2.Clone the forked repository to your local machine.
3.Create a new branch for your feature or bug fix: 

git checkout -b feature-name

4.Make your changes and commit them:
git add .
git commit -m "Description of your changes"

5.Push your changes to your fork: 
git push origin feature-name

6.Create a pull request from your fork to the original repository.

License:
This project is licensed under the MIT License.

Acknowledgements
Java Servlet and JSP documentation for providing detailed guidelines on building web applications.
Apache Tomcat for providing a robust server platform to deploy the application.
Maven for simplifying project management and build processes.
