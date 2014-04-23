package org.kndl.shorty

import akka.actor.Actor

class URLDataStore extends Actor {

  var urls:Map[String,String] = Map()

  def receive = {
    case STORE(hash,url) => {
      urls = urls + (hash -> url)
      sender ! STORED(true)
    }
    case GET(hash) => {
      sender ! URL(urls.get(hash).get)
    }

  }

}
