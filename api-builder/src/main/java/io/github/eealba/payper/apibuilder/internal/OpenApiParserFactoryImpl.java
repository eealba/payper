package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.OpenApiParser;
import io.github.eealba.payper.apibuilder.OpenApiParserFactory;

import java.io.File;

public class OpenApiParserFactoryImpl implements OpenApiParserFactory {

    @Override
    public OpenApiParser create(File openApiSpec, String packageName) {
        return new OpenApiParserImpl(openApiSpec, packageName);
    }
    @Override
    public OpenApiParser create(byte[] openApiSpec, String packageName) {
        return new OpenApiParserImpl(openApiSpec, packageName);
    }
}
