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
package io.github.eealba.payper.core;

/**
 * The PayperBuilder class.
 * This class is used to create a Payper instance with custom configuration.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Payper
 * @see PayperConfig
 * @see PayperProvider
 * @see PayperProvider#provider()
 */
public class PayperBuilder {


    /**
     * Create a Payper instance with custom configuration.
     *
     * @param config the custom configuration
     * @return the Payper instance
     */
    public static Payper create(PayperConfig config) {
        return PayperProvider.provider().createPayper(config);
    }
}
