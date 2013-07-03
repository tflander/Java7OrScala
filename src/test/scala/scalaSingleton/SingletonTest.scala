package scalaSingleton
import org.scalatest._

class SingletonTest extends FunSpec with ShouldMatchers {

  it("should init lazy") {
    println("this comes before Singleton init");
    println(Singleton.x)
  }
  
}