package scalaSupport.api

object ApiStatus extends Enumeration {
  case class Val(name: String) extends super.Val(nextId, name)
  implicit def valueToApiStatus(x: Value) = x.asInstanceOf[Val]

  val OK = Val("OK")
  val Error = Val("Error")
}

case class OperationResult(status: ApiStatus.Val, message: String)

class OriginalApi {
  def expensiveInit(): OperationResult = 
    OperationResult(ApiStatus.OK, "init successful")

  def close(): OperationResult = 
    OperationResult(ApiStatus.OK, "api closed")
    
  def operationOne(param: String): OperationResult = 
    OperationResult(ApiStatus.OK, "operationOne performed on " + param)
    
  def operationTwo(param1: String, param2: Int): OperationResult = 
    OperationResult(ApiStatus.OK, "operationTwo performed on " + param1 + ' ' + param2)
}