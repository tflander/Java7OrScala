package scalaOption

object Thing {
	def getThingWithKey(key: String): Option[String] = {
	  key match {
	    case "goodKey" => Some("thing")
	    case _ => None
	  }
	}
}