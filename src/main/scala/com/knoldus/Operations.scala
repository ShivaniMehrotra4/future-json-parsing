package com.knoldus

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Operations {

  def findUsersWithPosts(listOfUsers: Future[List[Users]], listOfPosts: Future[List[Posts]]): Future[List[UsersWithPosts]] = {

    val ans = listOfUsers.map(onlyListUser => listOfPosts.map(post => onlyListUser.map(user => UsersWithPosts(user, post.filter(_.userId == user.id))))).flatten
    ans
  }

  def findPostsWithComments(listOfPosts: Future[List[Posts]], listOfComments: Future[List[Comments]]): Future[List[PostsWithComments]] = {

    val ans = listOfPosts.map(onlyListPost => listOfComments.map(comments => onlyListPost.map(
      post => PostsWithComments(post, comments.filter(_.postId == post.id))))).flatten
    ans
  }

  def findUserWithMaxPosts(listOfUsersWithPosts: Future[List[UsersWithPosts]]): Future[String] = {
    val sortedList = listOfUsersWithPosts.map(_.sortWith((x, y) => x.post.length <= y.post.length))
    val userWithMaxPost = sortedList.map(ansF => ansF.head.user.name)
    userWithMaxPost
  }

  def findUserWithMaxPostComments(listOfPostsWithComments: Future[List[PostsWithComments]], listOfUsers: Future[List[Users]]): Future[String] = {
    val sortedList = listOfPostsWithComments.map(_.sortWith((x, y) => x.comments.length <= y.comments.length))
    val firstElementOfSortedList = sortedList.map(_.head.post.userId)
    val userName = listOfUsers.map(user => firstElementOfSortedList.map(v => user.filter(_.id == v).map(_.name).head)).flatten
    userName

  }


}
