package scalaDisplayList
import org.scalatest._
import scalaDisplayList.PrettyListDisplayer._

class DisplayListTest extends FunSpec with ShouldMatchers {

  val teas = List("black", "green", "white")

  describe("mkString with one parameter") {

    it("Should print list comma separated") {
      teas.mkString(sep = ", ") should be("black, green, white")
    }
  }

  describe("mkString with three parameters") {

    it("Should print list with start and end") {
      teas.mkString(start = "types of teas: ", sep = ", ", end = ".") should be("types of teas: black, green, white.")
    }

    it("Doesn't suppress start and end on empty list") {
      Nil.mkString(start = "types of teas: ", sep = ", ", end = ".") should be("types of teas: .")
    }
  }

  describe("sandbox") {
    val ld = new PrettyListDisplayer
    
    it("should format three or more strings") {
    	ld.englishPrint(teas) should be("black, green, and white.")
    }
    
    it("should format two strings") {
    	ld.englishPrint(List("dog", "cat")) should be("dog and cat.")
    }
    
    it("should format a single string") {
    	ld.englishPrint(List("me")) should be("me.")
    }
    
    it("should format an empty list") {
    	ld.englishPrint(Nil) should be("")
    }
    
    it("should monkey-patch formatting into a raw list") {
    	teas.englishPrint() should be("black, green, and white.")
    }
  }
}
