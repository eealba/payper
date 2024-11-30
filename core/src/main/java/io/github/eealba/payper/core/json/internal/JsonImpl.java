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
package io.github.eealba.payper.core.json.internal;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.payper.core.json.Json;

/**
 * Author: Edgar Alba
 */
class JsonImpl implements Json {
    private final Jasoner jasoner;

    JsonImpl(Jasoner jasoner) {
        this.jasoner = jasoner;
    }

    @Override
    public String toJson(Object object) {
        return jasoner.toJson(object);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return jasoner.fromJson(json, clazz);
    }
}