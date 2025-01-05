package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.RequestSpecsFactory;
import io.github.eealba.payper.core.Spec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.Map;

class RequestSpecsFactoryImpl extends RequestSpecsFactory {
    static final RequestSpecsFactory INSTANCE = new RequestSpecsFactoryImpl();

    @Override
    public <T1> T1 post(Spec<T1> spec) {
        var obj = new RequestSpecImpl.PostRequestSpecImpl<>(spec);
        var proxyObj = ProxyImpl.newInstance(obj, spec);
        return castObject(proxyObj);
    }

    @Override
    public <T1> T1 get(Spec<T1> spec) {
        var obj = new RequestSpecImpl.GetRequestSpecImpl<>(spec);
        var proxyObj = ProxyImpl.newInstance(obj, spec);
        return castObject(proxyObj);
    }
    @Override
    public <T1> T1 get(Spec<T1> spec, Map<String, String> alias) {
        var obj = new RequestSpecImpl.GetRequestSpecImpl<>(spec);
        var proxyObj = ProxyImpl.newInstance(obj, spec, alias);
        return castObject(proxyObj);
    }

    @Override
    public <T1> T1 put(Spec<T1> spec) {
        var obj = new RequestSpecImpl.PutRequestSpecImpl<>(spec);
        var proxyObj = ProxyImpl.newInstance(obj, spec);
        return castObject(proxyObj);
    }

    @Override
    public <T1> T1 delete(Spec<T1> spec) {
        var obj = new RequestSpecImpl.DeleteRequestSpecImpl<>(spec);
        var proxyObj = ProxyImpl.newInstance(obj, spec);
        return castObject(proxyObj);
    }

    @Override
    public <T1> T1 patch(Spec<T1> spec) {
        var obj = new RequestSpecImpl.PatchRequestSpecImpl<>(spec);
        var proxyObj = ProxyImpl.newInstance(obj, spec);
        return castObject(proxyObj);
    }

    @Override
    public <T1> T1 patch(Spec<T1> spec, Map<String, String> alias) {
        var obj = new RequestSpecImpl.PatchRequestSpecImpl<>(spec);
        var proxyObj = ProxyImpl.newInstance(obj, spec, alias);
        return castObject(proxyObj);
    }

    @SuppressWarnings("unchecked")
    <T1> T1 castObject(Object obj) {
        return (T1) obj;
    }

    private static class ProxyImpl implements java.lang.reflect.InvocationHandler {

        private final Object obj;
        private final Map<String, String> alias;

        private static Object newInstance(Object obj, Spec<?> spec) {
            return newInstance(obj, spec, Collections.emptyMap());
        }

        public static <T1> Object newInstance(Object obj, Spec<T1> spec, Map<String, String> alias) {
            return Proxy.newProxyInstance(
                    spec.clazz().getClassLoader(),
                    new Class[]{spec.clazz()},
                    new ProxyImpl(obj, alias));
        }

        private ProxyImpl(Object obj, Map<String, String> alias) {
            this.obj = obj;
            this.alias = alias;
        }


        @Override
        public Object invoke(Object proxy, java.lang.reflect.Method m, Object[] args)
                throws Throwable {
            Object result;
            try {
                System.out.println("before method " + m.getName());
                for (var entry : alias.entrySet()) {
                    if (entry.getKey().equals(m.getName())) {
                        String[] values = entry.getValue().split(",");
                        if (values.length == 1) {
                            m = obj.getClass().getMethod(values[0], m.getParameterTypes());
                        } else {
                            Object[] params = new Object[args.length + 1];
                            //copy the args
                            for (int i = 0; i < args.length; i++) {
                                params[i+1] = args[i];
                            }
                            params[0] = values[1];
                            args = params;
                            Class<?>[] types = new Class[args.length];
                            for (int i = 0; i < args.length; i++) {
                                types[i] = args[i].getClass();
                            }
                            m = obj.getClass().getMethod(values[0],types);
                        }
                    }
                }
                result = m.invoke(obj, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            } catch (Exception e) {
                throw new RuntimeException("unexpected invocation exception: " +
                        e.getMessage());
            } finally {
                System.out.println("after method " + m.getName());
            }
            if (result == obj) {
                return proxy;
            }
            return result;
        }
    }
}