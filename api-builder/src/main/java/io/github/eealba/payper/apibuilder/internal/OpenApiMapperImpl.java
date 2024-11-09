package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ApiRequestDef;
import io.github.eealba.payper.apibuilder.ApiResponseDef;
import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.OperationDef;
import io.github.eealba.payper.apibuilder.ParameterDef;
import io.github.eealba.payper.apibuilder.ParameterInType;
import io.github.eealba.payper.apibuilder.SchemaDef;
import io.github.eealba.payper.apibuilder.Utils;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.ComposedSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

class OpenApiMapperImpl implements OpenApiMapper {

    @Override
    public OperationDef mapOperation(OperationParameter operationParameter) {
        return OperationDefImpl.builder()
                .tags(operationParameter.operation().getTags())
                .summary(operationParameter.operation().getSummary())
                .description(operationParameter.operation().getDescription())
                .operationId(operationParameter.operation().getOperationId())
                .httpMethod(operationParameter.httpMethod())
                .parameters(operationParameter.operation().getParameters().stream().map(this::mapParameter)
                        .collect(Collectors.toList()))
                .path(operationParameter.path())
                .requestModel(Optional.ofNullable(operationParameter.operation().getRequestBody())
                        .map((RequestBody req) -> mapApiRequest(new ApiRequestParameter(req,
                                operationParameter.components(), operationParameter.packageName())))
                        .orElse(null))
                .responseModel(operationParameter.operation().getResponses().entrySet().stream()
                        .filter(entry -> "default".equals(entry.getKey()) || entry.getKey().startsWith("2"))
                        .map(entry -> this.mapApiResponse(entry.getKey(), entry.getValue(),
                                operationParameter.components(), operationParameter.packageName()))
                        .collect(Collectors.toList()))

                .build();
    }

    private List<FieldDef> getFieldsTmp(FieldInternal parent, String packageName, Schema<?> schema, Map<String,
            Schema<?>> components) {
        Schema<?> schema2 = schema;
        if (schema2.get$ref() != null) {
            String ref = getRef(schema2).orElseThrow();
            schema2 = getFieldInternal(ref, components).schema().orElseThrow();
        }
        var requiredFields = Optional.ofNullable(schema2.getRequired()).orElse(Collections.emptyList());
        var properties = Optional.ofNullable(schema2.getProperties()).orElse(Collections.emptyMap());
        return properties.entrySet().stream()
                .map(entry -> buildField(parent, requiredFields, packageName, entry.getKey(), entry.getValue(),
                        components, Collections.emptyList()))
                .collect(Collectors.toList());
    }

    private List<FieldDef> getFields(FieldInternal parent, String packageName, Schema<?> schema, Map<String, Schema<?>> components) {
        Schema<?> schema2 = schema;
        if (schema2.get$ref() != null) {
            String ref = getRef(schema2).orElseThrow();
            schema2 = getFieldInternal(ref, components).schema().orElseThrow();
        }
        var requiredFields = Optional.ofNullable(schema2.getRequired()).orElse(Collections.emptyList());
        var properties = Optional.ofNullable(schema2.getProperties()).orElse(Collections.emptyMap());
        return properties.entrySet().stream()
                .map(entry -> buildField(parent, requiredFields, packageName, entry.getKey(), entry.getValue(), components,
                        Collections.emptyList()))
                .collect(Collectors.toList());
    }


    private ApiRequestDef mapApiRequest(ApiRequestParameter apiRequestParameter) {
        final var ref = getRef(apiRequestParameter.requestBody());
        final var fieldInternal = getFieldInternal(ref.orElseThrow(), apiRequestParameter.components());
        final var fieldDefList = getFields(fieldInternal, apiRequestParameter.packageName(), apiRequestParameter.components());

        var classDef = new ClassDef(apiRequestParameter.packageName(),Utils.normalizeClassName(ref.orElseThrow()));
        var description = fieldInternal.schema().map(Schema::getDescription).orElse("");
        var model = model(classDef, fieldDefList, description);
        var refModels = getRefModels(model, apiRequestParameter.components(), apiRequestParameter.packageName());
        return ApiRequestDefImpl.builder()
                .description(fieldInternal.schema().map(Schema::getDescription).orElse(""))
                .model(model)
                .refModels(refModels)
                .build();
    }

