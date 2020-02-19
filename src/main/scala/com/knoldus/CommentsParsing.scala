package com.knoldus

import net.liftweb.json.DefaultFormats

case class Comments(postId: String, id: String, name: String, email: String, body: String)

object CommentsParsing extends GetUrlData {

  implicit val formats: DefaultFormats.type = DefaultFormats

  override def getData(url: String): String = super.getData(url)

  def parseComments(jsonCommentData: String): List[Comments] = {
    val parsedCommentData = net.liftweb.json.parse(jsonCommentData)
    parsedCommentData.children.map { comments =>
      comments.extract[Comments]
    }
  }
}
