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
 * The type Credential.
 * This class is used to store the client id and secret.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see Object
 * @see String
 * @see char
 *
 * @author Edgar Alba
 */
public final class Credential {
    private final String clientId;
    private final char[] secret;
    /**
     * Instantiates a new Credential with a client id and a secret.
     *
     * @param clientId the client id
     * @param secret the secret
     */
    public Credential(String clientId, char[] secret) {
        this.clientId = clientId;
        this.secret = secret;
    }

    /**
     * Client id.
     *
     * @return the client id
     */
    public String clientId() {
        return clientId;
    }
    /**
     * Secret.
     *
     * @return the secret
     */
    public char[] secret() {
        return secret;
    }
    /**
     * Secret string.
     *
     * @return the secret as a string
     */
    public String secretString() {
        return new String(secret);
    }
}
