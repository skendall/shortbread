name := "shorty"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "net.fwbrasil" %% "zoot-core" % "1.0-RC2",
  "net.fwbrasil" %% "zoot-finagle" % "1.0-RC2"
)
