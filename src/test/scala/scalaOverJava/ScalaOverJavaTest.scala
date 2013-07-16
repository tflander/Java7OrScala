package scalaOverJava
import org.scalatest._
import javaSupport.api.OriginalApi
import javaSupport.api.ApiStatus._

class ScalaOverJavaTest extends FunSpec with ShouldMatchers {
  
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