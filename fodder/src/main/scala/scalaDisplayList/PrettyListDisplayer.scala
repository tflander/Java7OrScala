package scalaDisplayList

class PrettyListDisplayer {
    def englishPrint(list: List[String]): String = list.size match {
      case 0 => ""
      case x if x < 3 => list.mkString(" and ") + '.'
      case _ => list.dropRight(1).mkString(", ") + ", and " + list.takeRight(1).head + '.'
    } 
}

class PrettyListDisplayerForPatching(list: List[String]) {
	def englishPrint(): String = PrettyListDisplayer.ld.englishPrint(list)
}

object PrettyListDisplayer {
	val ld = new PrettyListDisplayer
	implicit def ListToPrettyListDisplayerForPatching(list: List[String]): PrettyListDisplayerForPatching = {
	  new PrettyListDisplayerForPatching(list)
	}
}
