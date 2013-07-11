Java7OrScala
============

Overview
--------
This project supports a presentation for exposing the pain of Java.  It contains code examples written in both Scala and Java.  Observe how much cleaner the Scala code is compared to Java.  Although 3rd party Java libraries exist to clean up awkward Java code, it seems more fair to compare core Java7 to core Scala.


The Examples
============

Musical Fruit:  Beans (aka Value Objects, Data Transfer Objects)
----------------------------------------------------------------

Packages:  
[javabean](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javabean)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javabean)
& [scalabean](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalabean)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalabean)


There is a lot of overhead for java beans, especially if you want to handle equals() and have a pretty toString().  Since scala case classes give you this behavior out-of-the box, it was only fair that the behavior is reproduced in the java bean.  Most of this example comes from the work of Venkat Subramaniam.  He says people often object that their IDE can generate most of the boilerplate code necessary for defining beans in Java.  Venkat refers to this as "IDE vomit", when you need an IDE to vomit out code that you need to make the compiler happy.

Only the Lonely:  Singletons
----------------------------

Packages:  [javaSingleton](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javaSingleton)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javaSingleton)
& [scalaSingleton](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalaSingleton)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalaSingleton)

Another example based on the work of Venkat Subramaniam.  He describes the Singleton pattern in Java as a pattern that takes one hour to learn and six months to debug.


To Be or Not To Be: Optional Things
-------------------------------------

Packages:  [javaOption](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javaOption) 
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javaOption)
& [ScalaOption](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalaOption)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalaOption)

Scala has an easy answer to a common problem in Java.  Bugs in Java often appear as a thrown NullPointerException.  This occurs when a developer does not expect a null referece to an object, but it happens.  Scala's easy answer is to never use null to represent optional data.  While there are patterns in Java to avoid nulls, it seems more fair for the example code to show the more common approach of adding null checks.

Family Ties: Polymorphic Behavior
-----------------------------------

Packages:  
[javaKinds](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javaKinds) 
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javaKinds)
& [scalaKinds](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalaKinds)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalaKinds)

This example demonstrates how useful it can be to package up related code into a single source code file.  Most of the code in the Java example is boilerplate code.  Boilerplate code is code that is necessary for the compiler, but not necessary to express the intent of the developer's design.  This example also demonstrates scala functions as first-class objects.

Penmanship: API Scripting
-------------------------

Packages:  
[javaApiScriptOverJava](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javaApiScriptOverJava)[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javaApiScript)
, 
[scalaApiScriptOverJava](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalaApiScriptOverJava)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalaApiScript)
, 
[javaApiScriptOverScala](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javaApiScriptOverScala)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javaApiScript)
, 
[scalaApiScriptOverScala](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalaApiScriptOverScala)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalaApiScript)

A common programming task is to take a generic API and wrap it for a specific purpose.  Also, many API's require some kind of initialization and/or tear-down that could be mishandled.  Programmers mitigate this risk through inversion of control.  

This example takes a very simple API and hides the initialization and tear-down.  The api is written in both Java and Scala.  The api wrappers handle the four permutations of wrapper language and api language; java over java, scala over java, java over scala, and scala over scala.  This example illustrates how partially applied functions can avoid having to create a polymorphic Java model.  This example also shows how easy it is to integrate Scala and Java within a JVM.  You can consider Scala as simply another Java jar file included in your project.

Groupies:  Aggregating and filtering data
-----------------------------------------

Packages:  [javaGroupAndFilter](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javaGroupAndFilter)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javaGroupAndFilter)
&
[scalaGroupAndFilter](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalaGroupAndFilter)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalaGroupAndFilter)


To Do:
------
  Neotoma: Collections
  


