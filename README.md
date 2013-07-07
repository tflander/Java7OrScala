Java7OrScala
============

Overview
--------
This project supports a presentation for exposing the pain of Java.  It contains code examples written in both Scala and Java.  Observe how much cleaner the Scala code is compared to Java.  Although 3rd party Java libraries exist to clean up awkward Java code, it seems more fair to compare core Java7 to core Scala.


The Examples
============

Musical Fruit:  Beans (aka Value Objects, Data Transfer Objects)
----------------------------------------------------------------

Packages:  javabean & scalabean

There is a lot of overhead for java beans, especially if you want to handle equals() and have a pretty toString().  Since scala case classes give you this behavior out-of-the box, it was only fair that the behavior is reproduced in the java bean.  Most of this example comes from the work of Venkat Subramaniam.  He says people often object that their IDE can generate most of the boilerplate code necessary for defining beans in Java.  Venkat refers to this as "IDE vomit", when you need an IDE to vomit out code that you need to make the compiler happy.

Only the Lonely:  Singletons
----------------------------

Packages:  javaSingleton & scalaSingleton

Another example based on the work of Venkat Subramaniam.  He describes the Singleton pattern in Java as a pattern that takes one hour to learn and six months to debug.


To Be or Not To Be -- Optional Things
-------------------------------------

Package:  javaOption & ScalaOption

Scala has an easy answer to a common problem in Java.  Bugs in Java often appear as a thrown NullPointerException.  This occurs when a developer does not expect a null referece to an object, but it happens.  Scala's easy answer is to never use null to represent optional data.  While there are patterns in Java to avoid nulls, it seems more fair for the example code to show the more common approach of adding null checks.

Family Ties -- Polymorphic Behavior
-----------------------------------

Package:  javaKinds & scalaKinds

This example demonstrates how useful it can be to package up related code into a single source code file.  Most of the code in the Java example is boilerplate code.  Boilerplate code is code that is necessary for the compiler, but not necessary to express the intent of the developer's design.  This example also demonstrates scala functions as first-class objects.

To Do:
------
  Neotoma -- Collections

In Progress:
------------
  Penmanship -- API Scripting


