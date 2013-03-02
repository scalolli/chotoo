package org.chotoo.fileproc

import scalax.io._
import scalax.file._
import Line.Terminators._
import scala.Nothing

/**
 * Actual file processing happens here
 * 
 * 
 * @author MohanMuddana
 */
class Initiator(fileName: String, searchString: String) {

  def initiate() {

    println("FileName as retrieved " + fileName)
    
    val file: Path = Path(fileName).toAbsolute
    
    //check if the given file or path is valid or not.
    file.isFile match {
      case true => processFile(file)
      case false => println("Filename or Path as given is not valid")
    } 
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
    val nonEmptyLines: LongTraversable[String] = Path(fileName).lines(includeTerminator = false)
      .dropWhile(_ isEmpty)
      .takeWhile(_ nonEmpty)
    
    nonEmptyLines.foreach(e => process(e.split(" ").toList))
    
  }
  
  /**
  *  process method splits the lines into lists of strings  
  */
  def process(s: List[String]) = {
    val d: List[String] = s.filter(_ == searchString)
    println(d.contains(searchString))
    println(s.filter(_ == searchString))
    writeToOutput(d)
  }

  /**
   * Writes the searched string into a file
   */
  def writeToOutput(foundString: Traversable[String]) = {
    Path("entriesfound.txt").writeStrings(foundString)
  }
}