This README will present the build and how to run it.

# Magazine REST API

This is application works as a Magazing manager which allows to store, retrieve, update, delete a product and list all of them.

## Build

The app uses Java 11 as its main programming languaje. Java basic knowledge might help to understand and to further develop.

This project uses [Gradle](https://gradle.org/) as its build automation tool, but no prior knowledge is required.

This project is deployed using [Spring Boot](https://spring.io/projects/spring-boot) and controlled by Spring MVC.

For Data Access [Spring DataJPA](https://spring.io/projects/spring-data-jpa) is used connected to a [PostgreSQL](https://www.postgresql.org/). A relational database was chosen as the magazine might grow implementing other objects that will be related to products. One example could be a cart, which contains multiple products. In particular, PostgreSQL was chosen to have more advanced tools to work with tables.

[JUnit](https://junit.org/junit5/) is used for unit testing enhanced with mocks by [Mockito](https://site.mockito.org/).

## Environment Setup

1. Download [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/).
2. Download an IDE: [Visual Studio Code](https://code.visualstudio.com/) (or [Eclipse](https://www.eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)).
3. Open the project from your IDE and download Java and Gradle extensions.
4. Download [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) for your Operating System and build a `product` table on the default port `5432`.

## Run Project

Run `MagazineApplication.java`. Magazine REST API working on port `8080`.

## Endpoints

To interact with the API use:

-   List Products: `GET http://localhost:8080/api/v1/product`.

-   Create a Product: `POST http://localhost:8080/api/v1/product`. With a payload representing the desired `Product` object in JSON.

-   Read a Product by its SKU: `GET http://localhost:8080/api/v1/product/{sku}`.

-   Update a Product by its SKU: `PUT http://localhost:8080/api/v1/product/{sku}`. With a payload representing a `Product` object in JSON with the new data. The fields that are not desired to be updated can be omitted.

-   Delete a Product by its SKU: `DELETE http://localhost:8080/api/v1/product/{sku}`.

Note 1: SKU means Stock-Keeping unit and is the identifier commonly used by vendors for their products.

Note 2: The updations cannot currently erase not even optional fields such as `size` and `otherImageURLs`. This limitation comes from the fact of considering `null` fields as not desired to be updated. In order to enable this, a updation mask could be used to indicate which fields are desired to be updated differentiating them from the deletion of a field.
