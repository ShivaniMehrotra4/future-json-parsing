package com.knoldus

import net.liftweb.json.DefaultFormats

case class Comments(postId: String, id: String, name: String, email: String, body: String)

object CommentsParsing extends GetUrlData {

  implicit val formats: DefaultFormats.type = DefaultFormats

  /**
   * This function returns extracted json data based on the URL given
   *
   * @param url - urls for comments
   * @return - extracted data in string
   */
  override def getData(url: String): String = super.getData(url)

  /**
   * This function parses Json comments data
   *
   * @param jsonCommentData - contains json format extracted data
   * @return - list of comments
   */
  def parseComments(jsonCommentData: String): List[Comments] = {
    val parsedCommentData = net.liftweb.json.parse(jsonCommentData)
    parsedCommentData.children.map { comments =>
      comments.extract[Comments]
    }
  }
}
