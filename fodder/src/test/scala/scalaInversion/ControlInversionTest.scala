package scalaInversion
import org.scalatest._
import javaSupport.api.ApiStatus._
import scalaApiScriptOverJava.ApiWrapper
import javaSupport.api.OriginalApi
import javaSupport.api.OperationResult

class ControlInversionTest extends FunSpec with ShouldMatchers {

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
