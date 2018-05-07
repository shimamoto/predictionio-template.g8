// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val root = (project in file(".")).
  settings(
    name := "predictionio-template.g8",
    // TODO As a temporary workaround, I copied scaffolds directory. See also https://github.com/foundweekends/giter8/pull/376
    g8 in Test ++= {
      val src = (sourceDirectory in Compile).value
      val out = (target in (Test, g8)).value
      IO.copyDirectory(src / "scaffolds", out / ".g8")

      val g8files = Path.allSubpaths(out / ".g8").collect {
        case (f, _) if f.isFile => f
      }.toSeq

      val base  = (unmanagedSourceDirectories in (Compile, g8)).value
      val srcs  = (sources in (Compile, g8)).value
      val props = (g8Properties in (Test, g8)).value
      val retval = giter8.G8(srcs pair Path.relativeTo(base), out, props)
      retval ++ g8files
    },
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
  )
