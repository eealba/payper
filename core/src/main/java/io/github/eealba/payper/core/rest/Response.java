package io.github.eealba.payper.core.rest;

public interface Response<T,T2> {
    int statusCode();
    T toEntity();
    T2 toErrorEntity();
    void toVoid();
}
