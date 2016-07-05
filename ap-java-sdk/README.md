# Java SDK for Alternative Payments

Java library which wraps HTTP communication with Alternative Payments server.

## Installation

### PREREQUISITE

Installed Java 1.7
Installed Maven 3.x with configured Java

### RUNNING APPLICATION

Clone repo
Inside ap-java-sdk folder run `mvn clean install`

## Content of library

Inside library main class for communication is [AlternativePaymentClient](https://github.com/AlternativePayments/ap-java-sdk/blob/master/ap-java-sdk/src/main/java/com/alternativepayments/AlternativePaymentClient.java). It is wrapping HTTP client and it is used to do POST and GET requests to the server.

[Models](https://github.com/AlternativePayments/ap-java-sdk/tree/master/ap-java-sdk/src/main/java/com/alternativepayments/models) have base classes for serialization and deserialization. Each one is using builder to create instance so we avoid construction with lot of null values.

Here is example how to construct `Customer` and send it to server:

```
  Customer customer = new Customer.Builder("John", "Doe", "john@doe.com", "US").address("Rutledge Ave 409")
    .city("Folsom").zip("19033").state("PA").phone("55555555555").build();

  final Customer createdCustomer = alternativePaymentClient.create(customer, Customer.API_ENDPOINT, Customer.class);
```

## Running tests

Tests are using [WireMock](http://wiremock.org/) to mock HTTP requests. We provide pattern and library is making request to embedded WireMock server and we get controlled response. This way we can test library communication which will be put on the wire.

After you finish development run `mvn test` which will start all tests, both unit and integration tests using WireMock.

## Development

When you do development use [java-formatter](https://github.com/AlternativePayments/ap-java-sdk/blob/master/sample-application/java-formatter.xml) inside project which will keep format consistent accross commits.

Also maven is configured to run [checkstyle](http://checkstyle.sourceforge.net/) so make sure you configure properly [checkstyle checker](https://github.com/AlternativePayments/ap-java-sdk/blob/master/sample-application/checkstyle.xml) inside your favorite IDE.

## Contributing

1. Fork it ( `https://github.com/AlternativePayments/ap-java-sdk/fork` )
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request

## Publishing jar to BinTray

Alternative Payments is using [BinTray](https://bintray.com/) as repository for library. It is synchronized with JCenter public repository and JCenter has automatic synchronization with Maven Central. You can find latest version of library on [JCenter](https://bintray.com/bintray/jcenter?filterByPkgName=ap-java-sdk).

When you want to deploy new version, you must increase version in [pom.xml](https://github.com/AlternativePayments/ap-java-sdk/blob/master/ap-java-sdk/pom.xml) and after that run `mvn clean deploy -P extras` which will build jar and upload it to BinTray repo.

