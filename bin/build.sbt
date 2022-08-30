lazy val root = (project in file("."))
  .settings(
    name := "spring-boot-scala-web",

    version := "1.0",

    scalaVersion := "2.11.8",

    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-web" % "2.4.2",
      "org.springframework.boot" % "spring-boot-starter-tomcat" % "2.4.2",
      "org.apache.tomcat.embed" % "tomcat-embed-jasper" % "9.0.46",
"javax.servlet" % "javax.servlet-api" % "4.0.0",
"javax.servlet.jsp" % "javax.servlet.jsp-api" % "2.3.3" % "provided",
      "javax.servlet" % "jstl" % "1.2",
      "org.springframework.boot" % "spring-boot-starter-websocket" % "2.4.2",
      "org.springframework.boot" % "spring-boot-starter-security" % "2.4.2",
      "org.springframework.security" % "spring-security-crypto" % "5.3.2.RELEASE",
      "org.springframework.security" % "spring-security-web" % "5.0.0.RELEASE",
      "org.springframework.security" % "spring-security-core" % "5.0.0.RELEASE",
      "org.springframework.security" % "spring-security-config" % "5.0.0.RELEASE",
      "de.mkammerer" % "argon2-jvm" % "2.7",
      "commons-codec" % "commons-codec" % "1.15",
      "org.bouncycastle" % "bcpkix-jdk15on" % "1.65",
      "org.webjars" % "webjars-locator-core" % "0.46",
      "org.webjars" % "bootstrap" % "3.3.7",
      "org.webjars" % "jquery" % "3.1.1-1"
     ),

    mainClass := Some("example.MyApp")
)
