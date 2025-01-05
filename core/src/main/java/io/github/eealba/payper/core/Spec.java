package io.github.eealba.payper.core;

public class Spec<T1> {
    private final Payper payper;
    private final String path;
    private final Class<?> entityClass;
    private final Class<?> errorClass;
    private final Class<T1> clazz;

    public Spec(Class<T1> clazz, Payper payper, String path, Class<?> entityClass, Class<?> errorClass) {
        this.payper = payper;
        this.path = path;
        this.entityClass = entityClass;
        this.errorClass = errorClass;
        this.clazz = clazz;
    }
    public Class<T1> clazz() {
        return clazz;
    }

    public Payper payper() {
        return payper;
    }

    public String path() {
        return path;
    }

    public Class<?> entityClass() {
        return entityClass;
    }

    public Class<?> errorClass() {
        return errorClass;
    }
}
