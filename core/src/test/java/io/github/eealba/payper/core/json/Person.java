package io.github.eealba.payper.core.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private int age;
    private boolean developer;
    private List<String> hobbies;
    private Map<String, String> socialMedia;
}
