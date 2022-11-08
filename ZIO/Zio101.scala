//> using scala "3"
//> using lib "dev.zio::zio::2.0.2"

// run me like this:
//     scala-cli Zio101.scala

import zio.{ZIOAppDefault, Console}

object Zio101 extends ZIOAppDefault:
    def run = Console.printLine("Hello, World!")

