package todolist

import IOHelper.{promptUser, readInput}

@main def ToDoList =

    val db = Database("./ToDoList.dat")
    val inputProcessor = InputProcessor(db)

    mainLoop()

    def mainLoop(): Either[Throwable, Unit] = for
        _     <- promptUser()
        input <- readInput()
        _     <- {
                    inputProcessor.handleUserInput(input)
                    mainLoop()
                }
    yield
        () 

