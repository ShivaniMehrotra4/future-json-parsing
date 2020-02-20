package com.knoldus

import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AsyncFlatSpec


class OperationFindMaximumSpec extends AsyncFlatSpec with BeforeAndAfterAll {

  var max: OperationFindMaximum = _

  override def beforeAll(): Unit = {
    max = new OperationFindMaximum()
  }


  "find Answers method " should " find User with Maximum number of posts " in {
    val actualResult = max.findAnswers("post")
    actualResult map { value => assert(value == "Clementina DuBuque") }
  }

  "it " should " find User whose Post has maximum number of comments " in {
    val actualResult = max.findAnswers("comments")
    actualResult map { value => assert(value == "Clementina DuBuque") }
  }

  "it " should " fail if anything other than posts/comments is entered " in {
    val actualResult = max.findAnswers("aaaa")
    actualResult map { value => assert(value == "Not valid") }
  }
}
