package github.nakamura_t0428.util.model

object SexOptions extends SelectOptions {
  val Male = 1
  val Female = 2
  
  val labelList = List(
      (Male, "男性"),
      (Female, "女性")
      )
    val labelMap:Map[Int, String] = labelList.toMap
}
