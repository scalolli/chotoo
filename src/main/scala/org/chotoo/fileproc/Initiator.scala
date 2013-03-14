package org.chotoo.fileproc

import scalax.io._
import scalax.file._
import Line.Terminators._
import scala.Nothing
import scalax.file.Path
import Path._
import scalax.file.PathSet
import scalax.file.PathMatcher._

/**
 * Actual file processing happens here
 *
 *
 * @author MohanMuddana
 */
class Initiator(pathToDir: String, searchString: String) {

  def initiate() {
    println("FileName as retrieved " + pathToDir)
    val path = Path.fromString(pathToDir)

    //check if the given file or path is valid or not.
    path ** "*.txt" foreach (processFile)
  }

  /**
   * Start processing the file
   */
  def processFile(file: Path) {
    println(file.isFile.toString)
    println(file.lastModified)
    println(file.canRead.toString)
    println(file.path)

    // Take the first set of non-empty lines, keeping the terminator for each line
    val nonEmptyLines = file.lines(includeTerminator = false)
      .dropWhile(_ isEmpty)
      .takeWhile(_ nonEmpty)
    if (!nonEmptyLines.isEmpty) {
      nonEmptyLines.foreach(e => process(e.split(" ").toList))
    }
  }

  /**
   *  process method splits the lines into lists of strings
   */
  def process(s: List[String]) = {
    val d = s.filter(_ == searchString)
    println(d.contains(searchString))
    println(s.filter(_ == searchString))
    writeToOutput(d)
  }

  /**
   * Writes the searched string into a file
   */
  def writeToOutput(foundString: Traversable[String]) = {
    Path("entriesfound.txt").appendStrings(foundString)
  }
}