//> using scala "3"
//> using lib "dev.zio::zio::2.0.2"

// run me like this:
//     scala-cli Zio107.scala

import zio.*
import scala.io.StdIn

object Zio107 extends ZIOAppDefault:

    // new: replace `attempt` and `succeed` with
    // the built-in `Console` functions
    def run =
        for
            _    <- Console.print("What’s your name? ")
            name <- Console.readLine
            _    <- Console.printLine(s"Hello, $name")
        yield
            ()


