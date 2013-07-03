package scalabean

case class Car(model: String, var miles: Int = 0) {
  require(miles >= 0)
  require(!model.isEmpty)
  
  def copy(): Car = Car(model, miles)
}
