//> using scala "3"
//> using lib "org.scalameta::munit::0.7.27"

// run me with either of these commands:
//     scala-cli run . --main-class wordcount.wctry.wcFromFileTry
//     scala-cli run . --main-class wordcount.wctry.wcFromFileTry --watch

package wordcount {    

    package wctry {

        import wordcount.WordCountUtils.*
        import scala.collection.immutable.VectorMap
        import scala.io.Source
        import scala.util.{Try, Success, Failure}
        import scala.util.Using

        def readFile(filename: String): Try[String] =
            Using(io.Source.fromFile(filename)) { bufferedSource =>
                bufferedSource
                    .getLines
                    // need to add the "\n" character back in
                    .map(line => s"$line\n")
                    .mkString
            }

        // short version
        def wcFromFile(filename: String): Try[VectorMap[String, Int]] =
            readFile(filename).map(wc(_))

        // longer version
        // def wcFromFile(filename: String): Try[VectorMap[String, Int]] =
        //     val maybeDocument: Try[String] = readFile(filename)
        //     maybeDocument.map(wc(_))

        @main
        def wcFromFileTry = 
            val map = wcFromFile("yoda.txt")
            println(map)
        
    }

}



