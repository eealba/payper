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
package io.github.eealba.payper.core.client;


import java.util.Map;

public abstract class RequestSpecsFactory {
    private static final RequestSpecsFactory INSTANCE = PayperProvider.provider().createRequestSpecsFactory();

    public abstract <T1> T1 post(Spec<T1> spec);
    public abstract <T1> T1 get(Spec<T1> spec);
    public abstract <T1> T1 get(Spec<T1> spec, Map<String, String> alias);
    public abstract <T1> T1 put(Spec<T1> spec);
    public abstract <T1> T1 put(Spec<T1> spec, Map<String, String> alias);
    public abstract <T1> T1 delete(Spec<T1> spec);
    public abstract <T1> T1 delete(Spec<T1> spec, Map<String, String> alias);
    public abstract <T1> T1 patch(Spec<T1> spec);
    public abstract <T1> T1 patch(Spec<T1> spec, Map<String, String> alias);

    public static RequestSpecsFactory getInstance() {
        return INSTANCE;
    }
}
