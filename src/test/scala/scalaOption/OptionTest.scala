package scalaOption
import org.scalatest._

class OptionTest extends FunSpec with ShouldMatchers {
	it("should call a method with a populated return value") {
	  val thingOrNot = Thing.getThingWithKey("goodKey")
	  thingOrNot should be (Some("thing"))
	}
	
	it("should call a method with a empty return value") {
	  val thingOrNot = Thing.getThingWithKey("badKey")
	  thingOrNot should be (None)
	} 
	
	it("should be brave if we know we have a return value") {
	  val thing = Thing.getThingWithKey("goodKey").get
	  thing should be ("thing")
	}
	
	it("throws if we were wrong in expecting a return value") {
	  intercept[NoSuchElementException] {
		  val thing = Thing.getThingWithKey("badKey").get	  
	  }
	}
}