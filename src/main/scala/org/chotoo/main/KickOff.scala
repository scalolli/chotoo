package org.chotoo.main

import org.chotoo.fileproc.Initiator
import scalax.io.Resource

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
  		" \n Usage:- run Directory searchstring"

  /* Entry point of the application */
  def main(args: Array[String]) {
    println("Chotoo boostrapping....")

    if (args.length < 2) println(usage)
    else kickoff(args(0), args(1))
  }

  /* Initiate file processing */
  def kickoff(pathToDir: String, searchString: String) {
    val initiator = new Initiator(pathToDir, searchString)
    initiator.initiate()
  }
}