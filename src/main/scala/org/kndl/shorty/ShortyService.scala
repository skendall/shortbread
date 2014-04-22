package org.kndl.shorty

import scala.concurrent.Future
import net.fwbrasil.zoot.core.response.{ResponseStatus, NormalResponse, Response}

class ShortyService extends ShortyAPI {

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
    builder.toString()
  }

  def redirectTo(id: String): Future[Response] = Future {
    NormalResponse("",ResponseStatus.TEMPORARY_REDIRECT,Map("Location" -> "http://cnn.com"))
  }

}
