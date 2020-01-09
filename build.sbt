val Http4sVersion = "0.20.15"
val CirceVersion = "0.11.1"
val Specs2Version = "4.1.0"
val ScalatestVersion = "3.0.7"
val LogbackVersion = "1.2.3"

lazy val assemblySettings = Seq(
  mainClass in assembly := Some("io.github.thurstonsand.website.Main"),
  assemblyMergeStrategy in assembly := {
    case x if x.contains("io.netty.versions.properties") => MergeStrategy.discard
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)

lazy val bloopSettings = Seq(
  bloopExportJarClassifiers in Global := Some(Set("sources")),
  bloopAggregateSourceDependencies in Global := true,
  bloopMainClass in (Compile, run) := Some("io.github.thurstonsand.website.Main")
)

lazy val root = (project in file("."))
  .settings(
    organization := "io.github.thurstonsand",
    name := "website",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.10",
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "org.scalatest"   %% "scalatest"           % ScalatestVersion % "test",
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
  )
  .settings(assemblySettings: _*)
  .settings(bloopSettings: _*)