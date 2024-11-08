package io.github.eealba.payper.apibuilder;

import java.util.List;

public interface ApiResponseDef {
    int statusCode();
    String description();
    ModelDef model();
    List<ModelDef> refModels();
}
