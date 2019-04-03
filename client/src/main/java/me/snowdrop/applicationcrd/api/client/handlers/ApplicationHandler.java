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
package me.snowdrop.applicationcrd.api.client.handlers;


import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ResourceHandler;
import io.fabric8.kubernetes.client.Watch;

import io.fabric8.kubernetes.client.Watcher;
import me.snowdrop.applicationcrd.api.client.internal.ApplicationOperationsImpl;
import me.snowdrop.applicationcrd.api.model.Application;
import me.snowdrop.applicationcrd.api.model.ApplicationBuilder;
import okhttp3.OkHttpClient;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class ApplicationHandler implements ResourceHandler<Application, ApplicationBuilder> {
  @Override
  public String getKind() {
    return Application.class.getSimpleName();
  }

  @Override
  public String getApiVersion() {
        return "v1";
      }

  @Override
  public Application create(OkHttpClient client, Config config, String namespace, Application item) {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).create();
  }

  @Override
  public Application replace(OkHttpClient client, Config config, String namespace, Application item) {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).withName(item.getMetadata().getName()).replace(item);
  }

  @Override
  public Application reload(OkHttpClient client, Config config, String namespace, Application item) {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).withName(item.getMetadata().getName()).fromServer().get();
  }

  @Override
  public ApplicationBuilder edit(Application item) {
    return new ApplicationBuilder(item);
  }

  @Override
  public Boolean delete(OkHttpClient client, Config config, String namespace, Application item) {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).delete(item);
  }

  @Override
  public Watch watch(OkHttpClient client, Config config, String namespace, Application item, Watcher<Application> watcher) {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).withName(item.getMetadata().getName()).watch(watcher);
  }

  @Override
  public Watch watch(OkHttpClient client, Config config, String namespace, Application item, String resourceVersion, Watcher<Application> watcher) {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).withName(item.getMetadata().getName()).watch(resourceVersion, watcher);
  }

  @Override
  public Application waitUntilReady(OkHttpClient client, Config config, String namespace, Application item, long amount, TimeUnit timeUnit) throws InterruptedException {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).withName(item.getMetadata().getName()).waitUntilReady(amount, timeUnit);
  }

  @Override
  public Application waitUntilCondition(OkHttpClient client, Config config, String namespace, Application item, Predicate<Application> condition, long amount, TimeUnit timeUnit) throws InterruptedException {
    return new ApplicationOperationsImpl(client, config).withItem(item).inNamespace(namespace).withName(item.getMetadata().getName()).waitUntilCondition(condition, amount, timeUnit);
  }
}
