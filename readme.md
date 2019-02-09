# Application CRD Client
---

## Features

- Java DSL that provides access to Application CRD resources
- Works on top of the fabric8 kubernetes-client

## Usage

Throughout the usage section code samples will be provided. The code samples are going to use the following naming conventions:

- `ac` The Application CRD client instance
- `kc` The Fabric8 Kubernetes client instance


### Configuring the client

There are two ways of creating an instance of the service catalog client.
- adapting from an existing instance of `KubernetesClient`
- manual configuration and instantiation

#### Adapting

When the Application CRD API is provided by the same API server as the Kubernetes API (both APIs are proxied together), you can easily adapt an existing 
`KubernetesClient` instance.

    KubernetesClient kc = new DefaultKubernetesClient();
    Application ac = kc.adapt(ApplicationClient.class);
    
This approach is pretty easy, but it can only work if the topology requirements explained above are satisfied. If not, the client needs to be configured manually.

#### Manual Configuration

To manually instantiate the client, you just need to create a configuration object, that specifies at least the url of the api server and a way to authenticate to it.
These may be specified:


- explicitly
    - via the `io.fabric8io.kubernetes.client.Config` DSL
- implicitly
    - using system properties
    - using env variables
    - using `~/.kube/confg`
    

##### Using the Config DSL

      import io.fabric8.kubernetes.client.Config;
      import io.fabric8.kubernetes.client.ConfigBuilder;
      
      ...
      
      Config config = new ConfigBuilder().withMasterUrl("https://url.to.api.server")
          .withOautToken("sometoken")
          .build();
          
      ApplicationClient app = new DefaultApplicationClient(config);
      

### Manipulating Resources

#### Application

In order to create an Application:

        Application app = ac.applications().createNew()
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
                .done();
                
