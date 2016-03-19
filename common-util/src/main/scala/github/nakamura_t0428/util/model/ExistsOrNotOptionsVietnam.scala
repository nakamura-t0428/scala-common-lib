package github.nakamura_t0428.util.model

object ExistsOrNotOptionsVietnam extends SelectOptions {
  val YES = 1
  val NO = 2
  
  val labelList = List(
      (YES, "Có"),
      (NO, "Không")
      )
    val labelMap:Map[Int, String] = labelList.toMap
}