package io.github.eealba.payper.apibuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void given_string_starting_with_number_return_valid_classname(){
        var res = Utils.normalizeClassName("404");

        assertEquals("N404", res);
    }
    @Test
    void given_string_snakecase_return_camelcase_classname(){
        var res = Utils.normalizeClassName("hola_mundo");

        assertEquals("HolaMundo", res);
    }

    @Test
    void given_string_kebabcase_return_camelcase_classname(){
        var res = Utils.normalizeClassName("hola-mundo");

        assertEquals("HolaMundo", res);
    }
    @Test
    void given_string_withdot_return_camelcase_classname(){
        var res = Utils.normalizeClassName("hola.mundo");

        assertEquals("HolaMundo", res);
    }

    @Test
    void given_string_with_spaces_return_camelcase_classname(){
        var res = Utils.normalizeClassName("hola mundo");

        assertEquals("HolaMundo", res);
    }

    @Test
    void given_string_snakecase_return_camelcase_instanceName(){
        var res = Utils.normalizeInstanceName("hola_mundo");

        assertEquals("holaMundo", res);
    }

    @Test
    void given_string_kebabcase_return_camelcase_instanceName(){
        var res = Utils.normalizeInstanceName("hola-mundo");

        assertEquals("holaMundo", res);
    }
    @Test
    void given_string_withdot_return_camelcase_instanceName(){
        var res = Utils.normalizeInstanceName("hola.mundo");

        assertEquals("holaMundo", res);
    }


}