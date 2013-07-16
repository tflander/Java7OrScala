package scalaKinds
import org.scalatest._
import scalaKinds.Animals._

class BasicPolymorphismTest extends FunSpec with ShouldMatchers {
  it("should make animal sounds") {
    val animals = Seq(Dog(), Cat(), Donkey())
    for (animal <- animals) println(animal.speak)
    /*
		 * Output:
		 * 
		 * woof 
		 * mew mew 
		 * hee-haw
		 */
  }

  it("should allow you to define new animals") {
    case class Zerg() extends Animal(speakMethod = () => "for the swarm")
    val animals = Seq(Dog(), Cat(), Donkey(), Zerg())
    for (animal <- animals) println(animal.speak)
    /*
		 * Output:
		 * 
		 * woof 
		 * mew mew 
		 * hee-haw
		 * for the swarm
		 */
  }
}
