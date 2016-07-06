name := "scala-intacct-client"
version := "1.0"
scalaVersion := "2.11.8"

enablePlugins(IntacctJaxbPlugin)
enablePlugins(CommonSettingsPlugin)
enablePlugins(CoverallsWrapper)

IntacctJaxbPlugin.fluentApiSettings

xjcRequestDtd := file(".") / "src" / "main" / "resources" / "dtd" / "intacct_request.v2.1.dtd"
xjcResponseDtd := file(".") / "src" / "main" / "resources" / "dtd" / "intacct_response.v2.1.dtd"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.slf4j" % "slf4j-log4j12" % "1.6.2",
  "javax.xml.bind" % "jaxb-api" % "2.2.2",
  "com.sun.xml.bind" % "jaxb-impl" % "2.2.3-1"
)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

licenses in ThisBuild += ("MIT", url("http://opensource.org/licenses/MIT"))