Overview
==========

"There can be only one"

Highlander is a Java micro-library for safely retrieving the only element from a source that may have zero, one or more elements. Supports:

- Arrays, iterables and streams
- Both Java 7 and 8
- Both Guava and Java 8 Optional

See www.michael-snell.com/2015/03/there-can-be-only-one.html for the motivation behind this.

Usage
=======

````java
List<String> list = asList("ONE", "TWO");
return only(list); // Will throw an exception as the list contains more than one entry
````

See the tests at https://github.com/snellm/highlander/blob/master/src/test/java/com/snell/michael/highlander/ for more usage examples.

Downloading
===========

Current version is 0.3 - alpha quality code (API is subject to change).

Maven:

````xml
<dependency>
  <groupId>com.snell.michael.highlander</groupId>
  <artifactId>highlander</artifactId>
  <version>0.3</version>
</dependency>
````

Direct download: http://repo1.maven.org/maven2/com/snell/michael/highlander/highlander/0.3

Fine print
==========
- Copyright 2015 Michael Snell
- Licensed under the MIT license - see [LICENSE](https://github.com/snellm/highlander/blob/master/LICENSE)
- Things may break. Performance may suffer. Giant creatures may arise from the oceans and destroy your civilization.
