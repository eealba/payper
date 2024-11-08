package io.github.eealba.payper.apibuilder;

import java.io.File;

public interface OpenApiParserFactory {
    OpenApiParser create(File openApiSpec, String packageName);
    OpenApiParser create(byte[] openApiSpec, String packageName);

}
