
Musical Fruit:  Beans (aka Value Objects, Data Transfer Objects)
----------------------------------------------------------------

Packages:  
[javabean](https://github.com/tflander/Java7OrScala/tree/master/src/main/java/javabean)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/java/javabean)
& [scalabean](https://github.com/tflander/Java7OrScala/tree/master/src/main/scala/scalabean)
[(tests)](https://github.com/tflander/Java7OrScala/tree/master/src/test/scala/scalabean)


There is a lot of overhead for java beans, especially if you want to handle equals() and have a pretty toString().  Since scala case classes give you this behavior out-of-the box, it was only fair that the behavior is reproduced in the java bean.  Most of this example comes from the work of Venkat Subramaniam.  He says people often object that their IDE can generate most of the boilerplate code necessary for defining beans in Java.  Venkat refers to this as "IDE vomit", when you need an IDE to vomit out code that you need to make the compiler happy.

