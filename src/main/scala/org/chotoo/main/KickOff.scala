package org.chotoo.main

import org.chotoo.fileproc.Initiator
import scalax.io.Resource
import scalax.file.Path

/**
 * Main File which kicks off the application.
 * As of now it takes two parameters as input.
 * First argument - FileName
 * Second argument - search String
 * Then initiates the file search calling Initiator.scala
 *
 * @author MohanMuddana
 */
object KickOff {

  val usage = "Directory Path and SearchString not supplied as arguments" +
    " \n Usage:- run Directory name_Of_File_containing_List_of_Strings_to_be_Searched"

  /* Entry point of the application */
  def main(args: Array[String]) {
    println("Chotoo boostrapping....")

    if (args.length < 2) println(usage)
    else {
      Path("entriesfound.txt").delete(true) //TODO: find some other alternatives
      val inputStrings = Path(args(1)).lines(includeTerminator = false).dropWhile { _.isEmpty }.
        takeWhile { _.nonEmpty }.toList
      inputStrings.foreach(kickoff(args(0), _))
    }
  }

  /* Initiate file processing */
  def kickoff(pathToDir: String, searchString: String) {
    val initiator = new Initiator(pathToDir, searchString)
    initiator.initiate()
  }
}