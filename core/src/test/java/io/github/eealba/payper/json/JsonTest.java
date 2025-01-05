package io.github.eealba.payper.json;

import io.github.eealba.payper.core.json.Json;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonTest {

    @Test
    void toJson() {
        Person person = new Person("Luis", 25, true, List.of("coding", "reading"),
                Map.of("twitter", "luis"));
        String json = Json.create().toJson(person);
        System.out.println(json);

    }

    @Test
    void fromJson() {
        String json = """
                {
                    "name": "Luis",
                    "age": 25,
                    "developer": true,
                    "hobbies": ["coding", "reading"],
                    "socialMedia": {
                        "twitter": "luis"
                    }
                }
                """;
        Person person = Json.create().fromJson(json, Person.class);
        assertNotNull(person);
        assertEquals("Luis", person.getName());
        assertEquals(25, person.getAge());
        assertTrue(person.isDeveloper());
        assertEquals(List.of("coding", "reading"), person.getHobbies());
        assertEquals(Map.of("twitter", "luis"), person.getSocialMedia());
    }

}