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
 * @since 1.0
 * @version 1.0
 *
 * @see Spec
 * @see PayperProvider
 * @see PayperProvider#provider
 * 
 * @author Edgar Alba
 */
public abstract class RequestSpecsFactory {
    private static final RequestSpecsFactory INSTANCE = PayperProvider.provider().createRequestSpecsFactory();
    /**
     * Creates a new request specification.
     *
     * @param <T> the type of the request specification
     * @return the request specification
     */
    public abstract <T> T post(Spec<T> spec);
    /**
     * Creates a new request specification.
     *
     * @param <T> the type of the request specification
     * @return the request specification
     */
    public abstract <T> T get(Spec<T> spec);
    /**
     * Creates a new request specification.
     *
     * @param <T> the type of the request specification
     * @return the request specification
     */
    public abstract <T> T put(Spec<T> spec);
    /**
     * Creates a new request specification.
     *
     * @param <T> the type of the request specification
     * @return the request specification
     */
    public abstract <T> T delete(Spec<T> spec);
    /**
     * Creates a new request specification.
     *
     * @param <T> the type of the request specification
     * @return the request specification
     */
    public abstract <T> T patch(Spec<T> spec);
    
    public static RequestSpecsFactory getInstance() {
        return INSTANCE;
    }
}
