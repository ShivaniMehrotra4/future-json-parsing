package com.knoldus

import net.liftweb.json.DefaultFormats

case class Posts(userId: String, id: String, title: String, body: String)

object PostsParsing extends GetUrlData {

  implicit val formats: DefaultFormats.type = DefaultFormats

  override def getData(url: String): String = super.getData(url)

  def parsePosts(jsonPostData: String): List[Posts] = {
    val parsedUserData = net.liftweb.json.parse(jsonPostData)
    parsedUserData.children.map { posts =>
      posts.extract[Posts]
    }
  }
}
