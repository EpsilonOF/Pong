# Pong Game

## Introduction

This Pong is a paddle game programmed in Java 17 with JavaFX. The project is configured with Gradle using the JavaFX plugin. This game is largely inspired by the game [Pong](https://en.wikipedia.org/wiki/Pong), a classic from arcade halls in the 1970s.

The principle is simple: a field (the "*court*"), two paddles and a ball. The game is played with two players, each player being able to move their paddle on an up/down axis and aiming not to let the ball pass behind their paddle (which causes their immediate defeat). The ball moves at a constant speed and bounces off the walls (upper and lower limits of the window) and off the paddles.

For now, only the basic functionalities are implemented: 2 paddles controlled by 2 pairs of keys on the keyboard; basic management of ball bounces and detection of exit from the field by the sides (goal scored).

## Instructions

In the instructions below, you need to replace `myteam` with
- either the name of your team in gaufre, if the repository has been forked in the name of your team,
- or the login of the member of your team who forked the repository for everyone,
- or `cproj2022`, if you want to directly clone the teaching team's repository.

Note that in the latter case, you will be able to compile and run the project, but not do a `git push`.

### Download Pong

The most convenient way to download Pong to participate in its development is to clone the repository. From the console:

```bash
$ git clone https://gaufre.informatique.univ-paris-diderot.fr/myteam/pong
```

#### From a TP machine at Halle aux Farines

It seems that the git installation on the TP machines refuses to recognize the gaufre certificate. Fortunately, we can ask git to ignore certificate verification:

```bash
$ git clone -c http.sslVerify=false https://gaufre.informatique.univ-paris-diderot.fr/myteam/pong
```

Then, you can permanently save your choice to ignore verification for this repository:

```bash
$ cd pong
$ git config http.sslVerify false
```
(this will allow you to do `git push`, `git pull` and `git fetch` without having to specify `-c http.sslVerify=false` each time)

## Execution, compilation

After downloading/cloning the sources, you can compile and run the project using gradle.
The principle is that the `gradlew` script in the project directory will download and use the version of gradle that works with the project.

To compile, simply run, from the `pong` directory:

```bash
`./gradlew build`
```

To run, simply execute, from the `pong` directory:

```bash
`./gradlew run`
```

The project itself needs Java 17 to be compiled and executed.

### Special cases

#### On a TP machine at Halle aux Farines, from the console

If you are working from a machine in the TP rooms at Halle aux Farines, you must first pass certain parameters to gradle via an environment variable. This can be done by executing

```bash
$ source SCRIPT/envsetup
```

before launching any gradle command (notably `build` and `run`).

To be safe, you can insert this command in your `~/.bashrc` file, which will avoid having to type it manually at each new session. Remember to adapt the command by giving the absolute path to `envsetup`.

For information, the parameters passed to gradle indicate:

- the certificate file to use to download dependencies via HTTPS
- the proxy parameters of Halle aux Farines
- the path to Java 17

#### On a TP machine at Halle aux Farines using Eclipse

Eclipse installed on the TP machines contains a Java 17 distribution, and seems to know how to pass the correct configuration to Gradle. To work with eclipse, simply launch Eclipse (command `eclipse`), then import the project:

1. File > Import... > Gradle > Existing Gradle Project, Next >
2. choose the pong path and validate with Finish

In the "Gradle Tasks" tab, you will find the tasks to compile and run the project.

#### On a personal machine with Java 11 to 16

If you wish/need to work with an older version of Java, it is not very difficult to modify the configuration: just change the version numbers in `build.gradle`.

Then, you can work as with Java 17 (the project should be able to run as is).

Important: commit `build.gradle` and push it to your fork so that your entire team works with the same version of Java.

#### On a personal machine with Java 8 to 10 (DISCLAIMER: complicated!)

I haven't tested it, but Pong should be able to run (perhaps with some minor modifications). Unfortunately, it can be a bit complicated.

In broad terms:

- Start by disabling the JavaFX plugin in gradle (still in `build.gradle`), as it only works from Java 11 onwards.
- From now on, gradle no longer takes care of downloading and installing JavaFX. So you need to make sure you have installed it in another way. If you have a Java distribution without JavaFX (note that Oracle Java 8 contains JavaFX), you need to download and install it separately, taking care to take the same version number.
- If JavaFX has been installed separately, gradle needs to know about it so that it adds its directory to the classpath.
- If you are using Java 9 or 10, there is JPMS module configuration to be done manually. I will not explain, either what it is, or what it consists of here. It is possible to find help in the forums.

In short, it is strongly advised to use a more recent version of Java. Nevertheless, if you have no choice and are in difficulty, ask your teachers for help.

Important: here too, your entire team must then work with the same version of Java. Commit `build.gradle` and push it to your fork so that your entire team works with the same version of Java.

## Play

The left paddle is controlled by the CONTROL and ALT keys, while the right paddle is controlled by the UP and DOWN arrows.
