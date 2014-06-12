package org.kndl.shorty.ds

import java.io.{BufferedReader, FileReader, PrintWriter, File}
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
    val reader = new BufferedReader(new FileReader(new File(dir,hash)))
    val str = reader.readLine
    val stored = str.unpickle[StoredURL]
    stored.url
  }

  def storeURL(hash: String, url: String): Unit = {
    val dir = new File(baseDir)
    if(!dir.exists())
      dir.mkdir()
    val writer = new PrintWriter(new File(dir,hash))
    val data = new StoredURL(hash,url,Map.empty[String,Integer])
    val str = data.pickle.toString
    writer.write(str)
    writer.close()
  }

  def getReferrers(hash: String): Map[String, Long] = ???

  def storeReferrer(hash: String, ref: String): Unit = ???

}
