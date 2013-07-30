package scalaInversion
import org.scalatest._

class FunctionTypeDemoTest extends FunSpec with ShouldMatchers {

  describe("demo of function types (used by ApiWrapper.callBlock())") {
    
    it("demos function types using Java-like boilerplate") {

      def foo1(i: Int): String = {
        return "test" + i
      }

      def foo2: Int => String = foo1(_)

      def foo3: Int => String = intVal => {
        "whoo" + intVal
      }

      foo1(1) should be("test1")
      foo2(2) should be("test2")
      foo3(3) should be("whoo3")
    }

    it("demos function types with boilerplate removed  (used by ApiWrapper.callBlock())") {
      def foo1(i: Int) = "test" + i
      def foo2 = foo1(_)
      def foo3 = (i: Int) => "whoo" + i

      def foo4 = (i: Int) => {
        println("we can do anything in this anonymous block")
        "whoo" + i
      }

      def foo5 = any => "blah" + any
      
      foo1(1) should be("test1")
      foo2(2) should be("test2")
      foo3(3) should be("whoo3")
      foo4(4) should be("whoo4")
      foo5(5) should be("blah5")
      foo5(" blah") should be("blah blah")
    }
  }
}