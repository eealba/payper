package io.github.eealba.payper.core;


import java.util.Map;

public abstract class RequestSpecsFactory {
    private static final RequestSpecsFactory INSTANCE = PayperProvider.provider().createRequestSpecsFactory();

    public abstract <T1> T1 post(Spec<T1> spec);
    public abstract <T1> T1 get(Spec<T1> spec);
    public abstract <T1> T1 get(Spec<T1> spec, Map<String, String> alias);
    public abstract <T1> T1 put(Spec<T1> spec);
    public abstract <T1> T1 delete(Spec<T1> spec);
    public abstract <T1> T1 patch(Spec<T1> spec);
    public abstract <T1> T1 patch(Spec<T1> spec, Map<String, String> alias);

    public static RequestSpecsFactory getInstance() {
        return INSTANCE;
    }
}
