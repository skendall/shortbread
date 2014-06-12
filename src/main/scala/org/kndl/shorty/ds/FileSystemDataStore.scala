package org.kndl.shorty.ds

import java.io.{FileReader, PrintWriter, File}
import scala.pickling._
import json._

class FileSystemDataStore(baseDir:String) extends DataStore {

  def getURL(hash: String): String = {
    val dir = new File(baseDir)
    if(!dir.exists())
      dir.mkdir()
    val f = new File(dir,hash)
    if(!f.exists())
      "None"
    val reader = new FileReader(new File(dir,hash))
  }

  def storeURL(hash: String, url: String): Unit = {
    val dir = new File(baseDir)
    if(!dir.exists())
      dir.mkdir()
    val writer = new PrintWriter(new File(dir,hash))
    writer.write(new StoredURL(hash,url,Map.empty[String,Integer]).pickle)
    writer.close()
  }

  def getReferrers(hash: String): Map[String, Long] = ???

  def storeReferrer(hash: String, ref: String): Unit = ???

}
