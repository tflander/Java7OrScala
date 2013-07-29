package scalaApiScriptOverJava

import api.OperationResult;
import api.OriginalApi;

object ApiWrapper {
  
  def callBlock(commands: OriginalApi => Seq[OperationResult]): Seq[OperationResult]  = {
    val api = new OriginalApi()
    val initResult = api.expensiveInit()
    val results = commands(api)
    val apiCloseResult = api.close()
    return results
  }
  
}
