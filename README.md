# calameo-api

Java API for the Calaméo document publishing platform.

## Building

    $ git clone git@github.com:mbooth101/calameo-api.git
    $ cd calameo-api
    $ mvn clean install

Unit tests are skipped by default because they require the secret details of a Calaméo PREMIUM or PLATINUM account in order to complete successfully. To enable unit tests during the build, you need to pass extra flags to Maven:

    $ mvn -DskipTests=false -Dcalameo.key=abc -Dcalameo.secret=def -Dcalameo.subscription=123 clean install

## Usage

Simply add calameo-api as a dependency in your Maven POM file:

    <dependency>
      <groupId>uk.co.matbooth</groupId>
      <artifactId>calameo-api</artifactId>
      <version>1.0.0</version>
    </dependency>

For detailed usage information and API documentation, see the [JavaDocs][].

## Licence

This project is licenced under the [Apache Software License, Version 2.0][ASL2].

[JavaDocs]: http://mbooth101.github.io/calameo-api/apidocs/
[ASL2]: http://www.apache.org/licenses/LICENSE-2.0
