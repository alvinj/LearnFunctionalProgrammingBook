//> using scala "3"
//> using lib "org.scalameta::munit::0.7.27"
package wordcount

// run me with either of these commands:
//     scala-cli test .
//     scala-cli test . --watch

import WordCountUtils.*
import scala.collection.immutable.VectorMap

class WordCountTests extends munit.FunSuite:

    val emptyString = "  "
    test(emptyString) {
        val result = wc(emptyString)
        val expected = VectorMap[String, Int]()   // creates an empty VectorMap
        // val expected = VectorMap[]()           // can also use this
        assert(result == expected)
    }

    val fooBarBaz = "foo bar baz"
    test(fooBarBaz) {
        val result = wc(fooBarBaz)
        val expected = VectorMap(
            "bar" -> 1, "foo" -> 1, "baz" -> 1
        )
        assert(result == expected)
    }

    test("buzzle") {
        val testString = """
            |Foo bar’s baz?
            |
            |Buzzle? Buzzle!
            | buzzy. Burf!
        """.stripMargin
        val expected = VectorMap(
            "buzzle" -> 2, 
            "buzzy" -> 1,
            "burf" -> 1,
            "baz" -> 1,
            "bar’s" -> 1,
            "foo" -> 1,
        )
        assert(wc(testString) == expected)
    }

    test("yoda test") {
        val testString = """
            |Try not.
            |Do or do not.
            |There is no try.
        """.stripMargin
        val expected = VectorMap(
            "try" -> 2,
            "do" -> 2,
            "not" -> 2,
            "or" -> 1,
            "there" -> 1,
            "is" -> 1,
            "no" -> 1
        )
        assert(wc(testString) == expected)
    }

end WordCountTests


