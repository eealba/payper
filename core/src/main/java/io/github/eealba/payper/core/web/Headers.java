/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.core.web;

import java.util.Map;
import java.util.Optional;

/**
 * The class Headers.
 * This class is used to store and manage HTTP headers.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Map<String, String> headerMap = Map.of("Content-Type", "application/json", "Accept", "application/json");
 * Headers headers = new Headers(headerMap);
 * Optional<String> contentType = headers.getValue("Content-Type");
 * contentType.ifPresent(System.out::println);
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 *
 * @see Map
 * @see Optional
 */
public final class Headers {
    private final Map<String, String> values;

    /**
     * Instantiates a new Headers object with the given map of header values.
     *
     * @param values the map of header values
     */
    public Headers(Map<String, String> values) {
        this.values = Map.copyOf(values);
    }

    /**
     * Gets the value of a header by its name.
     *
     * @param name the name of the header
     * @return an Optional containing the header value if present, otherwise an empty Optional
     */
    public Optional<String> getValue(String name) {
        return Optional.ofNullable(values.get(name));
    }

    /**
     * Converts the headers to an array of strings.
     * Each header name and value are placed in consecutive positions in the array.
     *
     * @return an array of strings representing the headers
     */
    public String[] toArray() {
        var array = new String[values.size() * 2];
        int i = 0;
        for (var entry : values.entrySet()) {
            array[i++] = entry.getKey();
            array[i++] = entry.getValue();
        }
        return array;
    }
}