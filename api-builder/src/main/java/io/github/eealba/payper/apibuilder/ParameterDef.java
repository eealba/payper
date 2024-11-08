package io.github.eealba.payper.apibuilder;

public interface ParameterDef {
    String name();

    ParameterInType in();

    boolean required();

    String description();

    SchemaDef schema();

}
