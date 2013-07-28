import scala.sbt._
import Keys._

object Java7OrScalaBuild extends Build {
    lazy val root = Project(id = "Java7OrScala",
                            base = file(".")) aggregate(
                               AfterMarketParts,
                               CakeBoss,
                               Censorship,
                               FamilyTies,
                               hollywood,
                               MobRule,
                               MusicalFruit,
                               OnlyTheLonley,
                               ToBeOrNotToBe
                            )

    lazy val amp = Project(id = "AfterMarketParts",
                           base = file("AfterMarketParts"))

    lazy val cb = Project(id = "CakeBoss",
                           base = file("CakeBoss"))

    lazy val c = Project(id = "Censorship",
                           base = file("Censorship"))

    lazy val ft = Project(id = "FamilyTies",
                           base = file("FamilyTies"))

    lazy val h = Project(id = "hollywood",
                           base = file("hollywood"))

    lazy val mr = Project(id = "MobRule",
                           base = file("MobRule"))

    lazy val mf = Project(id = "MusicalFruit",
                           base = file("MusicalFruit"))

    lazy val otl = Project(id = "OnlyTheLonley",
                           base = file("OnlyTheLonley"))

    lazy val tbontb = Project(id = "",
                           base = file("ToBeOrNotToBe"))



}