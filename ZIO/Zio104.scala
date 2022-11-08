//> using scala "3"
//> using lib "dev.zio::zio::2.0.2"

// run me like this:
//     scala-cli Zio104.scala

import zio.*
import scala.io.StdIn

object Zio104 extends ZIOAppDefault:

    // recall that this is a `val`
    val promptForName: ZIO[Any, Throwable, String] =
        ZIO.attempt(StdIn.readLine("What’s your name? "))

    // new: when you need to pass a parameter into your
    // code, use a `def`
    def printName(name: String): ZIO[Any, Nothing, Unit] = 
        ZIO.succeed(println(s"Hello, $name"))

    // new: run effects in sequence with flatMap
    val run = promptForName.flatMap { name =>
        printName(name)
    }

