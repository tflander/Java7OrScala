package scalaInversion
import org.scalatest._
import api.ApiStatus._
import scalaApiScriptOverJava.ApiWrapper
import api.OriginalApi
import api.OperationResult

class ControlInversionTest extends FunSpec with ShouldMatchers {

  it("should execute a function using an initialized API") {

    def useApi()(api: OriginalApi): Seq[OperationResult] = {
      val resultOne = api.operationOne("test")
      if (resultOne.getStatus() == OK) {
        val resultTwo = api.operationTwo("testing", 123)
        Seq(resultOne, resultTwo)
      } else {
        Seq(resultOne)
      }
    }

    val results = ApiWrapper.callBlock(useApi())

    results map (_.getStatus()) should be(Seq(OK, OK))
    results map (_.getMessage()) should be(Seq(
      "operationOne performed on test",
      "operationTwo performed on testing 123"))
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
