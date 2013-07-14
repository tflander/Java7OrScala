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

    it("should execute an anonymous block of code") {
      ApiWrapper.callBlock { api =>
        val resultOne = api.operationOne("test")
        val resultTwoOrNot = if (resultOne.getStatus() == OK) {
          Some(api.operationTwo("testing", 123))
        } else {
          None
        }
        resultOne.getStatus() should be(OK)
        resultOne.getMessage() should be("operationOne performed on test")
        val resultTwo = resultTwoOrNot.get
        resultTwo.getStatus() should be(OK)
        resultTwo.getMessage() should be("operationTwo performed on testing 123")
      }
    }
  }

}