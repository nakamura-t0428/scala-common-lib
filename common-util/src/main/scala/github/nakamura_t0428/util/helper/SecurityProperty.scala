package github.nakamura_t0428.util.helper

import java.util.Properties
import scala.io.Source

object SecurityProperty {
  val prop = new Properties
  prop.load(Source.fromURL(PropertyHelper.loadProperties("/security")).bufferedReader)

  def failCount = prop.getProperty("auth.lock.fail.count").toInt
  def failIntervalMillis = prop.getProperty("auth.lock.fail.interval.hours").toLong * 3600 * 1000

  def staffFailCount = prop.getProperty("auth.staff.lock.fail.count").toInt
  def staffFailIntervalMillis = prop.getProperty("auth.staff.lock.fail.interval.hours").toLong * 3600 * 1000
  def staffPasswordEstimationDays = prop.getProperty("auth.staff.password.estimation.days").toInt
}
