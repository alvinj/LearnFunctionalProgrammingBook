//> using scala "3"
//> using lib "dev.zio::zio::2.0.2"

// run me like this:
//     scala-cli Zio103.scala

import zio.*
import scala.io.StdIn

object Zio103 extends ZIOAppDefault:

    // new: wrap procedural code with `attempt`
    val promptForName: ZIO[Any, Throwable, String] =
        ZIO.attempt(StdIn.readLine("What’s your name? "))

    val run = promptForName
