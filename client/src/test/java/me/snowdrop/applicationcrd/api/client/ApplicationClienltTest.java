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

import me.snowdrop.applicationcrd.api.model.Application;
import me.snowdrop.applicationcrd.api.model.ApplicationBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationClienltTest {

    @Test
    public void applications() {
        Application app = new ApplicationBuilder()
                .withNewMetadata()
                    .withName("wordpress-01")
                    .addToLabels("app/kubernetes.io/name", "wordpress-01")
                    .addToLabels("app/kubernetes.io/version", "3")
                .endMetadata()
                .withNewSpec()
                .withNewSelector()
                    .addToMatchLabels("app.kubernetes.io/name", "wordpress-01")
                .endSelector()
                .addNewComponentKind("core" ,"Service")
                .addNewComponentKind("apps" ,"Deployment")
                .addNewComponentKind("apps" ,"StatefulSet")
                .withAssemblyPhase("Pending")
                .withNewDescriptor()
                .withVersion("4.9.4")
                .withDescription("WordPress is open source software you can use to create a beautiful website, blog, or app.")
                .addNewIcon()
                    .withSrc("https://example.com/wordpress.png")
                    .withType("image/png")
                .endIcon()
                .addNewMaintainer()
                    .withName("Kenneth Owens")
                    .withEmail("kow3ns@github.com")
                .endMaintainer()
                .addNewLink("About", "https://wordpress.org/")
                .endDescriptor()
                .endSpec()
                .build();

        Assert.assertNotNull(app);
    }
}
