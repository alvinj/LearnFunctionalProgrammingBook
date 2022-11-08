//> using scala "3"
//> using lib "dev.zio::zio::2.0.2"

// run me like this:
//     scala-cli Zio102.scala

import zio.{ZIOAppDefault, ZIO, Console}
import java.io.IOException

object Zio102 extends ZIOAppDefault:
    val hello: ZIO[Any, IOException, Unit] =
        Console.printLine("Hello, World!")
    val run = hello

