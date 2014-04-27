package org.kndl.shorty.ds

import scala.pickling._
import scala.pickling.binary._
import scala.slick.session.Database
import st.sparse.persistentmap.PersistentMap

class PersistentMapDataStore extends DataStore {

  val db: scala.slick.session.Database = Database.forURL("jdbc:h2:~/db:shortbread", driver = "org.h2.Driver")

  val map = PersistentMap.create[String,String]("hash",db)

  def getURL(hash: String): String = map(hash)

  def storeURL(hash: String, url: String): Unit = map += hash -> url

  def getReferrers(hash: String): Map[String, Long] = ???

  def storeReferrer(hash: String, ref: String): Unit = ???
}
