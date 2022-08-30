package example.model;

import java.util.ArrayList;

case class Challenge(
  var question: Int
  , var answer: String
){
  def this(){
    this(0, null);
  }
}

object Challenge {
  def listQuestions(): ArrayList[String] =
  {
    val list = new ArrayList[String]();
    list.add("Year you were born?")
    list.add("Last 4 digits of your SSN?")

    return list;
  }
}
