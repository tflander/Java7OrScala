package scalaSingleton
import org.scalatest._

class SingletonTest extends FunSpec with ShouldMatchers {

  it("should init lazy") {
    println("this comes before Singleton init");
    println(Singleton.x)
    println(Singleton.y)
  }

  /*
   * Output:
   * 
   * this comes before Singleton init
   * Singleton Init Started
   * performing expensive operation one
   * Singleton Init Done
   * expensive operation one
   * performing expensive operation two
   * expensive operation two
   */

}