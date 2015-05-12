name := "jenkins-agent"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-actor"            % "2.3.11",
  "com.typesafe.akka"      %% "akka-slf4j"            % "2.3.11",
  "org.aspectj"             % "aspectjweaver"         % "1.8.5",
  "org.aspectj"             % "aspectjrt"             % "1.8.5"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.8",
  "-encoding", "UTF-8"
)

parallelExecution in Test := false

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

fork in run := true

connectInput in run := true
