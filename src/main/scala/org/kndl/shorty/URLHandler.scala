package org.kndl.shorty

import akka.actor.Actor
import org.kndl.shorty.ds.{FileSystemDataStore, DataStore}

class URLHandler extends Actor {

  var urls:Map[String,String] = Map()

  val db: DataStore = new FileSystemDataStore("urls")

  def receive = {
    case STORE(hash,url) => {
      urls = urls + (hash -> url)
      db.storeURL(hash,url)
      sender ! STORED(true)
    }
    case GET(hash) => {
      println("Sending url: " + db.getURL(hash))
      sender ! URL(db.getURL(hash))
    }

  }

}
