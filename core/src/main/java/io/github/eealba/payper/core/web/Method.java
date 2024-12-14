/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.core.web;

/**
 * The enum Method.
 * This enum represents the HTTP methods that can be used in a request.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Method method = Method.GET;
 * System.out.println("HTTP Method: " + method);
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public enum Method {
    /**
     * The GET method.
     * Used to request data from a specified resource.
     */
    GET,

    /**
     * The POST method.
     * Used to send data to a server to create/update a resource.
     */
    POST,

    /**
     * The PUT method.
     * Used to send data to a server to create/update a resource.
     */
    PUT,

    /**
     * The DELETE method.
     * Used to delete a specified resource.
     */
    DELETE,

    /**
     * The PATCH method.
     * Used to apply partial modifications to a resource.
     */
    PATCH

}