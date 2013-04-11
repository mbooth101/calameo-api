# calameo-api

Java API for the Calaméo document publishing platform.

## Usage

For detailed usage information and API documentation, see the [JavaDocs][].

## Build & Test

The source can be checked out from git as follows:

    $ git clone git@github.com:mbooth101/calameo-api.git
    $ cd calameo-api

The API is built as a JAR file using [Apache Maven][]:

    $ mvn install

Unit tests are skipped by default because they require the secret details of a Calaméo PREMIUM or PLATINUM account in order to complete successfully. To enable unit tests during the build, you need to pass extra flags to Maven:

    $ mvn -DskipTests=false -Dcalameo.key=abc -Dcalameo.secret=def -Dcalameo.subscription=123 install

## Licence

This project is licenced under the [Apache Software License, Version 2.0][ASL2].

[JavaDocs]: http://mbooth101.github.io/calameo-api/apidocs/
[Apache Maven]: http://maven.apache.org/
[ASL2]: http://www.apache.org/licenses/LICENSE-2.0
