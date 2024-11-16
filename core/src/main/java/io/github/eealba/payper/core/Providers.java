package io.github.eealba.payper.core;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Providers.
 *
 * @author Edgar Enrique Alba Barrile (eealba@gmail.com)
 */
public class Providers {
    private static final Map<String, Object> providers = new ConcurrentHashMap<>();

    private Providers() {
    }

    /**
     * Gets provider.
     *
     * @param <T>             the type parameter
     * @param provider        the provider
     * @param defaultProvider the default provider
     * @return the provider
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
     *
     * @param <T>          the type parameter
     * @param provider     the provider
     * @param providerImpl the provider
     */
    public static <T> void registerDefaultProvider(Class<T> provider, Object providerImpl) {
        providers.put(Objects.requireNonNull(provider).getName(), Objects.requireNonNull(providerImpl));
    }

    /**
     * Clear this loader's provider cache so that all providers will be reloaded.
     *
     * <p>
     * This method is intended for use in situations in which new providers can be
     * installed into a running Java virtual machine.
     */
    public static void clear() {
        providers.clear();
    }

}
