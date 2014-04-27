package org.kndl.shorty

import akka.actor.{Props, ActorSystem}
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.Http
import java.net.InetSocketAddress
import net.fwbrasil.zoot.finagle.FinagleServer
import net.fwbrasil.zoot.core.Server
import scala.concurrent.ExecutionContext.Implicits.global
import net.fwbrasil.zoot.core.mapper.JacksonStringMapper

object ShortyServer extends App {

  private implicit val mirror = scala.reflect.runtime.currentMirror
  private implicit val mapper = new JacksonStringMapper

  val system = ActorSystem.create("shortbread")
  val ds = system.actorOf(Props[URLHandler], "datastore")

  val server = {
    val serverBuilder =
      ServerBuilder()
        .codec(Http())
        .bindTo(new InetSocketAddress(9000))
        .name("ShortbreadServer")

    new FinagleServer(Server[ShortyAPI](new ShortyService(ds)), serverBuilder.build)
  }
}
