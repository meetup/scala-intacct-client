
lazy val jaxbPlugin = project
  .in(file("intacct-jaxb-plugin"))
  .settings(name := "Intacct Jaxb Plugin", sbtPlugin := true)

lazy val root = project.in(file(".")).dependsOn(jaxbPlugin)
