import sbt._
import Keys._

object Java7OrScalaBuild extends Build {
    lazy val root = Project(id = "Java7OrScala",
                            base = file(".")) aggregate(
                               afterMarketParts,
                               cakeBoss,
                               censorship,
                               familyTies,
                               hollywood,
                               mobRule,
                               musicalFruit,
                               onlyTheLonley,
                               toBeOrNotToBe
                            )

    lazy val afterMarketParts = Project(id = "AfterMarketParts",
                           base = file("AfterMarketParts"))

    lazy val cakeBoss = Project(id = "CakeBoss",
                           base = file("CakeBoss"))

    lazy val censorship = Project(id = "Censorship",
                           base = file("Censorship"))

    lazy val familyTies = Project(id = "FamilyTies",
                           base = file("FamilyTies"))

    lazy val hollywood = Project(id = "hollywood",
                           base = file("hollywood"))

    lazy val mobRule = Project(id = "MobRule",
                           base = file("MobRule"))

    lazy val musicalFruit = Project(id = "MusicalFruit",
                           base = file("MusicalFruit"))

    lazy val onlyTheLonley = Project(id = "OnlyTheLonley",
                           base = file("OnlyTheLonley"))

    lazy val toBeOrNotToBe = Project(id = "ToBeOrNotToBe",
                           base = file("ToBeOrNotToBe"))



}