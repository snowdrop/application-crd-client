/*
 * Copyright (C) 2018 Red Hat inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.snowdrop.applicationcrd.api.client;

import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.WithRequestCallable;
import io.fabric8.kubernetes.client.dsl.FunctionCallable;
import io.fabric8.kubernetes.client.dsl.Resource;
import me.snowdrop.applicationcrd.api.model.Application;
import io.fabric8.kubernetes.client.BaseClient;
import io.fabric8.kubernetes.client.Config;
import me.snowdrop.applicationcrd.api.client.internal.ApplicationOperationsImpl;
import me.snowdrop.applicationcrd.api.model.ApplicationList;
import me.snowdrop.applicationcrd.api.model.DoneableApplication;
import okhttp3.OkHttpClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;

public class DefaultApplicationClient extends BaseClient implements NamespacedApplicationClient {

    public DefaultApplicationClient() {
        super();
    }

    public DefaultApplicationClient(Config configuration) {
        super(configuration);
    }

    public DefaultApplicationClient(OkHttpClient httpClient, Config configuration) {
        super(httpClient, configuration);
    }

   public NonNamespaceOperation<Application, ApplicationList, DoneableApplication, Resource<Application, DoneableApplication>> applications(){
        return new ApplicationOperationsImpl(this.getHttpClient(), this.getConfiguration());
    }

    @Override
    public NamespacedApplicationClient inAnyNamespace() {
        return inNamespace(null);
    }

    @Override
    public NamespacedApplicationClient inNamespace(String namespace) {
        Config updated = new ConfigBuilder(getConfiguration())
                .withNamespace(namespace)
                .build();

        return new DefaultApplicationClient(getHttpClient(), updated);
    }
  @Override
  public FunctionCallable<NamespacedApplicationClient> withRequestConfig(RequestConfig requestConfig) {
    return new WithRequestCallable<NamespacedApplicationClient>(this, requestConfig);
  }
}
