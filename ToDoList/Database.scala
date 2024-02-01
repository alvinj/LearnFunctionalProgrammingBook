package todolist

import scala.util.Using
import com.alvinalexander.utils.FileUtils.{readFile, writeToFile}
import com.alvinalexander.utils.CollectionUtils.removeElementByIndex

class Database(val dbFilename: String):

    def insert(record: String): Either[Throwable, Unit] =
        writeToFile(dbFilename, List(record), true)

    /**
     * i.e., this is like "select * from tasks"
     */
    def selectAll(): Either[Throwable, Seq[String]] =
        readFile(dbFilename)

    def delete(indexToDelete: Int): Either[Throwable, Int] =
        val maybeNumsRowsDeleted: Either[Throwable, Int] = for
            rows1   <- selectAll()
            newRows =  removeElementByIndex(rows1, indexToDelete)
            _       <- writeToFile(dbFilename, newRows, false)
            rows2   <- selectAll()
            numRowsDeleted = rows1.size - rows2.size
        yield
            numRowsDeleted
        maybeNumsRowsDeleted

end Database


