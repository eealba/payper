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
package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.util.Providers;

public abstract class SubscriptionsProvider {
    private static final String DEFAULT = "io.github.eealba.payper.subscriptions.v1.internal.SubscriptionsProviderImpl";
    public SubscriptionsProvider() {
    }
    public static SubscriptionsProvider provider() {
        return Providers.getProvider(SubscriptionsProvider.class, DEFAULT);
    }
    public abstract Subscriptions createSubscriptions(PayperConfig config);
}
