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

public final class Headers {
    private final Map<String,String> headers;
    public Headers(Map<String,String> headers) {
        this.headers = Map.copyOf(headers);
    }
    public Optional<String> getValue(String name) {
        return Optional.ofNullable(headers.get(name));
    }
    public String[] toArray() {
        var array = new String[headers.size() * 2];
        int i = 0;
        for (var entry : headers.entrySet()) {
            array[i++] = entry.getKey();
            array[i++] = entry.getValue();
        }
        return array;
    }
}
