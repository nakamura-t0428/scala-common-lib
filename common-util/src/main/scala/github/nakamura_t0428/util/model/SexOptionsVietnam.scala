package github.nakamura_t0428.util.model

object SexOptionsVietnam extends SelectOptions {
  val Male = 1
  val Female = 2
  
  val labelList = List(
      (Male, "Nam"),
      (Female, "Nữ")
      )
    val labelMap:Map[Int, String] = labelList.toMap
}