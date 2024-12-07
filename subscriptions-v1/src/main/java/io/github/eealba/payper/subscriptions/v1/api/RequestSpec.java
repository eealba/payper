package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.web.Method;

public interface RequestSpec<T, R1, R2> {
    T withPrefer(String prefer);

    T withPaypalRequestId(String paypalRequestId);

    ResponseSpec<R1, R2> retrieve();

    default Method getMethod() {
        return Method.GET;
    }


    Class<R1> getEntityClass();

    Class<R2> getErrorEntityClass();


    interface BodyRequestSpec<T extends BodyRequestSpec<T, T2, T3, T4>, T2, T3, T4> extends RequestSpec<T, T3, T4> {
        T withBody(T2 body);

    }

    interface PostRequestSpec<T extends PostRequestSpec<T, T2, T3, T4>, T2, T3, T4> extends BodyRequestSpec<T, T2, T3, T4> {
        default Method getMethod() {
            return Method.POST;
        }
    }

    interface GetByIdRequestSpec<T, T2, T3> extends RequestSpec<T, T2, T3> {
        T withId(String id);
    }

    interface PatchRequestSpec<T extends PatchRequestSpec<T, T2, T3, T4>, T2, T3, T4> extends BodyRequestSpec<T, T2, T3, T4> {
        default Method getMethod() {
            return Method.PATCH;
        }
    }
}
