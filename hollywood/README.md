Hollywood:  Inversion of control
--------------------------------
TODO:  Review

Hollywood:  Inversion of control
--------------------------------

A common programming task is to take a generic API and wrap it for a specific purpose.  Also, many API's require some kind of initialization and/or tear-down that could be mishandled.  Programmers mitigate this risk through inversion of control.  

This example takes a very simple API and hides the initialization and tear-down.  The original api is written in Java.  There are api wrappers written in both scala and java.  This example illustrates two approaches to api wrapping.  The first approach is an api scripting approach where you can script multiple calls to a properly initialized API, then have tear-down happen from the scripting engine.  The second approach allows you to execute a block of logic against a properly initialized API, then have tear-down happen from the API wrapper.

For both approaches (scripting and block calls), we create a polymorphic Java model.  For scala, however, no object model is necessary.  You can script calls to the api using parameter place holders, and you can exucute an anonomous block of code be passing it to the API wrapper as a function.  This eliminates the need to create concrete classes in a polymorphic model just to pass code to the API wrapper.

- Open the four links from the Readme.
- Show that there are multiple Java files, but one Scala file.
- Review the Java NoInversion Test
- Review the Java Inversion Test using Eclipse.  Draw the object model.
- Demo Function types FunctionTypeDemoTest.scala
- Review the Scala Test and Code
- Review the Scala Inversion Test using Eclipse.
