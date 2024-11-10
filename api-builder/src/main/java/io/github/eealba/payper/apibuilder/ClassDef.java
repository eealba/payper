package io.github.eealba.payper.apibuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ClassDef {
    public static final ClassDef OBJECT = new ClassDef("java.lang", "Object");
    public static final ClassDef STRING = new ClassDef("java.lang", "String");
    public static final ClassDef INTEGER = new ClassDef("java.lang", "Integer");
    public static final ClassDef BOOLEAN = new ClassDef("java.lang", "Boolean");
    public static final ClassDef INSTANT = new ClassDef("java.time", "Instant");
    public static final ClassDef LOCAL_DATE = new ClassDef("java.time", "LocalDate");
    public static final ClassDef BIG_DECIMAL = new ClassDef("java.math", "BigDecimal");
    private final String packageName;
    private final String simpleName;

    private final List<String> interfaces;
    private final List<String> builderInterfaces;

    public ClassDef(String packageName, String simpleName) {
        this(packageName, simpleName, Collections.emptyList());
    }
    public ClassDef(String packageName, String simpleName, List<String> interfaces) {
        this(packageName, simpleName, interfaces, Collections.emptyList());

    }

    public ClassDef(String packageName, String simpleName, List<String> interfaces,
                    List<String> builderInterfaces) {
        this.packageName = packageName;
        this.simpleName = simpleName;
        this.interfaces = List.copyOf(Objects.requireNonNull(interfaces));
        this.builderInterfaces = List.copyOf(Objects.requireNonNull(builderInterfaces));
    }

    public String packageName() {
        return packageName;
    }

    public String simpleName() {
        return simpleName;
    }

    public List<String> interfaces() {
        return interfaces;
    }
    public List<String> builderInterfaces() {
        return builderInterfaces;
    }

    public String name(){
        return packageName + "." + simpleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDef classDef = (ClassDef) o;
        return Objects.equals(packageName, classDef.packageName) && Objects.equals(simpleName, classDef.simpleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageName, simpleName);
    }
}
