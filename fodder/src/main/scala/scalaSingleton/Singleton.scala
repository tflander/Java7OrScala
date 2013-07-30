package scalaSingleton

object Db {
  def expensiveOperationOne = {
    println ("performing expensive operation one")
    "expensive operation one"
  }
  def expensiveOperationTwo = {
    println ("performing expensive operation two")
    "expensive operation two"
  }
}

object Singleton {
  println("Singleton Init Started")
  val x = Db.expensiveOperationOne
  lazy val y = Db.expensiveOperationTwo
  println("Singleton Init Done")
}