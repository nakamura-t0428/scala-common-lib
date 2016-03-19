package github.nakamura_t0428.util.model

object YesNoOptions extends SelectOptions {
  val YES = 1
  val NO = 2
  
  val labelList = List(
      (YES, "はい"),
      (NO, "いいえ")
      )
    val labelMap:Map[Int, String] = labelList.toMap
}