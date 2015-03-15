Overview
==========

"There can be only one"

Highlander is a Java micro-library for safely retrieving the only element from a source that may have zero, one or more elements. Supports:

- Arrays, iterables and streams
- Both Java 7 and 8
- Both Guava and Java 8 Optional

See www.michael-snell.com/2015/03/there-can-be-only-one.html for the motivation behind this.

Dependencies
============

- Works with Java 7 or 8
- Can work with Guava if present, but not required

Usage
=====

- For Java 8: import static com.snell.michael.highlander.Highlander.only
- For Java 7: import static com.snell.michael.highlander.Java7Highlander.only
- For Guava Optional support with Java 7: import static com.snell.michael.highlander.GuavaOptionalHighlander.only

Basic usage of "only":

````java
List<String> list = asList("ONE", "TWO");
return only(list); // Will throw an exception as the list contains more than one entry

List<String> list = asList();
return only(list); // Will throw an exception as the list is empty

List<String> list = asList("ONE");
return only(list); // Will return the only entry
````

Basic usage of "optionalOnly":

````java
List<String> list = asList("ONE", "TWO");
return optionalOnly(list); // Will throw an exception as the list contains more than one entry

List<String> list = asList();
return optionalOnly(list); // Will return Optional.empty as the list is empty

List<String> list = asList("ONE");
return optionalOnly(list); // Will return an Optional containing the only entry
````

See the tests at https://github.com/snellm/highlander/blob/master/src/test/java/com/snell/michael/highlander/ for more usage examples.

Downloading
===========

Current version is 0.4 - beta quality code (API is subject to change).

Maven:

````xml
<dependency>
  <groupId>com.snell.michael.highlander</groupId>
  <artifactId>highlander</artifactId>
  <version>0.4</version>
</dependency>
````

[Other dependency systems](http://search.maven.org/#artifactdetails%7Ccom.snell.michael.highlander%7Chighlander%7C0.4%7Cjar)

[Direct download](http://repo1.maven.org/maven2/com/snell/michael/highlander/highlander)

Fine print
==========
- Copyright 2015 Michael Snell
- Licensed under the MIT license - see [LICENSE](https://github.com/snellm/highlander/blob/master/LICENSE)
- Things may break. Performance may suffer. Giant creatures may arise from the oceans and destroy your civilization.
