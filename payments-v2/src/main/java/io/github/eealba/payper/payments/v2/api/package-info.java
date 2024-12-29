/**
 * This package contains the API interfaces for the PayPal Payments API.
 * <p>
 * These interfaces define the operations available for interacting with the PayPal Payments API,
 * including authorizations, captures, and refunds.
 * <p>
 * Each interface provides methods to initialize requests for specific actions, such as getting an authorization,
 * capturing an authorization, refunding a capture, and more. The requests can be customized with various options
 * to match the requirements of the PayPal API.
 * <p>
 * Example usage:
 * <pre>{@code
 * // Initialize the API client
 * PaymentsApiClient client = PaymentsApiClient.create();
 *
 * // Get an authorization
 * Authorization2 authorization = client.authorizations().get()
 *     .withId("AUTHORIZATION_ID")
 *     .retrieve()
 *     .toEntity();
 *
 * // Capture an authorization
 * Capture2 capture = client.authorizations().capture()
 *     .withId("AUTHORIZATION_ID")
 *     .withBody(CaptureRequest.builder().build())
 *     .retrieve()
 *     .toEntity();
 *
 * // Refund a capture
 * Refund refund = client.captures().refund()
 *     .withId("CAPTURE_ID")
 *     .withBody(RefundRequest.builder().build())
 *     .retrieve()
 *     .toEntity();
 * }</pre>
 * <p>
 * The API interfaces are designed to be flexible and extensible, allowing for easy integration with the PayPal Payments API.
 * Each request can be customized with various options, such as setting headers, request bodies, and query parameters.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
package io.github.eealba.payper.payments.v2.api;