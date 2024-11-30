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
package io.github.eealba.payper.core.util;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Providers.
 * This class provides utility methods to manage and retrieve provider implementations.
 * It uses a cache to store provider instances and supports loading providers via the ServiceLoader mechanism.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * JsonProvider provider = Providers.getProvider(JsonProvider.class, "io.github.eealba.payper.core.json.internal.JsonProviderImpl");
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public class Providers {
    private static final Map<String, Object> providers = new ConcurrentHashMap<>();

    private Providers() {
        // Private constructor to prevent instantiation
    }

    /**
     * Gets provider.
     * Retrieves a provider implementation for the specified provider class.
     * If no implementation is found, it attempts to load the default provider.
     *
     * @param <T>             the type parameter
     * @param provider        the provider class
     * @param defaultProvider the default provider class name
     * @return the provider implementation
     * @throws ProviderNotFoundException if no provider implementation is found
     */
    public static <T> T getProvider(Class<T> provider, String defaultProvider) {
        T providerImpl = provider.cast(providers.get(provider.getName()));

        if (providerImpl != null) {
            return providerImpl;
        }

        ServiceLoader<T> loader = ServiceLoader.load(provider);
        Iterator<T> it = loader.iterator();
        if (it.hasNext()) {
            providerImpl = it.next();
            providers.put(provider.getName(), providerImpl);
            return providerImpl;
        }

        try {
            Class<?> clazz = Class.forName(defaultProvider);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> c : constructors) {
                if (c.getParameterCount() == 0) {
                    c.setAccessible(true);
                    providerImpl = provider.cast(c.newInstance(new Object[0]));
                    providers.put(provider.getName(), providerImpl);
                    return providerImpl;
                }
            }
        } catch (Exception x) {
            throw new ProviderNotFoundException(defaultProvider, x);
        }

        throw new ProviderNotFoundException(provider.getName());
    }

    /**
     * Register default provider.
     * Registers a default provider implementation for the specified provider class.
     *
     * @param <T>          the type parameter
     * @param provider     the provider class
     * @param providerImpl the provider implementation
     */
    public static <T> void registerDefaultProvider(Class<T> provider, Object providerImpl) {
        providers.put(Objects.requireNonNull(provider).getName(), Objects.requireNonNull(providerImpl));
    }

    /**
     * Clear this loader's provider cache so that all providers will be reloaded.
     * This method is intended for use in situations in which new providers can be
     * installed into a running Java virtual machine.
     */
    public static void clear() {
        providers.clear();
    }
}