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

import java.lang.reflect.Constructor;

/**
 * The type Payper provider.
 * This class is used to create Payper objects.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Payper
 * @see PayperConfig
 * @see PayperException
 * @see PayperProvider
 * @see PayperBuilder
 *
 * @author Edgar Alba
 */
public abstract class PayperProvider {
    /**
     * The providerImpl.
     */
    private static PayperProvider providerImpl;
    /**
     * A constant representing the name of the default {@code PayperProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.payper.internal.PayperProviderImpl";

    /**
     * Instantiates a new Payper provider.
     */
    public PayperProvider() {
    }

    /**
     * Creates a Payper provider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a Payper provider
     */
    public static PayperProvider provider() {
        if (providerImpl != null) {
            return providerImpl;
        }
        try {
            Class<?> clazz = Class.forName(DEFAULT_PROVIDER);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> c : constructors) {
                if (c.getParameterCount() == 0) {
                    c.setAccessible(true);
                    providerImpl = (PayperProvider) c.newInstance();
                    return providerImpl;
                }
            }
        } catch (Exception x) {
            throw new PayperException("Provider for JasonerProvider not found", x);
        }
        throw new PayperException("Provider for JasonerProvider not found");
    }


    /**
     * Creates a Payper object.
     *
     * @param config the configuration
     * @return a Payper object
     */
    public abstract Payper createPayper(PayperConfig config);
}
