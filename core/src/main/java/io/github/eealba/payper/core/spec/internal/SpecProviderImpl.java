package io.github.eealba.payper.core.spec.internal;

import io.github.eealba.payper.core.spec.RequestSpecsFactory;
import io.github.eealba.payper.core.spec.SpecProvider;

public class SpecProviderImpl extends SpecProvider {
    @Override
    public RequestSpecsFactory createRequestSpecsFactory() {
        return RequestSpecsFactoryImpl.INSTANCE;
    }

}
