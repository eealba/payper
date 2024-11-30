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
package io.github.eealba.payper.core;


/**
 * The type Payper.
 * This class is used to handle requests in the Payper library.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see PayperProvider
 * @see PayperConfig
 * @see PayperRequest
 * @see PayperResponse
 * @see PayperResponse.PayperResponseSpec
 *
 * @author Edgar Alba
 */
public interface Payper {
    static Payper newPayper() {
        return newPayper(PayperConfig.builder().build());
    }
    /**
     * New payper payper.
     *
     * @return the payper
     */
    static Payper newPayper(PayperConfig config) {
        return PayperProvider.provider().createPayper(config);
    }


    /**
     * Send response spec.
     *
     * @param request the request
     * @param bodyHandler the body handler
     * @return the response spec
     */
    <R1, R2> PayperResponse.PayperResponseSpec<R1, R2> send(PayperRequest request,
                                     PayperResponse.BodyHandler<R1> bodyHandler,
                                     PayperResponse.BodyHandler<R2> bodyHandler2);



}
