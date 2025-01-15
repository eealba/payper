package io.github.eealba.payper.core.client.internal;

import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.core.client.Spec;
import io.github.eealba.payper.core.exceptions.PayperException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.Map;

class RequestSpecsFactoryImpl extends RequestSpecsFactory {
    static final RequestSpecsFactory INSTANCE = new RequestSpecsFactoryImpl();

    @Override
    public <T1> T1 post(Spec<T1> spec) {
        var obj = new RequestSpecImpl.PostRequestSpecImpl<>(spec);
        var proxyObj = newProxyInstance(obj, spec);
        return castObject(proxyObj);
    }

    @Override
    public <T1> T1 get(Spec<T1> spec) {
        var obj = new RequestSpecImpl.GetRequestSpecImpl<>(spec);
        var proxyObj = newProxyInstance(obj, spec);
        return castObject(proxyObj);
    }

    @Override
    public <T1> T1 put(Spec<T1> spec) {
        var obj = new RequestSpecImpl.PutRequestSpecImpl<>(spec);
        var proxyObj = newProxyInstance(obj, spec);
        return castObject(proxyObj);
    }


    @Override
    public <T1> T1 delete(Spec<T1> spec) {
        var obj = new RequestSpecImpl.DeleteRequestSpecImpl<>(spec);
        var proxyObj = newProxyInstance(obj, spec);
        return castObject(proxyObj);
    }


    @Override
    public <T1> T1 patch(Spec<T1> spec) {
        var obj = new RequestSpecImpl.PatchRequestSpecImpl<>(spec);
        var proxyObj = newProxyInstance(obj, spec);
        return castObject(proxyObj);
    }

    @SuppressWarnings("unchecked")
    <T1> T1 castObject(Object obj) {
        return (T1) obj;
    }

    private static Object newProxyInstance(Object obj, Spec<?> spec) {
        return Proxy.newProxyInstance(
                spec.clazz().getClassLoader(),
                new Class[]{spec.clazz()},
                new ProxyImpl(obj, spec.alias().orElse(Collections.emptyMap()))
        );
    }

    private record ProxyImpl(Object obj, Map<String, String> alias) implements java.lang.reflect.InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method m, Object[] args)
                throws Throwable {
            try {
                return invoke2(proxy, m, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            } catch (Exception e) {
                throw new PayperException("unexpected invocation exception: " + e.getMessage(), e);
            }
        }

        private Object invoke2(Object proxy, Method m, Object[] args) throws NoSuchMethodException,
                IllegalAccessException, InvocationTargetException {
            Object result;
            for (var entry : alias.entrySet()) {
                if (entry.getKey().equals(m.getName())) {
                    String[] values = entry.getValue().split(",");
                    if (values.length == 1) {
                        m = obj.getClass().getMethod(values[0], m.getParameterTypes());
                    } else {
                        args = getArguments(args, values);
                        Class<?>[] types = new Class[args.length];
                        for (int i = 0; i < args.length; i++) {
                            types[i] = args[i].getClass();
                        }
                        m = obj.getClass().getMethod(values[0], types);
                    }
                }
            }
            result = m.invoke(obj, args);
            if (result == obj) {
                return proxy;
            }
            return result;
        }

        private static Object[] getArguments(Object[] args, String[] values) {
            Object[] params = new Object[args.length + 1];
            System.arraycopy(args, 0, params, 1, args.length);
            params[0] = values[1];
            args = params;
            return args;
        }
    }
}