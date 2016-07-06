credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
resolvers += "Nexus" at "https://nexus.blt.meetup.com/content/repositories/releases"

addSbtPlugin("com.meetup" % "sbt-plugins" % "18.0.0")

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")