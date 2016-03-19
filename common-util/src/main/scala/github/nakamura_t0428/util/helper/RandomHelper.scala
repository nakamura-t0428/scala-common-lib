package github.nakamura_t0428.util.helper

import scala.util.Random
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

object RandomHelper {
  def numericCharStream:Stream[Char] =
    Stream.cons(randomNumericChar, numericCharStream)

  def randomChoose[A](lst:List[A]):A = {
    lst(Random.nextInt(lst.size))
  }
  def randomChooseMulti[A](lst:List[A]):List[A] = {
    Range(0, Random.nextInt(lst.size)).map(e => Random.nextInt(lst.size)).toSet[Int].map(e => lst(e)).toList
  }
  def randomNumericChar:Char = {
    String.valueOf(Random.nextInt.abs%10).charAt(0)
  }
  def randomAlphabetics(length:Int) = Random.alphanumeric.take(length).foldLeft("")(_+_)
  def randomNumeric(length:Int) = numericCharStream.take(length).foldLeft[String]("")(_+_)

  def getUserQueryNo:String = {
    val dateFormatter = new SimpleDateFormat("yyMMdd")
    val str = "Q-" + dateFormatter.format(new java.util.Date) + "-" + Random.alphanumeric.take(5).foldLeft("")(_+_)
    str.toUpperCase
  }
  def genTesterQueryNo:String = {
    val dateFormatter = new SimpleDateFormat("yyMMdd")
    val str = "T-" + dateFormatter.format(new java.util.Date) + "-" + Random.alphanumeric.take(5).foldLeft("")(_+_)
    str.toUpperCase
  }

  def generateTmpToken:String = {
    "A-" + randomNumeric(4) + "-" + randomNumeric(4)
  }
  
  def randomPassedDate(agoDays:Int):Date = {
    val cal = Calendar.getInstance
    cal.add(Calendar.DATE, -Random.nextInt(agoDays))
    cal.getTime
  }
}
