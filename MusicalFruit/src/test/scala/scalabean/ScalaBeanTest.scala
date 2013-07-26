package scalabean

import org.scalatest._
import scalabean._

class ScalaBeanTest extends FunSpec with ShouldMatchers {
	it("should require a model") {
	  val car = Car("HHR")
	  car.model should be ("HHR")
	}
	
	it ("should optionally allow mileage") {
	  val car = Car("HHR", 40000)
	  car.miles should be (40000)
	}
	
	it ("should require mileage is zero or positive") {
	  intercept [IllegalArgumentException] {
	    val car = Car("HHR", -100)
	  }
	}
	
	it("should require the model is not an empty string") {
	  intercept [IllegalArgumentException] {
	    val car = Car("")
	  }
	}
	
	it("should allow you to adjust the mileage, but not the model") {
	  val car = Car("HHR", 40000)
	  car.miles should be (40000)
	  car.miles = 50000
	  // car.model = "Testerosa"  <== error:  reassignment to val
	  car.miles should be (50000)
	}
	
	it("supports deep copy") {
	  val car1 = Car("HHR", 40000)
	  val car2 = car1.copy
	  car2.miles = 50000
	  car1.miles should be (40000)
	  car2.miles should be (50000)
	}
	
	it("supports deep compare") {
	  val car1 = Car("HHR", 40000)
	  val car2 = Car("HHR", 40000)
	  val car3 = Car("HHR", 50000)
	  car1 should be (car2)
	  car1 should not be (car3)
  	  car1 == car2 should be(true)
  	  car1 == car3 should be(false)
	  car1 eq car2 should be(false)
	}
	
	it("has a pretty toString()") {
	  val car = Car("HHR", 40000)
	  car.toString should be ("Car(HHR,40000)")
	}
}