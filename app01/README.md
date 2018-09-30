## Check list

- [ ] Request Headerの取得
- [ ] RequestのContext化
- [X] HTTP Status Codeの変更
- [ ] Read Configuration
- [ ] Header操作 
- [ ] JPA系の操作

## Security

時間かかりそう。

-[helidon/security/providers at master · oracle/helidon](https://github.com/oracle/helidon/tree/master/security/providers "helidon/security/providers at master · oracle/helidon")
- [helidon/security/providers at master · oracle/helidon](https://github.com/oracle/helidon/tree/master/security/providers "helidon/security/providers at master · oracle/helidon")
- [Security Providers](https://helidon.io/docs/latest/#/security/02_providers "Security Providers")


## Database - JPA

### JP

- [Oracle Fusion Middleware Oracle JDeveloperによるアプリケーションの開発、12c (12.1.3)](https://docs.oracle.com/cd/E57014_01/jdev/user-guide/toc.htm "Oracle Fusion Middleware Oracle JDeveloperによるアプリケーションの開発、12c (12.1.3)")
- [Oracle Containers for J2EE Enterprise JavaBeans開発者ガイド -- 目次](https://docs.oracle.com/cd/E18355_01/web.1013/B31852-03/toc.htm "Oracle Containers for J2EE Enterprise JavaBeans開発者ガイド -- 目次")

### EN

- [Chapter 20 Introduction to the Java Persistence API (The Java EE 6 Tutorial)](https://docs.oracle.com/cd/E19798-01/821-1841/bnbpz/index.html "Chapter 20 Introduction to the Java Persistence API (The Java EE 6 Tutorial)")
- [Chapter 21 Running the Persistence Examples (The Java EE 6 Tutorial)](https://docs.oracle.com/cd/E19798-01/821-1841/gijst/index.html "Chapter 21 Running the Persistence Examples (The Java EE 6 Tutorial)")
- [Chapter 22 The Java Persistence Query Language (The Java EE 6 Tutorial)](https://docs.oracle.com/cd/E19798-01/821-1841/bnbtg/index.html "Chapter 22 The Java Persistence Query Language (The Java EE 6 Tutorial)")

## Web

- [Creating a RESTful Root Resource Class (The Java EE 6 Tutorial)](https://docs.oracle.com/cd/E19798-01/821-1841/6nmq2cp22/index.html "Creating a RESTful Root Resource Class (The Java EE 6 Tutorial)")
  
  - [The @Path Annotation and URI Path Templates (The Java EE 6 Tutorial)](https://docs.oracle.com/cd/E19798-01/821-1841/6nmq2cp26/index.html "The @Path Annotation and URI Path Templates (The Java EE 6 Tutorial)")

## Sample Code

- [hantsy/helidon-sample: Playground of helidon framework](https://github.com/hantsy/helidon-sample "hantsy/helidon-sample: Playground of helidon framework")
- https://github.com/hantsy/helidon-sample

```java
    @Inject
    public GreetResource(@ConfigProperty(name = "app.greeting")
                         final String greetingConfig) {

        if (this.greeting == null) {
            this.greeting = greetingConfig;
        }
    }
```
https://github.com/hensol/quickstart-mp/blob/6f6a2637bb9a954ee0bf7ba6830cfbeb1d3750ba/src/main/java/io/helidon/examples/quickstart/mp/GreetResource.java

## Link

- [The Central Repository Search Engine](https://search.maven.org/search?q=g:io.helidon.webserver "The Central Repository Search Engine")
- [Oracle Blogs 日本語のまとめ: [Java] Helidon Takes Flight](https://orablogs-jp.blogspot.com/2018/09/helidon-takes-flight.html "Oracle Blogs 日本語のまとめ: [Java] Helidon Takes Flight")