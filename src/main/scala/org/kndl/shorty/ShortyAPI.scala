package org.kndl.shorty

import net.fwbrasil.zoot.core.Api
import net.fwbrasil.zoot.core.request.RequestMethod
import scala.concurrent.Future
import net.fwbrasil.zoot.core.response.{NormalResponse, Response}

trait ShortyAPI extends Api {

  @endpoint(
     method = RequestMethod.POST,
     path = "/g"
  )
  def shortenURL(url: String): Future[String]

  @endpoint(
    method = RequestMethod.GET,
    path = "/:id"
  )
  def redirectTo(id: String): Future[String]

}
