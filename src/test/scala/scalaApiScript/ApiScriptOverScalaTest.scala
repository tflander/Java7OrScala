package scalaApiScript
import org.scalatest._
import scalaSupport.api._
import scalaSupport.api.ApiStatus._
import scalaApiScriptOverScala.ApiWrapper

class ApiScriptOverScalaTest extends FunSpec with ShouldMatchers {
  
  it("should allow you to pass script a single API call") {
	  val result = ApiWrapper.call(_.operationOne("test"))
	  result.status should be(OK)
	  result.message should be("operationOne performed on test")
  }
  
  it("should allow you to pass multiple operations to the API") {
      val operations = Seq[OriginalApi => OperationResult](
          _.operationOne("test"),
          _.operationTwo("testing", 123)
      )
      
	  val results = ApiWrapper.call(operations)
	  
	  results should be(Seq(
	      OperationResult(OK, "operationOne performed on test"),
	      OperationResult(OK, "operationTwo performed on testing 123")
	  ))
  }
}
