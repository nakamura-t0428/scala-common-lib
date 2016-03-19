package github.nakamura_t0428.util.model

/**
 * 性別(MALE/FEMAIL)や(YES/NO)などを定義するオブジェクトの基底クラス
 */
trait SelectOptions {
  val UNDEF = 0
  val labelList:List[(Int, String)]
  val labelMap:Map[Int, String]
}