    private static ModelDef model(ClassDef classDef, List<FieldDef> fieldDefList, String description) {
        return new ModelDef(classDef, fieldDefList, description);
    }

    private ApiResponseDef mapApiResponse(String key, ApiResponse value, Map<String, Schema<?>> components, String packageName) {
        final var status = "default".equals(key) ? 0 : Integer.parseInt(key);
        final var ref = getRef(value);
        final var fieldDefList = ref
                .map(s -> getFieldInternal(s, components))
                .map(fieldInternal -> getFields(fieldInternal, packageName, components))
                .orElse(Collections.emptyList());

        var builder = ApiResponseDefImpl.builder()
                .statusCode(status)
                .description(value.getDescription());

        if (!fieldDefList.isEmpty()) {
            var classDef = new ClassDef(packageName, Utils.normalizeClassName(ref.orElseThrow()));
            var description = getDescription(ref.orElseThrow(), components);
            var model = model(classDef, fieldDefList, description);
            var refModels = getRefModels(model, components, packageName);
            builder.model(model).refModels(refModels);
        }
        return builder.build();
    }




    private ParameterDef mapParameter(Parameter parameter) {
        return ParameterDefImpl.builder()
                .in(ParameterInType.valueOf(parameter.getIn().toUpperCase()))
                .name(parameter.getName())
                .required(parameter.getRequired())
                .description(parameter.getDescription())
                .schema(mapSchema(parameter.getSchema()))
                .build();
    }

    private SchemaDef mapSchema(Schema<?> schema) {
        return SchemaDefImpl.builder()
                .type(schema.getType())
                .format(schema.getFormat())
                .defaultValue(schema.getDefault())
                .minimum(schema.getMinimum())
                .maximum(schema.getMaximum())
                .maxLength(schema.getMaxLength())
                .minLength(schema.getMinLength())
                .pattern(schema.getPattern())
                .enumValues(schema.getEnum())
                .build();
    }

    private List<ModelDef> getRefModels(ModelDef model, Map<String, Schema<?>> components, String packageName) {
        var tmp = model.fieldDefList().stream()
                .filter(f -> f.ref() != null && f.classDef().packageName().equals(packageName))
                .map(f -> getFieldInternal(f.ref(), components))
                .map(s -> getModelDef(packageName, s, components))
                .toList();

        List<ModelDef> refModels = new ArrayList<>(tmp);
        tmp.stream().map(m -> getRefModels(m, components, packageName)).forEach(refModels::addAll);
        return refModels;
       
    }

    private ModelDef getModelDef(String packageName, FieldInternal fieldInternal,
                                        Map<String, Schema<?>> components) {
        var name = fieldInternal.name();
        var classDef = new ClassDef(packageName, Utils.normalizeClassName(name));
        var fields = getFields(fieldInternal, packageName, components);
        var description = fieldInternal.schema().map(Schema::getDescription).orElse("");
        return model(classDef, fields, description);
    }

