package com.knoldus

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

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

  def findAnswers(choice: String): Future[String] = {
    val listUsersWithPosts: Future[List[UsersWithPosts]] = Operations.findUsersWithPosts(finalListUser, finalListPost)
    val listPostsWithComments: Future[List[PostsWithComments]] = Operations.findPostsWithComments(finalListPost, finalListComments)

    if (choice.toLowerCase() == "post") {
      val userWithMaxPosts: Future[String] = Operations.findUserWithMaxPosts(listUsersWithPosts)
      userWithMaxPosts
    }
    else {
      val userForPostWithMaxComments: Future[String] = Operations.findUserWithMaxPostComments(listPostsWithComments, finalListUser)
      userForPostWithMaxComments
    }
  }


}
