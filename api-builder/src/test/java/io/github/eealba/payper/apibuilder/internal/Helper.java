package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.FieldDef;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Helper {
    public static final List<FieldDef> SAMPLE_1 = List.of(
            fieldDef(ClassDef.STRING, "name", "name", "Name of customer", true),
            fieldDef(ClassDef.STRING, "last_name", "lastName", "lastname of customer", false),
            fieldDef(ClassDef.INTEGER, "id", "id", "customer ID", true),
            fieldDef(ClassDef.LOCAL_DATE, "birthday", "birthday", "Birthday", true),
            fieldDef(ClassDef.BIG_DECIMAL, "credit", "credit", "Credit amount", false),
            fieldDef(ClassDef.BOOLEAN, "active", "active", "Customer active", false),
            fieldDef(ClassDef.INSTANT, "created", "created", "created Timestamp",false),
            fieldDef(new ClassDef("io.github.eealba.paypal.api",  "Frequency"), "frequency", "frequency", "frequency",false),
            fieldDef(new ClassDef("io.github.eealba.paypal.api",  "Link"), "links", "links", "link array",false, true)
    );

    public static final List<FieldDef> SAMPLE_2 = List.of(
           fieldDef(new ClassDef("io.github.eealba.paypal.api",
                   "value"), "value", "value", "The Card brand",
                   List.of("VISA", "MASTERCARD", "AMEX", "DISCOVER"))
    );

    //Sample 3 with fields of Sample 1 and Sample 2
    public static final List<FieldDef> SAMPLE_3 = List.of(
            fieldDef(ClassDef.STRING, "name", "name", "Name of customer", true),
            fieldDef(ClassDef.STRING, "last_name", "lastName", "lastname of customer", false),
            fieldDef(ClassDef.INTEGER, "id", "id", "customer ID", true),
            fieldDef(ClassDef.LOCAL_DATE, "birthday", "birthday", "Birthday", true),
            fieldDef(ClassDef.BIG_DECIMAL, "credit", "credit", "Credit amount", false),
            fieldDef(ClassDef.BOOLEAN, "active", "active", "Customer active", false),
            fieldDef(ClassDef.INSTANT, "created", "created", "created Timestamp",false),
            fieldDef(new ClassDef("io.github.eealba.paypal.api",  "Frequency"), "frequency", "frequency", "frequency",false),
            fieldDef(new ClassDef("io.github.eealba.paypal.api",  "Link"), "links", "links", "link array",false, true),
            fieldDef(new ClassDef("io.github.eealba.paypal.api",
                    "value"), "value", "value", "The Card brand",
                    List.of("VISA", "MASTERCARD", "AMEX", "DISCOVER"))
    );


    private static FieldDef fieldDef(ClassDef classDef, String originalName, String name,
                                     String description, boolean required, boolean array) {
        return FieldDefImpl.builder()
                .classDef(classDef)
                .originalName(originalName)
                .name(name)
                .description(description)
                .required(required)
                .array(array)
                .build();
    }

    private static FieldDef fieldDef(ClassDef classDef, String originalName, String name,
                                     String description, boolean required) {
        return fieldDef(classDef, originalName, name, description, required, false);
    }
    private static FieldDef fieldDef(ClassDef classDef, String originalName, String name,
                                     String description, List<String> enumValues) {
        return FieldDefImpl.builder()
                .classDef(classDef)
                .originalName(originalName)
                .name(name)
                .description(description)
                .required(false)
                .array(false)
                .enumValues(enumValues)
                .build();
    }


    public static List<String> getDifferents(List<String> lines, List<String> expectedLines) {
        List<String> differents = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++){
            var expectedLine = expectedLines.get(i).trim();
            var line = lines.get(i).trim();
            if (!expectedLine.equals(line)){
                differents.add("line: " + (i + 1) + " >> " + line);
            }
        }
        if (lines.size() != expectedLines.size()){
            differents.add("line count expected: " + expectedLines.size() +" but got: " + lines.size());
        }
        return  differents;
    }


    public static void verify(String testName, String generatedCode, String resourceName) throws URISyntaxException,
            IOException {
        var lines = Arrays.asList(generatedCode.split("\\R"));
        StringBuilder buff = new StringBuilder();
        buff.append("verify test => ").append(testName).append('\n');
        buff.append(generatedCode).append('\n');
        buff.append("lines").append('\n');
        for(int i = 0; i < lines.size(); i++){
            buff.append("line:").append(String.format("%04d", i + 1)).append(' ').append(lines.get(i)).append('\n');
        }
        System.out.println(buff);

        verify(resourceName, lines);
    }
    private static void verify(String resourceName, List<String> lines) throws URISyntaxException, IOException {
        var path = Paths.get(Objects.requireNonNull(Helper.class.getClassLoader().getResource(resourceName)).toURI());
        var expectedLines = Files.readAllLines(path);
        var differents = getDifferents(lines, expectedLines);
        assertEquals(0, differents.size(), String.join("\n", differents));
    }
}
