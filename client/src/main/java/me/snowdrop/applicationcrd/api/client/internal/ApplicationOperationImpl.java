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
package me.snowdrop.applicationcrd.api.client.internal;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.HasMetadataOperation;
import me.snowdrop.applicationcrd.api.model.DoneableApplication;
import me.snowdrop.applicationcrd.api.model.Application;
import me.snowdrop.applicationcrd.api.model.ApplicationList;
import okhttp3.OkHttpClient;

import java.util.Map;
import java.util.TreeMap;


public class ApplicationOperationImpl extends HasMetadataOperation<Application, ApplicationList, DoneableApplication, Resource<Application, DoneableApplication>>  {

    public ApplicationOperationImpl(OkHttpClient client, Config config) {
        this(client, config, "app", "v1beta1", null, null, true, null, null, false, -1, new TreeMap<String, String>(), new TreeMap<String, String>(), new TreeMap<String, String[]>(), new TreeMap<String, String[]>(), new TreeMap<String, String>());
    }

    public ApplicationOperationImpl(OkHttpClient client, Config config, String apiGroup, String apiVersion, String namespace, String name, Boolean cascading, Application item, String resourceVersion, Boolean reloadingFromServer, long gracePeriodSeconds, Map<String, String> labels, Map<String, String> labelsNot, Map<String, String[]> labelsIn, Map<String, String[]> labelsNotIn, Map<String, String> fields) {
        super(client, config, apiGroup, apiVersion, "applications", namespace, name, cascading, item, resourceVersion, reloadingFromServer, gracePeriodSeconds, labels, labelsNot, labelsIn, labelsNotIn, fields);
    }

    @Override
    public NonNamespaceOperation<Application, ApplicationList, DoneableApplication, Resource<Application, DoneableApplication>> inNamespace(String namespace) {
        return new ApplicationOperationImpl(client, config, apiGroup, apiVersion, namespace, name, isCascading(), getItem(), getResourceVersion(), isReloadingFromServer(), getGracePeriodSeconds(), getLabels(), getLabelsNot(), getLabelsIn(), getLabelsNotIn(), getFields());
    }

    @Override
    public Resource<Application, DoneableApplication> withName(String name) {
        return new ApplicationOperationImpl(client, config, apiGroup, apiVersion, namespace, name, isCascading(), getItem(), getResourceVersion(), isReloadingFromServer(), getGracePeriodSeconds(), getLabels(), getLabelsNot(), getLabelsIn(), getLabelsNotIn(), getFields());
    }

    @Override
    public boolean isResourceNamespaced() {
        return true;
    }
}
