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
package io.github.eealba.payper.core.json;

/**
 * The abstract Json.
 * This abstract class provides methods to convert objects to JSON and vice versa.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Create a new Json instance
 * Json json = Json.create();
 *
 * // Convert an object to JSON
 * MyObject myObject = new MyObject();
 * String jsonString = json.toJson(myObject);
 *
 * // Convert a JSON string to an object
 * MyObject obj = json.fromJson(jsonString, MyObject.class);
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public abstract class Json {

    /**
     * Creates a new Json instance using the default JsonProvider.
     *
     * @return a new Json instance
     */
    public static Json create() {
        return JsonProvider.provider().createJson();
    }

    /**
     * Converts an object to its JSON representation.
     *
     * @param object the object to be converted to JSON
     * @return the JSON representation of the object
     */
    public abstract String toJson(Object object);

    /**
     * Converts a JSON string to an object of the specified class.
     *
     * @param <T> the type of the object
     * @param json the JSON string to be converted
     * @param clazz the class of the object
     * @return the object represented by the JSON string
     */
    public abstract <T> T fromJson(String json, Class<T> clazz);
}