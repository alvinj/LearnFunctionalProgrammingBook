package todolist

import java.io.*
import scala.io.Source
import scala.util.{Try, Success, Failure}
import scala.util.Using


class Database(val dbFilename: String):

    // ------
    // STEP 1
    // ------
    def insert(record: String): Try[Unit] =
        writeToFile(List(record), true)

    /**
     * Notice that `dbFilename` comes in through a side door.
     *
     * Notes to self:
     *     - Throws FileNotFoundException when i set the file to read-only.
     *     - FileWriter throws IOException,
     *     - File throws NullPointerException,
     *     - `write` throws IOException
     * 
     * NOTE: this is intentionally written in an OOP style.
     * I’m using this approach with a var and null because (a) I want
     * to show how to use try/catch/finally, and (b) I don’t want to show
     * something like `scala.util.Using`, which may be confusing to a
     * beginner.
     *
     * TESTED: Returns "Failure(java.io.FileNotFoundException: foo (Permission denied))"
     * if the desired file is read-only.
     *
     * @param filename The name of the file to write to.
     * @param lines The strings to write to the file. "\n" is added to each 
     *              string when it is written.
     * @param append `true` to append, false to overwrite.
     * @return `true` if the file is written to, `false` otherwise.
     */
    private def writeToFile(lines: Seq[String], append: Boolean): Try[Unit] =
        var bw: BufferedWriter = null
        try
            bw = BufferedWriter(FileWriter(File(dbFilename), append))
            for line <- lines do bw.write(s"$line\n")
            Success(())
        catch
            case e: Throwable => Failure(e)
        finally
            if bw != null then bw.close


    /**
     * NOTE: `dbFilename` comes in through a side door.
     * NOTE: this is intentionally written in an OOP style.
     */
    def selectAll(): Try[Seq[String]] = 
        var bufferedSource: scala.io.BufferedSource = null
        try
            bufferedSource = Source.fromFile(dbFilename)
            val lines = for line <- bufferedSource.getLines yield line
            Success(lines.toList)
        catch
            case t: Throwable => Failure(t)
        finally
            if bufferedSource != null then bufferedSource.close


    // ------
    // STEP 3
    // ------
    /**
      * @param indexToDelete A 0-based index, because this function works with
      *         the list of tasks internally as a sequence.
      * @return The number of rows deleted inside a `Try`. When successful,
      *         this should be a `Success(1)`, because one row should be deleted.
      */
    def delete(indexToDelete: Int): Try[Int] = 
        // TODO: PROBABLY NEED TO EXPLAIN THIS APPROACH IN A PREVIOUS LESSON
        // get the original rows, then delete the desired row
        val maybeNumRowsDeleted = for
            rows           <- selectAll()
            newRows        =  CollectionUtils.removeElementByIndex(rows, indexToDelete)
            numRowsDeleted =  rows.size - newRows.size
            _              <- writeToFile(newRows, false)
        yield
            numRowsDeleted
        maybeNumRowsDeleted

end Database





