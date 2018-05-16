import com.typesafe.scalalogging.LazyLogging

class HelloWorld extends LazyLogging {
  def greet(greetings: String*): String = {
    greetings.mkString(" ")
  }
}