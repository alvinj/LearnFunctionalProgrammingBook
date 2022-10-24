//> using scala "3"
package wordcount

import scala.collection.immutable.VectorMap

// run me with either of these commands:
//     scala-cli WordCount.scala
//     scala-cli WordCount.scala --watch

object WordCountUtils:

    // STEP 1
    def replaceNewlinesWithBlanks(s: String): String =
         s.replaceAll("\n", " ")

    // STEP 2
    def stripFormattingCharacters(s: String): String =
        s.replaceAll(";", "")
         .replaceAll(",", "")
         .replaceAll("\\.", "")
         .replaceAll("—", "")       // this is the long (em) dash
         .replaceAll("!", "")
         .replaceAll("\\?", "")

    // STEP 3
    def squeezeBlankSpaces(s: String): String =
        s.replaceAll(" +", " ")

    // STEP 4
    def convertStringToListOfWords(s: String): Seq[String] =
        s.split(" ")
         .filter(_.trim != "")
         .toSeq

    // STEP 5
    def lowerCaseAllWords(listOfWords: Seq[String]): Seq[String] =
        listOfWords.map(_.toLowerCase)

    // STEP 6
    def convertWordListToWordCount(listOfWords: Seq[String]): Map[String, Int] =
        val wcMap = scala.collection.mutable.Map[String, Int]()
        for word <- listOfWords do
            if wcMap.contains(word) then
                val count = wcMap(word)
                wcMap(word) = count + 1
            else
                wcMap(word) = 1
            end if
        end for
        wcMap.toMap

    // STEP 5
    def sortMapByHighestValue(map: Map[String, Int]): VectorMap[String, Int] =
        VectorMap(map.toSeq.sortWith((t1, t2) => t1._2 > t2._2): _*)


    // this is the main 'wc' function that calls all the others.
    def wc(document: String): VectorMap[String, Int] = 
        val stringWithoutNewlines = replaceNewlinesWithBlanks(document)
        val stringWithoutFormatting = stripFormattingCharacters(stringWithoutNewlines)
        val stringWithSingleBlanks = squeezeBlankSpaces(stringWithoutFormatting)
        val listOfWords = convertStringToListOfWords(stringWithSingleBlanks)
        val lcListOfWords = lowerCaseAllWords(listOfWords)
        val initialMap = convertWordListToWordCount(lcListOfWords)
        val sortedMap = sortMapByHighestValue(initialMap)
        sortedMap

end WordCountUtils


@main
def wordCountApp = 

    import WordCountUtils.*

    val document = """
        |Foo bar’s baz?
        |
        |Buzzle; buzzy. Burf!
    """.stripMargin

    val wcMap = wc(document)
    println(wcMap)









