package org.kndl.shorty.ds

case class StoredURL(hash: String, url: String, referrers: Map[String,Integer])
