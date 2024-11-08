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
            .build();
    private final AccessLevel classAccessLevel;
    private final AccessLevel methodAccessLevel;
    private final AccessLevel fieldsAccessLevel;
    private final boolean accessorChain;
    private final boolean accessorFluent;

    public static CodeGeneratorParameter defaultParameter() {
        return defaultParameters;
    }

    public enum AccessLevel {
        PUBLIC, PACKAGE_PRIVATE, PROTECTED, PRIVATE;
        public String toJavaAccessLevel() {
            switch (this) {
                case PUBLIC:
                    return "public";
                case PACKAGE_PRIVATE:
                    return "";
                case PROTECTED:
                    return "protected";
                case PRIVATE:
                    return "private";
                default:
                    return "";
            }
        }
    }
}
