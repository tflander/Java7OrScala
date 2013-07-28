import sbt._
import Keys._

object HelloBuild extends Build {
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

    lazy val foo = Project(id = "AfterMarketParts",
                           base = file("AfterMarketParts"))

    lazy val bar = Project(id = "CakeBoss",
                           base = file("CakeBoss"))

    lazy val bar = Project(id = "Censorship",
                           base = file("Censorship"))

    lazy val bar = Project(id = "FamilyTies",
                           base = file("FamilyTies"))

    lazy val bar = Project(id = "MobRule",
                           base = file("MobRule"))

    lazy val bar = Project(id = "MusicalFruit",
                           base = file("MusicalFruit"))

    lazy val bar = Project(id = "OnlyTheLonley",
                           base = file("OnlyTheLonley"))

    lazy val bar = Project(id = "",
                           base = file("ToBeOrNotToBe"))

    lazy val bar = Project(id = "",
                           base = file("ToBeOrNotToBe"))


}