package todolist

import scala.io.StdIn
import scala.util.{Try, Success, Failure}

// RULE: any I/O must be wrapped in Try.
// NOTE: imagine that you are reading/writing via some REST service.
object IOHelper:

    def promptUser(): Try[Unit] = Try {
        println("\n(Commands: a \"task\", d 1, v, q, h)")
        print("Yo: ")
    }

    def readInput(): Try[String] = Try {
        StdIn.readLine()
    }

    def showHelp(): Try[Unit] = Try {
        val text = """
        |  Possible commands
        |  -----------------
        |  a [task]         - add a to-do item
        |  d [task number]  - delete a task by its number
        |  v                - view the list of tasks
        |  q                - quit
        |  h                - show this help text
        """.stripMargin.trim
        println(text)
    }

end IOHelper        

