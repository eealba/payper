package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.OperationDef;

public interface OpenApiMapper {
    OperationDef mapOperation(OperationParameter operationParameter);
//    List<FieldDef> explodeSchema(Schema<?> schema, Map<String, Schema<?>> components, String packageName);

}
