import scala.io.Source

object Bot extends App {
  val botToken = "1425522412:AAEFhMNcaFaRq6iHWjcgUbzks4Njxb1Gi9U"
  val webSite = s"https://api.telegram.org/bot$botToken"

  // procedures
  val getUpdates = "/getUpdates"
  ///start - begins interaction with the user, e.g., by sending a greeting message.
  // This command can also be used to pass additional parameters to the bot (see Deep linking)
  ///help - returns a help message. It can be a short text about what your bot can do and a list of commands.
  ///settings - (if applicable) returns the bot's settings for this user and suggests commands to edit these settings.

  val update = Source.fromURL(webSite + getUpdates)

  println(update.mkString)

  val isMetric = true
  //val locationUrl = "";
  //var currentConditionsUrl = "";

  val apiKey = "1TsLx3HuA234XU56tFnAthme6oMtZlGm"

  val country = "%2C%20Latvia"
  val city = "Riga"
  val location = Source.fromURL(s"http://dataservice.accuweather.com/locations/v1/search?apikey=$apiKey&q=$city$country")

  //print(location.mkString)
}



