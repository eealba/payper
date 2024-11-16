package io.github.eealba.payper.subscriptions.v1.api;

public interface CommandResult<T,T2> {
    int statusCode();
    T toEntity();
    T2 toErrorEntity();
}
