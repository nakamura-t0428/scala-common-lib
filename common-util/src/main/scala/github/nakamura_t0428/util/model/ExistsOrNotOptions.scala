package github.nakamura_t0428.util.model

object ExistsOrNotOptions extends SelectOptions {
  val YES = 1
  val NO = 2
  
  val labelList = List(
      (YES, "ある"),
      (NO, "ない")
      )
    val labelMap:Map[Int, String] = labelList.toMap
}