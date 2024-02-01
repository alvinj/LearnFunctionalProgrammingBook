package com.alvinalexander.utils

import scala.util.Using
import java.io.{BufferedWriter, File, FileWriter}
import java.io.{BufferedReader, FileReader}

object FileUtils:

    def readFile(filename: String): Either[Throwable, Seq[String]] = 
        Using(BufferedReader(FileReader(filename))) { reader =>
            val rez: Iterator[String] = Iterator.continually(reader.readLine()).takeWhile(_ != null)
            rez.toList
        }.toEither

    def writeToFile(filename: String, lines: Seq[String], append: Boolean): Either[Throwable, Unit] =
        Using(BufferedWriter(FileWriter(File(filename), append))) { bufferedWriter =>
            for line <- lines do bufferedWriter.write(s"$line\n")
        }.toEither
