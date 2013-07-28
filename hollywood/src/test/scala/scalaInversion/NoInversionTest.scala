package scalaInversion
import org.scalatest._
import api.OriginalApi
import api.ApiStatus._

class NoInversionTest extends FunSpec with ShouldMatchers {

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