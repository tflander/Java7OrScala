package scalaKinds

class Animal(val speak: () => String)

case class Dog() extends Animal(() => "woof")
case class Cat() extends Animal(() => "mew mew")
case class Donkey() extends Animal(() => "hee-haw")

