ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.1"

lazy val root = (project in file("."))
  .settings(
    name := "Metamorphosis"
  )

libraryDependencies ++= Seq(
  // Kafka
  "org.apache.kafka"    % "kafka-clients"                     % "3.4.0",
  "io.confluent"        % "kafka-avro-serializer"             % "7.3.3",
//  "net.cakesolutions"   %% "scala-kafka-client"               % "2.3.1",

  // Kafka Streams
  "org.apache.kafka"    % "kafka-streams"                     % "3.4.0",
  "org.apache.kafka"    %% "kafka-streams-scala"              % "3.4.0",

  // Scala Test
  "org.scalactic"       %% "scalactic"                        % "3.2.15",
  "org.scalatest"       %% "scalatest"                        % "3.2.15"    % Test,
)