package com.knoldus

import net.liftweb.json.DefaultFormats

case class Posts(userId: String, id: String, title: String, body: String)

object PostsParsing extends GetUrlData {

  implicit val formats: DefaultFormats.type = DefaultFormats

  /**
   * This function returns extracted json data based on the URL given
   *
   * @param url - urls for posts
   * @return - extracted data in string
   */
  override def getData(url: String): String = super.getData(url)

  /**
   * This function parses Json posts data
   *
   * @param jsonPostData - contains extracted json post data
   * @return - list of posts
   */
  def parsePosts(jsonPostData: String): List[Posts] = {
    val parsedUserData = net.liftweb.json.parse(jsonPostData)
    parsedUserData.children.map { posts =>
      posts.extract[Posts]
    }
  }
}
