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
 * The interface Json.
 * This interface provides methods to convert objects to JSON and vice versa.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Json json = Json.newJson();
 * String jsonString = json.toJson(new MyObject());
 * MyObject obj = json.fromJson(jsonString, MyObject.class);
 * }</pre>
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface Json {

    /**
     * Creates a new Json instance using the default JsonProvider.
     *
     * @return a new Json instance
     */
    static Json newJson() {
        return JsonProvider.provider().createJson();
    }

    /**
     * Converts an object to its JSON representation.
     *
     * @param object the object to be converted to JSON
     * @return the JSON representation of the object
     */
    String toJson(Object object);

    /**
     * Converts a JSON string to an object of the specified class.
     *
     * @param <T> the type of the object
     * @param json the JSON string to be converted
     * @param clazz the class of the object
     * @return the object represented by the JSON string
     */
    <T> T fromJson(String json, Class<T> clazz);
}