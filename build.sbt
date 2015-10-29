name := """play-benchmark-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  //"com.typesafe.play" % "play-json" % "2.4.3",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
//routesGenerator := InjectedRoutesGenerator

//EclipseKeys.preTasks := Seq(compile in Compile)

//EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

//EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(
//	EclipseCreateSrc.ManagedClasses, 
//	EclipseCreateSrc.ManagedResources
//) 

mainClass in assembly := Some("play.core.server.NettyServer")

fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

assemblyMergeStrategy in assembly := {
  case "META-INF/spring.tooling"                      => MergeStrategy.first
  case "javax/annotation/Syntax.class"                => MergeStrategy.first
  case "javax/annotation/Syntax.java"                 => MergeStrategy.first
  case "javax/annotation/meta/When.class"             => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

libraryDependencies ~= { _ map {
  case m if m.organization == "com.typesafe.play" =>
    m.exclude("commons-logging", "commons-logging").
      exclude("com.typesafe.play", "sbt-link")
  case n if n.organization == "com.google.code.findbugs" =>
    print("hello")
    n.exclude("com.google.code.findbugs", "jsr305")
  case o => o
}}

