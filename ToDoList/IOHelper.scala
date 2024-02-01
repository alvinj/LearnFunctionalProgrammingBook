package todolist

import scala.io.StdIn
import scala.util.Try
import scala.util.control.Exception.*

object IOHelper:

    def promptUser(): Either[Throwable, Unit] = allCatch.either {
        println("\n(Commands: a \"task\", d 1, h, q, v)")
        print("Yo: ")
    }

    def readInput(): Either[Throwable, String] = allCatch.either {
        StdIn.readLine()
    }

    def showHelp(): Either[Throwable, Unit] = allCatch.either {
        val text = """
        |  Possible commands
        |  -----------------
        |  a <task>         - add a to-do item
        |  h                - show this help text
        |  d [task number]  - delete a task by its number
        |  v                - view the list of tasks
        |  q                - quit
        """.stripMargin.trim
        println(text)
    }


    