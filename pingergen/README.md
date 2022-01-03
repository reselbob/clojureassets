# pingergen

This is a demonstration API project written in Clojure/Leiningen that publishes the following endpoints:

    * `GET` `/` ;returns a greeting
    * `GET` `/request` ;returns information about the request making the call
    * `GET` `/envars` ;returns the available environment variables
    * `GET` `/memory` ;returns the used and available memory in the JVM environment
    * `GET` `/ping` ;returns a simple ping message with timestamp


## Run the application locally

`lein ring server`

or

`lein run`

## Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

## Packaging as war

`lein ring uberwar`
