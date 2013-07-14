package scalaApiScriptOverJava

import javaSupport.api.OperationResult;
import javaSupport.api.OriginalApi;

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
  
  def callBlock(commands: OriginalApi => Unit) = {
    val api = new OriginalApi()
    val initResult = api.expensiveInit()
    commands(api)
    val apiCloseResult = api.close()
  }
  
}
