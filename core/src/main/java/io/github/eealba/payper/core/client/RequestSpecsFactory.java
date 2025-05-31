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
package io.github.eealba.payper.core.client;


/**
 * Factory for creating request specifications.
 *
 * @author Edgar Alba
 * @version 1.0
 * @see Spec
 * @see PayperProvider
 * @see PayperProvider#provider PayperProvider#provider
 * @since 1.0
 */
public abstract class RequestSpecsFactory {
    private static final RequestSpecsFactory INSTANCE = PayperProvider.provider().createRequestSpecsFactory();

    /**
     * Creates a new request specification.
     *
     * @param <T>  the type of the request specification
     * @param <R1> the type parameter
     * @param <R2> the type parameter
     * @param spec the spec
     * @return the request specification
     */
    public abstract <T, R1, R2> T requestSpec(Spec<T, R1, R2> spec);

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RequestSpecsFactory getInstance() {
        return INSTANCE;
    }
}