    private List<FieldDef> getFields(FieldInternal field, String packageName, Map<String, Schema<?>> components) {
        if (Objects.isNull(field) || field.schema().isEmpty()) {
            return Collections.emptyList();
        }
        var schemaMain = field.schema().get();
        List<Schema<?>> schemaList = new ArrayList<>();
        collectSchemas(schemaMain, schemaList, components);
        var fieldList = schemaList.stream().map((Schema<?> schema) -> getFields(field, packageName, schema, components))
                .flatMap(List::stream).distinct().collect(Collectors.toList());
        if (fieldList.isEmpty()) {
            fieldList = getFields2(field, packageName, schemaMain, components, field.name());
        }
        return fieldList;
    }
    private void collectSchemas(Schema<?> schema, List<Schema<?>> schemaList, Map<String, Schema<?>> components) {
        if (schema instanceof ComposedSchema composedSchema) {
            Optional.ofNullable(composedSchema.getOneOf())
                    .orElse(Collections.emptyList()).forEach(s -> collectSchemas(s, schemaList, components));
            Optional.ofNullable(composedSchema.getAllOf())
                    .orElse(Collections.emptyList()).forEach(s -> collectSchemas(s, schemaList, components));
            Optional.ofNullable(composedSchema.getAnyOf())
                    .orElse(Collections.emptyList()).forEach(s -> collectSchemas(s, schemaList, components));
        } else {
            if (schema.get$ref() != null) {
                String ref = getRef(schema).orElseThrow();
                var obj = components.entrySet().stream()
                        .filter(entry -> entry.getKey().equals(ref))
                        .findFirst().orElseThrow();
                collectSchemas(obj.getValue(), schemaList, components);
            }else {
                schemaList.add(schema);
            }
        }
    }

    private List<FieldDef> getFields2(FieldInternal parent, String packageName, Schema<?> schema, Map<String, Schema<?>> components,
                                      String name) {
        List<?> enums = Collections.emptyList();
        if ("currency_code".equals(name)) {
            enums = loadEnums("currency_code_iso_4217.properties");
        }
        if ("country_code".equals(name)) {
            enums = loadEnums("country_code_iso_3166-1.properties");
        }
        return List.of(buildField(parent, List.of("value"), packageName, "value", schema, components, enums));
    }
    private List<?> loadEnums(String fileName) {
        Properties properties = Utils.loadProperties(fileName);
        return properties.keySet().stream().map(Object::toString).collect(Collectors.toList());
    }

    private FieldDef buildField(FieldInternal parent, List<String> requiredFields, String packageName, String name,
                                           Schema<?> entry, Map<String, Schema<?>> components, List<?> enums) {
        List<?> enum2 = enums.isEmpty() ? entry.getEnum() : enums;
        var name2 = Utils.normalizeInstanceName(name);
        var type2 = entry.getType();
        var ref2 = getRef(entry).orElse(null);
        ClassDef classDef2;
        ModelDef modelDef2 = null;
        if (ref2 == null && type2 == null) {
            classDef2 = ClassDef.OBJECT;

        }else if (ref2 == null && type2.equals("object")) {
//            ref2 = name;
//            putIfAbsent(name, entry, components);
            classDef2 = new ClassDef(packageName, Utils.normalizeClassName(name));
            var fields = getFieldsTmp(parent, packageName, entry, components);
            modelDef2 = model(classDef2, fields, entry.getDescription());

        }else if (ref2 == null && ( type2.equals("string") && entry.getEnum() != null )) {
            if (entry.getEnum().size() == 1){
                classDef2 = ClassDef.STRING;
                enum2 = null;
            }else {
                type2 = "object";
//            String className = Utils.normalizeClassName(parent.name() + "_" + name);
                String className = Utils.normalizeClassName(name);
                classDef2 = new ClassDef(packageName, Utils.normalizeClassName(className));
            }
        }else{
            classDef2 = mapClassDef(entry, packageName);
        }
        return FieldDefImpl.builder()
                .originalName(name)
                .name(name2)
                .description(Optional.ofNullable(entry.getDescription()).orElse(name2))
                .required(requiredFields.contains(name))
                .type(type2)
                .format(entry.getFormat())
                .defaultValue(entry.getDefault())
                .minimum(entry.getMinimum())
                .maximum(entry.getMaximum())
                .maxLength(entry.getMaxLength())
                .minLength(entry.getMinLength())
                .pattern(entry.getPattern())
                .enumValues(enum2)
                .array("array".equals(entry.getType()))
                .ref(ref2)
                .classDef(classDef2)
                .modelDef(modelDef2)
                .build();
    }

