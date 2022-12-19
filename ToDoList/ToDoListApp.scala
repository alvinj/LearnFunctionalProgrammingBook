package todolist

import IOHelper.*
import scala.util.{Try, Success, Failure}

/**
 * The main application. Let the user do these things:
 *     - add a task (a "make coffee")
 *     - delete a task (d "make coffee")(d 1)
 *     - update a task (u 1 "brush teeth")
 *     - view the list of tasks (v)
 *     - show the help screen (h)
 *     - quit (q)
 */
@main def ToDoList = 

    val datafile = "./todo.dat"
    val db = Database(datafile)
    val processor = InputProcessor(db)

    def mainLoop(): Try[Unit] = for
        _     <- promptUser()
        input <- readInput()
        _     <- { processor.handleUserInput(input); mainLoop() }
    yield ()

    mainLoop()

end ToDoList

