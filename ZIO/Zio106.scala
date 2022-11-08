//> using scala "3"
//> using lib "dev.zio::zio::2.0.2"

// run me like this:
//     scala-cli Zio106.scala

import zio.*
import scala.io.StdIn

object Zio106 extends ZIOAppDefault:

    val promptForName: ZIO[Any, Throwable, String] =
        ZIO.attempt(StdIn.readLine("What’s your name? "))

    // new: replace ZIO with UIO
    // def printName(name: String): ZIO[Any, Nothing, Unit] =
    def printName(name: String): UIO[Unit] =
        ZIO.succeed(println(s"Hello, $name"))

    // new: replace `flatMap` with a `for` expression
    val run =
        for
            name <- promptForName
            _    <- printName(name)
        yield
            ()
