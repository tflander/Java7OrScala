package scalaInversion
import org.scalatest._
import javaSupport.api.ApiStatus._
import scalaApiScriptOverJava.ApiWrapper
import javaSupport.api.OriginalApi
import javaSupport.api.OperationResult

class ControlInversionTest extends FunSpec with ShouldMatchers {

  describe("direct api tests (no inversion)") {

    it("fails without initialization") {
      val api = new OriginalApi()
      intercept[IllegalStateException] {
        api.operationOne("test")
      }
    }

    it("works with initialization") {
      val api = new OriginalApi()
      api.expensiveInit()
      val result = api.operationOne("test")
      api.close()
      result.getStatus should be(OK)
      result.getMessage should be("operationOne performed on test")
    }

  }

  describe("inversion via API scripting with placeholder") {

    it("should allow you to pass script a single API call") {
      val result = ApiWrapper.call(_.operationOne("test")).head
      result.getStatus should be(OK)
      result.getMessage should be("operationOne performed on test")
    }

    it("should allow you to pass multiple operations to the API as a sequence of operations") {
      val operations = Seq[OriginalApi => OperationResult](
        _.operationOne("test"),
        _.operationTwo("testing", 123))

      val results = ApiWrapper.call(operations: _*)

      results map (_.getStatus()) should be(Seq(OK, OK))
      results map (_.getMessage()) should be(Seq(
        "operationOne performed on test",
        "operationTwo performed on testing 123"))
    }

    it("should allow you to pass multiple operations to the API as a variable list of operations") {

      val results = ApiWrapper.call(
        _.operationOne("test"),
        _.operationTwo("testing", 123))

      results map (_.getStatus()) should be(Seq(OK, OK))
      results map (_.getMessage()) should be(Seq(
        "operationOne performed on test",
        "operationTwo performed on testing 123"))
    }
  }

  describe("more better inversion using function parameter") {

    describe("demo of function types (used by ApiWrapper.callBlock())") {
      it("demos function types using Java-like boilerplate") {

        def foo(i: Int): String = {
          return "test" + i
        }

        def bar: Int => String = foo(_)

        def foobar: Int => String = intVal => {
          "whoo" + intVal
        }

        foo(1) should be("test1")
        bar(2) should be("test2")
        foobar(3) should be("whoo3")
      }

      it("demos function types with boilerplate removed  (used by ApiWrapper.callBlock())") {
        def foo(i: Int) = "test" + i
        def bar = foo(_)
        def foobar = (i: Int) => "whoo" + i

        def foobar2 = (i: Int) => {
          println("we can do anything in this anonymous block")
          "whoo" + i
        }

        foo(1) should be("test1")
        bar(2) should be("test2")
        foobar(3) should be("whoo3")
        foobar2(4) should be("whoo4")
      }
    }
    
    it("should execute a function using an initialized API") {

      def useApi(api: OriginalApi): Seq[OperationResult] = {
        val resultOne = api.operationOne("test")
        if (resultOne.getStatus() == OK) {
          val resultTwo = api.operationTwo("testing", 123)
          Seq(resultOne, resultTwo)
        } else {
          Seq(resultOne)
        }
      }

      val results = ApiWrapper.callBlock(api => useApi(api))

      results map (_.getStatus()) should be(Seq(OK, OK))
      results map (_.getMessage()) should be(Seq(
        "operationOne performed on test",
        "operationTwo performed on testing 123"))
    }
  }

  it("should execute an anonymous block of code against an initialized API") {

    val results = ApiWrapper.callBlock { api =>
      val resultOne = api.operationOne("test")
      if (resultOne.getStatus() == OK) {
        val resultTwo = api.operationTwo("testing", 123)
        Seq(resultOne, resultTwo)
      } else {
        Seq(resultOne)
      }
    }

    results map (_.getStatus()) should be(Seq(OK, OK))
    results map (_.getMessage()) should be(Seq(
      "operationOne performed on test",
      "operationTwo performed on testing 123"))
  }

}
