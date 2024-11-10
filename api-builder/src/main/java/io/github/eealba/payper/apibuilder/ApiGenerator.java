package io.github.eealba.payper.apibuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class ApiGenerator {
    private final Path openApiFile;
    private final Path sourceFolder;
    private final String packageName;
    private List<OperationDef> operations;
    private Map<String, String> examples;

    private ApiGenerator(Builder builder) {
        this.openApiFile = builder.openApiFile;
        this.sourceFolder = builder.sourceFolder;
        this.packageName = builder.packageName;

    }


    void generate() {
        parseOpenApi();
        cleanSourceFolder();
        generateApi();
        generateExamples();
    }

    private void generateExamples() {
        var filePath = sourceFolder.resolve("../../../src/test/resources/examples");
        examples.forEach((fileName, content) -> {
            try {
                Files.createDirectories(filePath);
                Files.writeString(filePath.resolve(fileName+".json"), content, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Failed to write file " + filePath, e);
            }
        });
    }


    private void parseOpenApi() {
        var parser = ObjectsFactory.openApiParser(openApiFile.toFile(), packageName);
        operations = parser.getOperations();
        if (operations.isEmpty()) {
            throw new RuntimeException("No operations found in OpenAPI file");
        }
        examples = parser.getExamples();
    }

    private void cleanSourceFolder() {
        var filePath = sourceFolder.resolve(packageName.replace(".", "/"));

        try {
            Files.walk(filePath)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            if (!path.equals(filePath)) {
                                Files.delete(path);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to delete " + path, e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to clean source folder", e);
        }
    }
    private void generateApi() {
        var list = getModels();
/*
        list.stream().filter(ApiGenerator::useInterface).distinct().forEach(this::generateInterface);
        list.stream().filter(ApiGenerator::useImmutableClassWithBuilder).distinct()
                .forEach(this::generateInterfaceBuilder);
        list.stream().filter(ApiGenerator::useImmutableClassWithBuilder).distinct()
                .forEach(this::generateClassWithBuilder);
        list.stream().filter(ModelDef::isRecord).distinct().forEach(this::generateRecord);
 */
        list.stream().filter(ModelDef::isEnum).distinct().forEach(this::generateEnum);
        list.stream().filter(ModelDef::isRecord).distinct().forEach(this::generatePublicRecord);
        list.stream().filter(ApiGenerator::useImmutableClassWithBuilder).distinct()
                .forEach(this::generateClassWithBuilder);
//        list.stream().filter(ApiGenerator::useImmutableClassWithBuilder).distinct().forEach(this::generateLombokDTO);
//        list.stream().filter(ApiGenerator::useImmutableClassWithBuilder).distinct().forEach(this::generatePojoDTO);
    }
    @SuppressWarnings("unused")
    private void generateLombokDTO(ModelDef modelDef) {
        var generator = ObjectsFactory.lombokDTOGenerator(modelDef);
        generateCode(generator, modelDef);
    }
    @SuppressWarnings("unused")
    private void generatePojoDTO(ModelDef modelDef) {
        var generator = ObjectsFactory.pojoDTOGenerator(modelDef, CodeGeneratorParameter.defaultParameter());
        generateCode(generator, modelDef);
    }

    @SuppressWarnings("unused")
    private static boolean useInterface(ModelDef modelDef) {
        return !modelDef.isEnum();// && !modelDef.isRecord();
    }
    private static boolean useImmutableClassWithBuilder(ModelDef modelDef) {
        return !modelDef.isEnum() && !modelDef.isRecord();
    }


    private List<ModelDef> getModels() {
        final List<ModelDef> list = new ArrayList<>();
        for (OperationDef operation : operations) {
            operation.requestModel().map(ApiRequestDef::model).ifPresent(list::add);
            operation.requestModel().map(ApiRequestDef::refModels).ifPresent(list::addAll);

            operation.responseModel().stream().map(ApiResponseDef::model)
                    .filter(Objects::nonNull).forEach(list::add);
            operation.responseModel().stream().map(ApiResponseDef::refModels)
                    .filter(Objects::nonNull).forEach(list::addAll);
        }
        return list;
    }


    @SuppressWarnings("unused")
    private void generateInterface(ModelDef modelDef) {
        var generator = ObjectsFactory.interfaceGenerator(modelDef);
        generateCode(generator, modelDef);
    }
    @SuppressWarnings("unused")
    private void generateRecord(ModelDef modelDef) {
        var classDefRecord = new ClassDef(modelDef.classDef().packageName()+ ".internal",
                modelDef.classDef().simpleName() + "Record",
                List.of(modelDef.classDef().simpleName()));
        var modelDefRecord = new ModelDef(classDefRecord, modelDef.fieldDefList(), modelDef.description());

        var generator = ObjectsFactory.recordGenerator(modelDefRecord, CodeGeneratorParameter.builder()
                .classAccessLevel(CodeGeneratorParameter.AccessLevel.PACKAGE_PRIVATE)
                .build());
        generateCode(generator, modelDefRecord);
    }
    private void generatePublicRecord(ModelDef modelDef) {
        var classDefRecord = new ClassDef(modelDef.classDef().packageName(),
                modelDef.classDef().simpleName());
        var modelDefRecord = new ModelDef(classDefRecord, modelDef.fieldDefList(), modelDef.description());

        var generator = ObjectsFactory.recordGenerator(modelDefRecord, CodeGeneratorParameter.defaultParameter());
        generateCode(generator, modelDefRecord);
    }
    @SuppressWarnings("unused")
    private void generateInterfaceBuilder(ModelDef modelDef) {
        var classDefBuilder = new ClassDef(modelDef.classDef().packageName(),
                modelDef.classDef().simpleName() + "Builder");
        var modelDefBuilder = new ModelDef(classDefBuilder, modelDef.fieldDefList(), modelDef.description());

        var generator = ObjectsFactory.interfaceBuilderGenerator(modelDefBuilder);
        generateCode(generator, modelDefBuilder);
    }
    @SuppressWarnings("unused")
    private void generateClassWithBuilder(ModelDef modelDef) {
                var classDefImpl = new ClassDef(modelDef.classDef().packageName(),
                modelDef.classDef().simpleName());
        var modelDefImpl = new ModelDef(classDefImpl, modelDef.fieldDefList(), modelDef.description());

        var generator = ObjectsFactory.immutableClassWithBuilderGenerator(modelDefImpl,
                CodeGeneratorParameter.defaultParameter());
        generateCode(generator, modelDefImpl);
    }
    @SuppressWarnings("unused")
    private void generateClassWithBuilderAndInterfaces(ModelDef modelDef) {
        var packagePrivate1 = CodeGeneratorParameter.builder()
                .fieldsAccessLevel(CodeGeneratorParameter.AccessLevel.PRIVATE)
                .methodAccessLevel(CodeGeneratorParameter.AccessLevel.PACKAGE_PRIVATE)
                .classAccessLevel(CodeGeneratorParameter.AccessLevel.PACKAGE_PRIVATE)
                .accessorChain(false)
                .accessorFluent(true)
                .build();

        var classDefImpl = new ClassDef(modelDef.classDef().packageName() + ".internal",
                modelDef.classDef().simpleName() + "Impl",
                List.of(modelDef.classDef().simpleName()), List.of(modelDef.classDef().simpleName()+"Builder"));
        var modelDefImpl = new ModelDef(classDefImpl, modelDef.fieldDefList(), modelDef.description());

        var generator = ObjectsFactory.immutableClassWithBuilderGenerator(modelDefImpl, packagePrivate1);
        generateCode(generator, modelDefImpl);
    }

    private void generateCode(CodeGenerator generator, ModelDef modelDefBuilder) {
        var fileContent = generator.process();
        var fileName = modelDefBuilder.classDef().simpleName() + ".java";
        var filePath = sourceFolder.resolve(modelDefBuilder.classDef().packageName().replace(".", "/")).resolve(fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, fileContent.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file " + filePath, e);
        }
    }


    private void generateEnum(ModelDef modelDef) {
        var generator = ObjectsFactory.enumGenerator(modelDef);
        generateCode(generator, modelDef);
    }

    static Builder builder(Path openApiFile, Path sourceFolder, String packageName) {
        return new Builder(openApiFile, sourceFolder, packageName);
    }
    static class Builder {
        private final Path openApiFile;
        private final Path sourceFolder;
        private final String packageName;

        public Builder(Path openApiFile, Path sourceFolder, String packageName) {
            this.openApiFile = openApiFile;
            this.sourceFolder = sourceFolder;
            this.packageName = packageName;
        }

        public ApiGenerator build() {
            return new ApiGenerator(this);
        }
    }
}
