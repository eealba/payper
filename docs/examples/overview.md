# Examples Overview

Payper maintains a separate repository with complete, runnable examples that demonstrate how to use the SDK in real-world scenarios.

**Examples Repository**: [https://github.com/eealba/payper-examples](https://github.com/eealba/payper-examples)

---

## Available Examples

### 🚀 payper-5-minutes

**Quick validation of your PayPal sandbox credentials**

A 5-minute quickstart that helps you validate your PayPal sandbox credentials and make your first API call using the Catalog Products API.

- **Purpose**: Verify setup and credentials
- **APIs Used**: Catalog Products API v1
- **Complexity**: Beginner
- **Time**: 5 minutes

**Link**: [payper-5-minutes](https://github.com/eealba/payper-examples/tree/main/payper-5-minutes)

---

### 📦 payper-orders-basic

**Complete Orders API workflow demonstration**

Demonstrates a complete order flow including creation, retrieval, payment source confirmation, and capture using the Orders API v2.

- **Purpose**: Learn order processing workflow
- **APIs Used**: Orders API v2
- **Complexity**: Intermediate
- **Features**:
  - Create orders
  - Retrieve order details
  - Confirm payment source
  - Capture payments

**Link**: [payper-orders-basic](https://github.com/eealba/payper-examples/tree/main/payper-orders-basic)

---

### 🔄 subscriptions-app

**Subscription management with sync and async examples**

Demonstrates product and subscription plan creation with both synchronous and asynchronous implementations.

- **Purpose**: Learn subscription workflow
- **APIs Used**: Subscriptions API v1, Catalog Products API v1
- **Complexity**: Intermediate
- **Features**:
  - Create products
  - Create billing plans
  - Manage subscriptions
  - Synchronous version (`App.java`)
  - Asynchronous version (`AppAsync.java`)

**Link**: [subscriptions-app](https://github.com/eealba/payper-examples/tree/main/subscriptions-app)

---

### 🛒 webstore

**Full-featured Spring Boot e-commerce demo**

A complete Spring Boot demo application showcasing catalog management, checkout, and backoffice integrations with OpenAPI documentation.

- **Purpose**: Production-ready integration example
- **Framework**: Spring Boot
- **APIs Used**: Multiple PayPal APIs
- **Complexity**: Advanced
- **Features**:
  - Product catalog
  - Shopping cart
  - Checkout flow
  - Order management
  - Backoffice integration
  - OpenAPI/Swagger documentation
  - REST API endpoints

**Link**: [webstore](https://github.com/eealba/payper-examples/tree/main/webstore)

---

## How to Run Examples

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PayPal Sandbox credentials ([Get them here](https://developer.paypal.com/dashboard/))

### Steps

1. **Clone the examples repository**:
   ```bash
   git clone https://github.com/eealba/payper-examples.git
   cd payper-examples
   ```

2. **Configure credentials**:
   ```bash
   export PAYPAL-CLIENT-ID=YOUR_CLIENT_ID
   export PAYPAL-CLIENT-SECRET=YOUR_CLIENT_SECRET
   export PAYPAL-BASE-URL=https://api-m.sandbox.paypal.com
   ```

3. **Navigate to an example**:
   ```bash
   cd payper-5-minutes
   ```

4. **Run with Maven**:
   ```bash
   mvn clean compile exec:java
   ```

---

## Example Structure

Each example follows a consistent structure:

```
example-name/
├── pom.xml                 # Maven dependencies
├── README.md               # Example-specific documentation
└── src/
    └── main/
        └── java/
            └── ...         # Example code
```

---

## Learning Path

We recommend following this learning path:

1. **Start Here**: [5 Minutes Start](5-minutes.md)
   - Validate your setup
   - Make your first API call
   - Understand basic concepts

2. **Next**: [Orders Example](orders.md)
   - Learn order workflow
   - Handle payments
   - Capture funds

3. **Then**: [Subscriptions Example](subscriptions.md)
   - Set up recurring billing
   - Manage subscriptions
   - Work with async operations

4. **Finally**: [webstore](https://github.com/eealba/payper-examples/tree/main/webstore)
   - See production-ready integration
   - Explore advanced features
   - Learn best practices

---

## What You'll Learn

### From All Examples

- ✅ How to configure Payper credentials
- ✅ How to create and use API clients
- ✅ How to build requests with fluent API
- ✅ How to handle responses and errors
- ✅ How to use both sync and async operations

### From Specific Examples

**5-minute quickstart**:
- Basic setup and configuration
- Simple API call patterns
- Credential validation

**Orders example**:
- Complete order lifecycle
- Payment processing
- Error handling

**Subscriptions example**:
- Product and plan creation
- Subscription management
- Async programming patterns

**Webstore**:
- Spring Boot integration
- Production architecture
- REST API design
- OpenAPI documentation

---

## Contributing Examples

Have an interesting use case? Contribute to the examples repository!

1. Fork the repository
2. Create your example
3. Add documentation
4. Submit a pull request

**Guidelines**:
- Keep examples focused and simple
- Include a README with clear instructions
- Add comments to explain key concepts
- Test with sandbox credentials

---

## Support

If you have questions about examples:

1. Check the example's README
2. Review the main [Payper documentation](../getting-started/installation.md)
3. Open an issue in the [payper-examples repository](https://github.com/eealba/payper-examples/issues)

---

## Related Resources

- **[5 Minutes Start Tutorial](5-minutes.md)** - Detailed walkthrough
- **[Orders Example Guide](orders.md)** - Orders workflow explained
- **[Subscriptions Example Guide](subscriptions.md)** - Subscriptions workflow explained
- **[API Documentation](../apis/orders.md)** - API reference
- **[GitHub Repository](https://github.com/eealba/payper-examples)** - Browse all examples
