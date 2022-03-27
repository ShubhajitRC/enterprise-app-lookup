# Enterprise Application Lookup

These System APIs provide a means for insulating the data consumers from the complexity or changes to the underlying
systems. Once built, many consumers can access data without any need to know the details of the underlying systems.
These System APIs are find-grained, business process-independent, and highly reusable.

## Design Principles

- Data consumers shall not aware of any details of underlying systems that provide data.
- Data consumers shall not be impacted if any changes happen to underlying systems.
- Data consumers shall be shields from any form of technical challenges and chaos of underlying systems.

<br>

## Developer Guide to System APIs

These System APIs are built using [REST API](https://www.redhat.com/en/topics/api/what-is-a-rest-api).


## Build, Test and Deploy

### Build

*Compile*

Following is a one-line maven command that embraces shift-left strategy in allowing developer to get a rapid feedback so
that he/she could improve their work quality at early stage of development.

```
mvnw clean package
```

*Run locally*

```
# (Spring Boot 2.x or above)
mvnw spring-boot:run
```

## Consuming System APIs

> **Problem Statement**<br>
> API behavior is typically described in documentation pages which list available endpoints, request data structures and expected response data structures, along with sample query and responses.