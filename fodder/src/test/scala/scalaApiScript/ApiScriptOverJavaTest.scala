package scalaApiScript
import org.scalatest._

import scalaApiScriptOverJava.ApiWrapper;
import javaSupport.api.ApiStatus._
import javaSupport.api.OriginalApi
import javaSupport.api.OperationResult

class ApiScriptOverJavaTest extends FunSpec with ShouldMatchers {
  
  it("should allow you to pass script a single API call") {
	  val result = ApiWrapper.call(_.operationOne("test"))
	  result.head.getStatus should be(OK)
	  result.head.getMessage should be("operationOne performed on test")
  }
  
  it("should allow you to pass multiple operations to the API") {
      val operations = Seq[OriginalApi => OperationResult](
          _.operationOne("test"),
          _.operationTwo("testing", 123)
      )
      
	  val results = ApiWrapper.call(operations: _*)
	  
	  results map (_.getStatus()) should be (Seq(OK, OK))
	  results map (_.getMessage()) should be (Seq(
	      "operationOne performed on test", 
	      "operationTwo performed on testing 123"))
  }
}
