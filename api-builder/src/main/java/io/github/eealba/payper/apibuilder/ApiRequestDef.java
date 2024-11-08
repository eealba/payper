package io.github.eealba.payper.apibuilder;

import java.util.List;

public interface ApiRequestDef {
    String description();
    ModelDef model();
    List<ModelDef> refModels();

}
