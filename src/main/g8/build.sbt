name := "$name$"

scalaVersion := "$scala_version$"
libraryDependencies ++= Seq(
  "org.apache.predictionio" %% "apache-predictionio-core" % "$pio_version$"   % "provided",
  "org.apache.spark"        %% "spark-mllib"              % "$spark_version$" % "provided",
  "org.scalatest"           %% "scalatest"                % "3.0.5" % "test"
)