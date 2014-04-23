package org.kndl.shorty

import akka.actor.{Props, ActorSystem}
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.Http
import java.net.InetSocketAddress
import net.fwbrasil.zoot.finagle.FinagleServer
import net.fwbrasil.zoot.core.Server

class ShortyServer extends App {
  val system = ActorSystem.create("shortbread")
  system.actorOf(Props[ShortyService], "service")
  system.actorOf(Props[URLDataStore], "datastore")

  val server = {
    val serverBuilder =
      ServerBuilder()
        .codec(Http())
        .bindTo(new InetSocketAddress(9000))
        .name("CounterServer")

    new FinagleServer(Server[ShortyAPI](new ShortyService), serverBuilder.build)
  }
}
