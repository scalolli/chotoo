package org.chotoo.main

import org.chotoo.fileproc.Initiator

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

  val usage = "File name and SearchString not supplied as arguments" +
  		" \n Usage:- run filename searchstring"

  /* Entry point of the application */
  def main(args: Array[String]) {
    println("Chotoo boostrapping....")

    if (args.length < 2) println(usage)
    else kickoff(args(0), args(1))
  }

  /* Initiate file processing */
  def kickoff(fileName: String, searchString: String) {
    val initiator = new Initiator(fileName, searchString)
    initiator.initiate()
  }
}