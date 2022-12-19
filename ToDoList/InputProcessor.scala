package todolist

import scala.io.StdIn
import scala.util.{Try, Success, Failure}

class InputProcessor(db: Database):

    def handleUserInput(input: String): Try[Unit] = input match
        case "q" => 
            Try(System.exit(0))
        case "h" => 
            IOHelper.showHelp()
        case "v" | "l" => 
            handleView()
        case add if add.startsWith("a ") => 
            handleAdd(add.drop(2))
            handleView()
        case del if del.startsWith("d ") => 
            handleDelete(del.drop(2))
            handleView()
        case _ =>
            handleUnknown()
            handleView()

    def handleAdd(taskName: String): Try[Unit] =
        db.insert(taskName)

    def handleDelete(taskIdString: String): Try[Int] = Try {
        // the task-id we show starts with 1, so subtract 1 here,
        // because sequences are 0-based:
        val taskId: Int = taskIdString.toInt
        val maybeCount = db.delete(taskId - 1)
        maybeCount.get
    }

    def handleUnknown(): Try[Unit] = Try {
        System.err.println("Dude, I don’t know what that means.")
    }

    def handleView(): Try[Unit] = Try {
        val res = db.selectAll()
        res match
            case Success(tasks) => 
                printTasks(tasks)
            case Failure(t) => 
                System.err.println(t)
    }

    private def printTasks(tasks: Seq[String]): Unit = 
        for
            (task, count) <- tasks.zip(Stream from 1)
        do
            println(s"${count}. $task")


end InputProcessor


