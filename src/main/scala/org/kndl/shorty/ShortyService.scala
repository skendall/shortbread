package org.kndl.shorty

import scala.concurrent.{Await, Future}
import net.fwbrasil.zoot.core.response.{ExceptionResponse, ResponseStatus, NormalResponse, Response}
import akka.actor.{Props, ActorRef, ActorSystem, Actor}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse}
import com.twitter.finagle.Service
import com.twitter.util

class ShortyService(ds: ActorRef) extends ShortyAPI {

  private val CHAR_MAP:Array[Char] = Array(
    'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    '0','1','2','3','4','5','6','7','8','9','-','_')

  def shortenURL(url: String): Future[String] = Future {
    val builder: StringBuilder = new StringBuilder()

    var dividend:Int = url.hashCode
    var remainder = 0

    while(dividend != 0) {
      remainder = dividend & 63
      dividend = dividend >>> 6
      builder + CHAR_MAP(remainder)
    }
    val hashString: String = builder.toString()
    ds ! STORE(hashString,url)
    hashString
  }

  def redirectTo(id: String): Future[String] = Future {
    implicit val timeout = Timeout(5 seconds)
    val f = ds ? GET(id)
    val url = Await.result(f,timeout.duration).asInstanceOf[URL]
    // this is incredibly broken but required because Zoot does not allow writing headers/status codes
    throw ExceptionResponse("",ResponseStatus.TEMPORARY_REDIRECT,Map("Location" -> url.url))
  }
}
