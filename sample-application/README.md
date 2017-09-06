## Sample Application

Purpose of this sample Spring Boot application is to demonstrate how you can use Alternative Payment Java SDK on Java project.

### PREREQUISITE

- Installed Java 1.7
- Installed Maven 3.x with configured Java

### Optional: ENABLE TLS ON JAVA 1.7

If server is using TLS 1.2 or TLS1.1 (which is the case with Alternative Payments API server) you need to enable it in Java 1.7.
Both are supported by JRE 1.7 but not enabled by default. There are two ways how you can do it:

1. Add `-Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2` when starting your application
2. Add `System.setProperty(“https.protocols”, “TLSv1,TLSv1.1,TLSv1.2”);` to your client application code.

This sample application is using second approach.

Reference is [this page](https://tonyyan.wordpress.com/2015/07/17/enabled-tls-1-2-and-tls-1-1-on-java-7/).

### RUNNING APPLICATION

- Clone repo
- Inside sample-application folder run `mvn clean install`
- Add your API secret key and API public key in [application.yml](https://github.com/AlternativePayments/ap-java-sdk/blob/master/sample-application/src/main/resources/application.yml) located in `src/main/resources/`
- Start Spring Boot application running `mvn spring-boot:run` from command line
- You can access sample application on `http://localhost:8080`
