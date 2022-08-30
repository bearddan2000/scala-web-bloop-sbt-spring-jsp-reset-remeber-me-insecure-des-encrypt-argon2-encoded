# scala-web-bloop-sbt-spring-jsp-reset-remeber-me-insecure-des-encrypt-argon2-encoded

## Description
A springboot secure web app with jsp support.
Three roles are defined; USER, ADMIN, and SUPER. All roles
can access pages `/home`, `/login`, and `/about`. Only USER
can access `/user` and ADMIN only `/admin` whereas SUPER can
navigate to either and have its own `/super`. Each role
has an action USER=VIEW ONLY, ADMIN=READ/WRITE, SUPER=CREATE.
All password are DES encrypted and encoded with argon2.

Presents a register form to create an inMemoryUser.
Once the user is created it is given the `USER` role
by default and auto logged in.

Presents a reset form to reset passwords on any user,
by default the user is reassigned `USER` role and auto
logged in. Only restriction on passwords are they match;
all validation is done client side.

Uses the rememberMe cookie for a 2 min window
this as well as other setting can be found in
`config/Security.scala`. One way to test is the following:
- Set rememberMe checkbox
- login
- set a bookmark to the secured page
- open a new window
- use the bookmark

rememberMe cookie does not redirect it only authenticates.

Compiled and ran from build server `bloop`.

# Build note
Dependencies must be compatable with jdk8 or less.

## Tech stack
- bloop
- scala
- gradle
  - springboot
  - jsp
  - bootstrap
  - jquery
  - datatable

## Docker stack
- hseeberger/scala-bloop-sbt:11.0.2-oraclelinux7_1.3.5_2.12.10

## To run
`sudo ./install.sh -u`
Available at http://localhost
- Login with id: user and password: pass
- Login with id: admin and password: pass
- Login with id: super and password: pass

## To stop (optional)
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`
