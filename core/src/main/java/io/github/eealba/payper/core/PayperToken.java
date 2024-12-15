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

import java.time.Instant;
/**
 * Interface representing a Payper token.
 * This interface provides methods to access the token's properties and check if it is valid.
 *
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface PayperToken {
    Instant created();
    String scope();
    String accessToken();
    String tokenType();
    String appId();
    int expiresIn();
    String nonce();
    default boolean isValid() {
        return Instant.now().isBefore(created().plusSeconds(expiresIn()));
    }

}
