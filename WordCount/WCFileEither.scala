//> using scala "3"
//> using lib "org.scalameta::munit::0.7.27"

// run me with either of these commands:
//     scala-cli run . --main-class wordcount.either.wcFromFileEither
//     scala-cli run . --main-class wordcount.either.wcFromFileEither --watch

package wordcount {

    package either {

        import wordcount.WordCountUtils.*
        import scala.collection.immutable.VectorMap
        import scala.io.Source
        import scala.util.{Try, Success, Failure}
        import scala.util.Using

        def readFile(filename: String): Either[Throwable, String] = 
            Using(io.Source.fromFile(filename)) { bufferedSource =>
                bufferedSource
                    .getLines
                    // need to add the "\n" character back in
                    .map(line => s"$line\n")
                    .mkString
            }.toEither   // <== convert Try to Either

        // short version
        def wcFromFile(filename: String): Either[Throwable, VectorMap[String, Int]] =
            readFile(filename).map(wc(_))

        /**
         * prints the map using the 'f' string interpolator, which i describe here:
         * https://alvinalexander.com/scala/scala-f-string-interpolator-printf-formatting-variable-substitution/
         */
        @main
        def wcFromFileEither = 
            val maybeMap = wcFromFile("yoda.txt")
            maybeMap match
                case Right(map) => 
                    for ((k,v) <- map) println(f"$k%-7s  => $v")
                case Left(t) =>
                    System.err.println(t)

    }

}



