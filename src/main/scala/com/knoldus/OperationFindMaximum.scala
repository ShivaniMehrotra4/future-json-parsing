package com.knoldus

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This is the main class where all operations are performed.
 */
class OperationFindMaximum {
  val user1: String = UsersParsing.getData("https://jsonplaceholder.typicode.com/users")
  val finalListUser: Future[List[Users]] = Future {
    UsersParsing.parseUsers(user1)
  }

  val post: String = PostsParsing.getData("https://jsonplaceholder.typicode.com/posts")
  val finalListPost: Future[List[Posts]] = Future {
    PostsParsing.parsePosts(post)
  }

  val comment: String = CommentsParsing.getData("https://jsonplaceholder.typicode.com/comments")
  val finalListComments: Future[List[Comments]] = Future {
    CommentsParsing.parseComments(comment)
  }

  /**
   * findAnswers class is specific for finding the answers to queries like max posts and post with max comments
   *
   * @param choice - specifies whether to max posts or max commented post of user
   * @return - user name
   */
  def findAnswers(choice: String): Future[String] = {
    val listUsersWithPosts: Future[List[UsersWithPosts]] = Operations.findUsersWithPosts(finalListUser, finalListPost)
    val listPostsWithComments: Future[List[PostsWithComments]] = Operations.findPostsWithComments(finalListPost, finalListComments)

    if (choice.toLowerCase() == "post") {
      val userWithMaxPosts: Future[String] = Operations.findUserWithMaxPosts(listUsersWithPosts)
      userWithMaxPosts
    }
    else if(choice.toLowerCase == "comments") {
      val userForPostWithMaxComments: Future[String] = Operations.findUserWithMaxPostComments(listPostsWithComments, finalListUser)
      userForPostWithMaxComments
    }
    else {
      val errorMessage:Future[String] = Future {"Not valid" }
      errorMessage
    }
  }


}
