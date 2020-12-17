import cats.implicits.toFunctorOps

import scala.io.Source
//import io.circe._
//import io.circe.generic.JsonCodec
//import io.circe.parser._
import scala.io.{BufferedSource, Source}
import scala.util._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object Bot extends App {
  val botToken = "1425522412:AAEFhMNcaFaRq6iHWjcgUbzks4Njxb1Gi9U"
  val webSite = s"https://api.telegram.org/bot$botToken"

  // procedures
  val getUpdates = "/getUpdates"
  val sendMsg = "/sendMessage?"
  ///start - begins interaction with the user, e.g., by sending a greeting message.
  // This command can also be used to pass additional parameters to the bot (see Deep linking)
  ///help - returns a help message. It can be a short text about what your bot can do and a list of commands.
  ///settings - (if applicable) returns the bot's settings for this user and suggests commands to edit these settings.

  def update = Json.parse(Source.fromURL(webSite + getUpdates).mkString)

  /*val updateParsed = parse(update.mkString).getOrElse(Json.Null)
  val updateCursor: HCursor = updateParsed.hcursor

  val updateOk: Decoder.Result[Boolean] = updateCursor.downField("ok").as[Boolean]*/

  //val updateParsed: JsValue = Json.parse(update.mkString)
  //val updateCursor: HCursor = updateParsed.hcursor

  //val updateOk: Decoder.Result[Boolean] = updateCursor.downField("ok").as[Boolean]

  val isMetric = true
  //val locationUrl = "";
  //var currentConditionsUrl = "";

  val apiKey = "1TsLx3HuA234XU56tFnAthme6oMtZlGm"

  val country = "%2C%20Latvia"
  val city = "Riga"
  //val location = Source.fromURL(s"http://dataservice.accuweather.com/locations/v1/search?apikey=$apiKey&q=$city$country")

  //print(location.mkString)
  def result = (update \ "result").get
  def totalResults = (update \ "result" \\ "update_id").size - 1

  //println(result)
  //println(totalResults)

  def getChatId = (update \ "result" \ totalResults \ "message" \ "chat" \ "id").get
  //println(getChatId)
  def getMsgText = (update \ "result" \ totalResults \ "message" \ "text").get.toString
  //println(getMsgText)
  def latestResult = (update \ "result" \ totalResults).get

  def sendMessage(chat_id: Int, text: String): Unit= {
    val g: JsValue = Json.parse(Source.fromURL(webSite + sendMsg + s"chat_id=$chat_id&text=$text").mkString)
    //println(g)
  }

  var initUpdateId: Int = (latestResult \ "update_id").get.toString.toInt
  //println(s"updateId0 $updateId")
  while(true) {
    val updated = latestResult
    //println((updated \ "update_id").get.toString.toInt)
    //println(s"updateId1 $updateId")
    if (initUpdateId != (updated \ "update_id").get.toString.toInt)
      {//println(getMsgText.toLowerCase)
      if (getMsgText.toLowerCase == "hi")
        {sendMessage(getChatId.toString.toInt, "Sveiks!")}
      else if (getMsgText.toLowerCase == "/weather_now")
        {sendMessage(getChatId.toString.toInt, "Laiciņš šodien foršs!")}
      else
        {sendMessage(getChatId.toString.toInt, "Atvainojos, nesaprotu komandu")}}
    //println(s"updateId2 $updateId")
    initUpdateId = (updated \ "update_id").get.toString.toInt
    //println(s"updateId3 $updateId")
  }
}



