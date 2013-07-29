package scalaApiScriptOverJava

import api.OperationResult;
import api.OriginalApi;

object ApiWrapper {
  
  def call(commands: (OriginalApi => OperationResult)*): Seq[OperationResult] = {
    val api = new OriginalApi()
    val initResult = api.expensiveInit()
    
    val results = 
      for {
        command <- commands
      }  yield command(api)
    
    val apiCloseResult = api.close()
    return results
  }
  
  def callBlock(commands: OriginalApi => Seq[OperationResult]): Seq[OperationResult]  = {
    val api = new OriginalApi()
    val initResult = api.expensiveInit()
    val results = commands(api)
    val apiCloseResult = api.close()
    return results
  }
  
}
