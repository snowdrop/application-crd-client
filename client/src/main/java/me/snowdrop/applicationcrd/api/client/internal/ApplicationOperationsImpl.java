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
import io.fabric8.kubernetes.client.dsl.base.OperationContext;
import me.snowdrop.applicationcrd.api.client.util.ApiVersionUtil;
import me.snowdrop.applicationcrd.api.model.DoneableApplication;
import me.snowdrop.applicationcrd.api.model.Application;
import me.snowdrop.applicationcrd.api.model.ApplicationList;
import okhttp3.OkHttpClient;

import java.util.Map;
import java.util.TreeMap;

public class ApplicationOperationsImpl extends HasMetadataOperation<Application, ApplicationList, DoneableApplication, Resource<Application, DoneableApplication>>  {

    public ApplicationOperationsImpl(OkHttpClient client, Config config) {
        this(new OperationContext().withOkhttpClient(client).withConfig(config));
    }

    public ApplicationOperationsImpl(OperationContext ctx) {
        super(ctx.withPlural("applications").withApiGroupName("app").withApiGroupVersion("v1beta1"));
        this.type=Application.class;
        this.listType=ApplicationList.class;
        this.doneableType=DoneableApplication.class;
	}




	@Override
    public boolean isResourceNamespaced() {
        return true;
    }

}
