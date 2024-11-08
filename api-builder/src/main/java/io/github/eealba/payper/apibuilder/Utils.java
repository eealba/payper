package io.github.eealba.payper.apibuilder;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.Properties;
import java.util.StringTokenizer;

public class Utils {
    static void write(Path path, String str) {
      try {
            Files.writeString(path, str, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void clean(Path path) {
        try {
            Files.walk(path).forEach(path1 -> {
                try {
                    if (!path.equals(path1)) {
                        Files.delete(path1);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String normalizeClassName(String name) {
        var buff = new StringBuilder();
        var len = name.length();
        var upper = false;
        for(int i = 0 ; i < len; i++){
            char c = name.charAt(i);
            if (c == '.' || c == '-' || c == '_' || c == ' '){
                upper = true;
                continue;
            }
            if (i == 0) {
                upper = true;
                if (c >= '0' && c <= '9') {
                    buff.append('N');
                }
            }
            if (upper && c >= 'a' && c <= 'z') {
               c = (char) (c - 32);
            }
            buff.append(c);
            upper = false;
        }
        return buff.toString();
    }

    public static String normalizeInstanceName(String name) {
        var buff = new StringBuilder();
        var len = name.length();
        var upper = false;
        for(int i = 0 ; i < len; i++){
            char c = name.charAt(i);
            if (c == '.' || c == '-' || c == '_'){
                upper = true;
                continue;
            }
            if (i == 0) {
                if (c >= '0' && c <= '9') {
                    buff.append('n');
                }
                if (c >= 'A' && c <= 'Z') {
                    c = (char) (c + 32);
                }
            }
            if (upper && c >= 'a' && c <= 'z') {
                c = (char) (c - 32);
            }
            buff.append(c);
            upper = false;
        }
        return buff.toString();
    }

    public static String addLinebreaks(String input, int maxLineLength) {
        return addLinebreaks(input, maxLineLength, "\n");

    }
    public static String addLinebreaks(String input, int maxLineLength, String sep) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                output.append(sep);
                lineLen = 0;
            }
            output.append(word).append(' ');

            lineLen += word.length() + 1;
        }
        return output.toString().trim();
    }
    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Sorry, unable to find " + fileName);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading properties file", ex);
        }
        return properties;
    }

}
