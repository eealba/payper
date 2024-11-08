package io.github.eealba.payper.apibuilder;
import java.util.List;
import java.util.Optional;

public interface OperationDef {
    List<String> tags();
    String summary();
    String description();
    String operationId();
    HttpMethod httpMethod();
    List<ParameterDef> parameters();
    String path();
    Optional<ApiRequestDef> requestModel();
    List<ApiResponseDef> responseModel();
}
