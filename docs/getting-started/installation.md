# Installation

This guide will help you add Payper to your Java project using Maven or Gradle.

## Prerequisites

- **Java 17 or higher** - Payper is tested on Java LTS releases 17, 21, and 25
- **Maven 3.6+** or **Gradle 7.0+** - Your build tool of choice

---

## Maven Installation

Payper is available on Maven Central. Add the appropriate dependency for the PayPal API you want to use.

### Orders API

[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-orders-v2.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-orders-v2)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-orders-v2/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-orders-v2)

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-orders-v2</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-orders-v2:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-orders-v2:1.0.0")
    ```

### Subscriptions API

[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-subscriptions-v1.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-subscriptions-v1)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-subscriptions-v1/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-subscriptions-v1)

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-subscriptions-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-subscriptions-v1:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-subscriptions-v1:1.0.0")
    ```

### Payments API

[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-payments-v2.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-payments-v2)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-payments-v2/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-payments-v2)

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-payments-v2</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-payments-v2:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-payments-v2:1.0.0")
    ```

### Invoices API

[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-invoices-v2.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-invoices-v2)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-invoices-v2/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-invoices-v2)

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-invoices-v2</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-invoices-v2:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-invoices-v2:1.0.0")
    ```

### Catalog Products API

[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-catalog-products-v1.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-catalog-products-v1)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-catalog-products-v1/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-catalog-products-v1)

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-catalog-products-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-catalog-products-v1:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-catalog-products-v1:1.0.0")
    ```

### Webhooks API

[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-webhooks-v1.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-webhooks-v1)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-webhooks-v1/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-webhooks-v1)

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-webhooks-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-webhooks-v1:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-webhooks-v1:1.0.0")
    ```

---

## Next Steps

- **[Quick Start Guide](quickstart.md)** - Learn how to make your first API call
- **[Authentication](authentication.md)** - Configure your PayPal credentials
- **[API Documentation](../apis/orders.md)** - Explore available APIs