    private static void putIfAbsent(String name, Schema<?> entry, Map<String, Schema<?>> components) {
        if (components.containsKey(name) && components.get(name) != entry) {
            throw new RuntimeException("Duplicate name: " + name);
        }
        components.putIfAbsent(name, entry);
    }


    private ClassDef mapClassDef(Schema<?> value, String packageName) {
        if (value.getType() == null) {
            String ref = value.get$ref();
            if (ref == null && value instanceof ComposedSchema composedSchema) {
                ref = composedSchema.getOneOf().get(0).get$ref();
            }
            String name = ref.substring(ref.lastIndexOf('/') + 1);
            if ("date_time".equals(name)) {
                return ClassDef.INSTANT;
            }
            String simpleName = Utils.normalizeClassName(name);
            return new ClassDef(packageName, simpleName);
        }
        return switch (value.getType()) {
            case "string" ->{
                /*
                if (value.getEnum() != null){
                    yield  new ClassDef(packageName, "object");
                }

                 */
              yield  ClassDef.STRING;
            }
            case "integer" -> ClassDef.INTEGER;
            case "boolean" -> ClassDef.BOOLEAN;
            case "array" -> mapClassDef(((ArraySchema) value).getItems(), packageName);
            default -> new ClassDef(packageName, value.getType());
        };
    }
    private static class FieldInternal {
        private final String name;
        private final Schema<?> schema;

        public FieldInternal(String name, Schema<?> schema) {
            this.name = name;
            this.schema = schema;
        }

        public String name() {
            return name;
        }


        public Optional<Schema<?>> schema() {
            return Optional.ofNullable(schema);
        }
    }

    private FieldInternal getFieldInternal(String ref, Map<String, Schema<?>> components) {
        var obj = components.entrySet().stream()
                .filter(entry -> entry.getKey().equals(ref))
                .findFirst().map(Map.Entry::getValue).orElse(null);
        return new FieldInternal(ref, obj);
    }

    private Optional<String> getRef(RequestBody requestBody) {
        String ref = requestBody.get$ref();
        if (ref == null) {
            ref = Optional.ofNullable(requestBody.getContent()).map(content -> content.get("application/json"))
                    .map(mediaType -> mediaType.getSchema().get$ref())
                    .orElse(null);
        }
        return Optional.ofNullable(ref).map(r -> r.substring(r.lastIndexOf('/') + 1));
    }

    private Optional<String> getRef(ApiResponse apiResponse) {
        String ref = apiResponse.get$ref();
        if (ref == null) {
            ref = Optional.ofNullable(apiResponse.getContent()).map(content -> content.get("application/json"))
                    .map(mediaType -> mediaType.getSchema().get$ref())
                    .orElse(null);
        }
        return Optional.ofNullable(ref).map(r -> r.substring(r.lastIndexOf('/') + 1));
    }
    private Optional<String> getRef(Schema<?> obj) {
        String ref = obj.get$ref();
        if (Objects.isNull(ref)) {
            if (obj instanceof ArraySchema arraySchema) {
                ref = arraySchema.getItems().get$ref();
            }
        }
        return Optional.ofNullable(ref).map(r -> r.substring(r.lastIndexOf('/') + 1));
    }
    private Optional<String> getRef(ArraySchema arraySchema) {
        String ref = arraySchema.get$ref();
        if (Objects.isNull(ref)) {
                ref = arraySchema.getItems().get$ref();
        }
        return Optional.ofNullable(ref).map(r -> r.substring(r.lastIndexOf('/') + 1));
    }

    private String getDescription(String ref, Map<String, Schema<?>> components) {
        return components.entrySet().stream()
                .filter(entry -> entry.getKey().equals(ref))
                .findFirst().map(Map.Entry::getValue).map(Schema::getDescription).orElse("");



    }


}
