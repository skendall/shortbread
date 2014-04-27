package org.kndl.shorty

import akka.actor.Actor
import org.kndl.shorty.ds.{PersistentMapDataStore, DataStore}

class URLHandler extends Actor {

  var urls:Map[String,String] = Map()

  val db: DataStore = new PersistentMapDataStore()

  def receive = {
    case STORE(hash,url) => {
      urls = urls + (hash -> url)
      db.storeURL(hash,url)
      sender ! STORED(true)
    }
    case GET(hash) => {
      sender ! URL(db.getURL(hash))
    }

  }

}
