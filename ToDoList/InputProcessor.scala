package todolist

import IOHelper.showHelp
import scala.util.control.Exception.*

class InputProcessor(db: Database):

    /** 
     * `q`, to quit
     * `a [item info]`, to add a new item
     * `d [num to delete]`, to delete an item
     * `h`, to show help
     * `v` or `l` (lowercase `L`), to list items
     */
    def handleUserInput(input: String): Either[Throwable, Unit] = input match
        // process input
        // access the database
        // completely handle the input
        case "q" =>
            allCatch.either(System.exit(0))
        case "h" => 
            showHelp()
        case "v" | "l" => 
            handleView()
        case add if add.startsWith("a ") => 
            handleAdd(add.drop(2))
        case del if del.startsWith("d ") => 
            val maybeNumRowsDeleted: Either[Throwable, Int] = handleDelete(del.drop(2))
            maybeNumRowsDeleted match
                case Right(numRowsDeleted) =>
                    if numRowsDeleted == 1 then
                        handleView()
                    else
                        printDeleteErrorMsg()
                case Left(t) =>
                    printDeleteErrorMsg()
        case _ =>
            ???
    end handleUserInput

    private def printDeleteErrorMsg(): Either[Throwable, Unit] = 
        allCatch.either(System.err.println("Had a problem deleting that."))

    private def handleDelete(taskIdString: String): Either[Throwable, Int] = 
        val taskId = taskIdString.toInt
        db.delete(taskId - 1)

    private def handleAdd(task: String): Either[Throwable, Unit] =
        db.insert(task)
        handleView()

    private def handleView(): Either[Throwable, Unit] = allCatch.either {
        // attempt to read all the tasks from the database.
        // this function returns a list of strings, wrapped inside
        // a Try:
        val res: Either[Throwable, Seq[String]] = db.selectAll()

        // handle the Success and Failure cases:
        res match
            case Right(tasks) => 
                // in the Success case we get a list of tasks (strings),
                // so print those out in a nice format:
                printTasks(tasks)
            case Left(t) => 
                // in the Failure case, where we can’t access the database,
                // print the exception (a Throwable) we get from selectAll:
                System.err.println(t)
    }

    private def printTasks(tasks: Seq[String]): Unit = 
        for
            (task, count) <- tasks.zip(LazyList from 1)
        do
            println(s"${count}. $task")

end InputProcessor
