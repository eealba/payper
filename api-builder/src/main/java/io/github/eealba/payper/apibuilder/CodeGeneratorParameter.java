package io.github.eealba.payper.apibuilder;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeGeneratorParameter {
    private static final CodeGeneratorParameter defaultParameters = new CodeGeneratorParameter
            .CodeGeneratorParameterBuilder()
            .fieldsAccessLevel(AccessLevel.PRIVATE)
            .methodAccessLevel(AccessLevel.PUBLIC)
            .classAccessLevel(AccessLevel.PUBLIC)
            .accessorChain(true)
            .accessorFluent(true)
            .useJasonerProperty(true)
            .maxLineLength(120)
            .build();
    private final AccessLevel classAccessLevel;
    private final AccessLevel methodAccessLevel;
    private final AccessLevel fieldsAccessLevel;
    private final boolean accessorChain;
    private final boolean accessorFluent;
    private final boolean useJasonerProperty;
    private final int maxLineLength;

    public static CodeGeneratorParameter defaultParameter() {
        return defaultParameters;
    }

    public enum AccessLevel {
        PUBLIC, PACKAGE_PRIVATE, PROTECTED, PRIVATE;
        public String toJavaAccessLevel() {
            return switch (this) {
                case PUBLIC -> "public";
                case PROTECTED -> "protected";
                case PRIVATE -> "private";
                default -> "";
            };
        }
    }
}
