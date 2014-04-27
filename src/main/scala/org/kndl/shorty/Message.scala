package org.kndl.shorty

case class STORE(hash: String, url: String)
case class STORE_REFERRER(hash: String, url: String)
case class STORED(success: Boolean)
case class GET(hash: String)
case class URL(url: String)
