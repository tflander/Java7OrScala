name := "AfterMarketParts"

scalaVersion := "2.10.2"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.10-M4" % "test"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
 