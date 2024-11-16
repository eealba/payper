package io.github.eealba.payper.subscriptions.v1.api;

import java.util.concurrent.CompletableFuture;

public interface CommandExecutor<T,T2> {
    CommandResult<T, T2> send();
    CompletableFuture<CommandResult<T, T2>> sendAsync();
}
