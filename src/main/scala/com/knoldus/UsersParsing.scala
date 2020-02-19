package com.knoldus

import net.liftweb.json.DefaultFormats

case class Geo(lat: String, lng: String)

case class Address(street: String, suite: String, city: String, zipcode: String, geo: Geo)

case class Company(name: String, catchPhrase: String, bs: String)

case class Users(id: String, name: String, username: String, email: String, address: Address, phone: String, website: String, company: Company)

object UsersParsing extends GetUrlData {

  implicit val formats: DefaultFormats.type = DefaultFormats

  override def getData(url: String): String = super.getData(url)

  def parseUsers(jsonUserData: String): List[Users] = {
    val parsedUserData = net.liftweb.json.parse(jsonUserData)
    parsedUserData.children.map { users =>
      users.extract[Users]
    }
  }
}
