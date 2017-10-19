name := "try_beam_scio"

version := "0.0.1"

scalaVersion := "2.12.3"

val beamVersion = "2.0.0"

libraryDependencies ++= Seq(
  // To fix NoSuchMethodError
  "com.google.protobuf" % "protobuf-java" % "3.2.0" force(),

  "org.apache.beam" % "beam-runners-core-java" % beamVersion,
  "org.apache.beam" % "beam-runners-direct-java" % beamVersion,
  "org.apache.beam" % "beam-runners-google-cloud-dataflow-java" % beamVersion,
  "com.spotify" %% "scio-core" % "0.4.3",
)

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

enablePlugins(PackPlugin)
