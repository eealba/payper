package io.github.eealba.payper.apibuilder;

import java.util.List;
import java.util.Map;

public interface OpenApiParser {
    List<OperationDef> getOperations();

    Map<String, String> getExamples();
}
