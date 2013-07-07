package scalaApiScriptOverJava

import javaSupport.api.OperationResult;
import javaSupport.api.OriginalApi;

object ApiWrapper {
  
  def call(command: OriginalApi => OperationResult): OperationResult = {
      return call(Seq(command)).head
  }

  def call(commands: Seq[OriginalApi => OperationResult]): Seq[OperationResult] = {
    val api = new OriginalApi()
    val initResult = api.expensiveInit()
    
    val results = 
      for {
        command <- commands
      }  yield command(api)
    
    val apiCloseResult = api.close()
    return results
  }
}
