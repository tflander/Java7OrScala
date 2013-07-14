package scalaSupport.api

object ApiStatus extends Enumeration {
  case class Val(name: String) extends super.Val(nextId, name)
  implicit def valueToApiStatus(x: Value) = x.asInstanceOf[Val]

  val OK = Val("OK")
  val Error = Val("Error")
}

case class OperationResult(status: ApiStatus.Val, message: String)

class OriginalApi {

  private var initialized = false;

  def expensiveInit(): OperationResult = {
    verifyNotInitialized
    initialized = true
    OperationResult(ApiStatus.OK, "init successful")
  }

  def close(): OperationResult = {
    verifyInitialized
    initialized = false
    OperationResult(ApiStatus.OK, "api closed")
  }

  def operationOne(param: String): OperationResult = {
    verifyInitialized
    initialized = true
    OperationResult(ApiStatus.OK, "operationOne performed on " + param)
  }

  def operationTwo(param1: String, param2: Int): OperationResult = {
    verifyInitialized
    initialized = true
    OperationResult(ApiStatus.OK, "operationTwo performed on " + param1 + ' ' + param2)
  }

  def verifyInitialized = {
    if (!initialized) {
      throw new IllegalStateException("api is not initialized");
    }
  }

  def verifyNotInitialized = {
    if (initialized) {
      throw new IllegalStateException("api is already initialized");
    }
  }
}