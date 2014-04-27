package org.kndl.shorty.ds

trait DataStore {
  def getURL(hash: String): String
  def storeURL(hash: String, url: String)
  def getReferrers(hash: String):Map[String,Long]
  def storeReferrer(hash: String, ref: String)
}
