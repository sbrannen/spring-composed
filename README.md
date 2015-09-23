# Welcome to _Spring Composed_!

This repository is a collection of _composed annotations_ for use with the
[Spring Framework][].

The current goal of this project is to brainstorm within the community and
gather ideas on what kinds of _composed annotations_ would be useful for
Spring to officially support. So... if you have an idea, share it!

As Spring Framework 4.3 approaches, the Spring team will review proposals
from the community and decide on a reduced set of _composed annotations_
to be officially supported by the Spring Framework or other projects in the
Spring portfolio (e.g, Spring Boot, Spring Security, Spring Integration,
etc.).

# Contributing

Feel free to provide us feedback and voice your opinion on which annotations
should make it into Spring in [SPR-13442][]. [Pull requests][] are also
welcome and encouraged.

## Contributor License Agreement

Although the `spring-composed` project is currently a test bed for experimentation,
there is a good chance that annotations from this project will eventually be
incorporated into official Spring projects. In order to have your pull requests
accepted, we therefore ask that you sign the [Contributor License Agreement][] and
document that you have done so in the commit message or comments of your pull request.

## License
The Spring Composed project is released under version 2.0 of the [Apache License][].

# See Also

- [Spring Annotation Programming Model][]: official wiki page from the Spring team
- [SPR-13442][]: JIRA issued related to this project
- [Spring Events][]: sample application that uses various annotations from _Spring Composed_

# Downloading Artifacts

The Spring Composed JAR is available from the Spring snapshot repository:

| Repository URL | Group ID | Artifact ID | Version |
| -------------- | -------- | ----------- | ------- |
| `https://repo.spring.io/snapshot/` | `org.springframework.composed` | `spring-composed` | `1.0.0.BUILD-SNAPSHOT` |

# Building from Source

Spring Composed uses a [Gradle][]-based build system. In the instructions
below, `./gradlew` is invoked from the root of the source tree and serves as
a cross-platform, self-contained bootstrap mechanism for the build.

You can check the current build status in the [Spring Composed build][].

## Prerequisites

- [Git][]
- [JDK 8][JDK8] update 60 or later
- [Spring Framework][] 4.2.1 or later

Most annotations in the `spring-composed` project rely on `@AliasFor` which
was introduced in Spring Framework 4.2 and therefore require at least Spring
Framework 4.2.1 to work properly. 

Be sure that your `JAVA_HOME` environment variable points to the `jdk1.8.0` folder
extracted from the JDK download.

## Compile and Test

Build all JARs, distribution ZIP files, and docs:

`./gradlew build`

## Install `spring-composed` in local Maven repository

`./gradlew install`


[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
[Gradle]: http://gradle.org
[Git]: http://help.github.com/set-up-git-redirect
[JDK8]: http://www.oracle.com/technetwork/java/javase/downloads
[Spring Framework]: http://projects.spring.io/spring-framework/
[Spring Annotation Programming Model]: https://github.com/spring-projects/spring-framework/wiki/Spring-Annotation-Programming-Model
[Spring Composed build]: https://build.spring.io/browse/SC-PUB
[SPR-13442]: https://jira.spring.io/browse/SPR-13442
[Spring Events]: https://github.com/sbrannen/spring-events
[Pull requests]: http://help.github.com/send-pull-requests
[Contributor License Agreement]: https://github.com/spring-projects/spring-framework/blob/master/CONTRIBUTING.md#sign-the-contributor-license-agreement
