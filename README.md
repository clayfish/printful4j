# printful4j

[![Release](https://jitpack.io/v/clayfish/printful4j.svg)](https://jitpack.io/#clayfish/printful4j)

This is an unofficial client of Printful API for Android. This can also be used in any JVM based
project to connect to Printful API. It uses [**semantic versioning**](http://semver.org/) so that
developers can trust it.

## How to download?

### Android
To use it, add jitpack repository in your `build.gradle` file.

```
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}
```

And add following in dependencies,

```
dependencies {
    compile 'in.clayfish:printful4j:0.1.0'
}
```

### Java

* **Using gradle**, `compile 'in.clayfish:printful4j:0.1.0'`
* **Using Maven**, Add following in `<repositories>`,

``` xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
 and then use following,

``` xml
<dependency>
    <groupId>in.clayfish</groupId>
    <artifactId>printful4j</artifactId>
    <version>0.1.0</version>
</dependency>
```

* **Using sbt**, Add following at the end of resolvers in your `build.sbt`,
```
resolvers += "jitpack" at "https://jitpack.io"
```
And add following in the dependencies,
```
libraryDependencies += "in.clayfish" % "printful4j" % "0.1.0"
```

* **Using leningen**, Add following in your project.clj at the end of repositories,
```
:repositories [["jitpack" "https://jitpack.io"]]
```
And add following,
```
:dependencies [[in.clayfish/printful4j "0.1.0"]]
```

For more information regarding download or using different build systems, please visit
[JitPack](https://jitpack.io/#clayfish/printful4j/SNAPSHOT).

## How to use?
There is one interface `Client` to use all the [printful API](https://www.theprintful.com/docs).
First create a variable to keep your client,

``` java
Client printfulClient = new CompositeClient("YOUR API KEY");
```

`CompositeClient` uses multiple specific clients to serve full API, if you want to use a specific
API, you can use a specific API client, for example,
``` java
Client printfulOrdersClient = new OrdersApiClient("YOUR_API_KEY");
```

> Do remember that `OrdersApiClient` will have all the methods available, but methods which are not
related to [Orders API](https://www.theprintful.com/docs/orders) will throw a `NotImplementedException`.

This client uses HTTPS JSON API provided by Printful as documented
[here](https://www.theprintful.com/docs). When using methods, please refer to that documentation.
Javadocs are available with builds as well as
[here](https://jitpack.io/com/github/clayfish/printful4j/f0095e50da/javadoc/).

> Currently many methods throw `NotImplementedException` because this client is in development
stage. Please use caution while using this library.
