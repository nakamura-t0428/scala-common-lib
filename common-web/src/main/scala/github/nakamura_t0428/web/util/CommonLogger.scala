package github.nakamura_t0428.web.util

import org.slf4j.MarkerFactory
import github.nakamura_t0428.util.log.MarkerTree

/**
 * 監査ログの基本ログマーカー
 */
object CommonLogger {
  /** 監査ログのルートマーカー */
  val M_AUDIT = MarkerFactory.getMarker("AUDIT")
  /** セッション関連のマーカー */
  val M_SESSION = MarkerFactory.getMarker("SESSION")
  /** セッションの保存に関するマーカー */
  val M_SESSION_SAVE = MarkerFactory.getMarker("SESSION_SAVE")
  /** セッションのロードに関するマーカー */
  val M_SESSION_LOAD = MarkerFactory.getMarker("SESSION_LOAD")
  /** セッションの削除に関するマーカー */
  val M_SESSION_DELETE = MarkerFactory.getMarker("SESSION_DELETE")
  /** セッションの有効期限切れに関するマーカー */
  val M_SESSION_EXPIRED = MarkerFactory.getMarker("SESSION_EXPIRED")
}

/**
 * 監査ログの基底クラス
 */
abstract class CommonLogger {
  val M_AUDIT = CommonLogger.M_AUDIT
  
  val M_SESSION = CommonLogger.M_SESSION
  val M_SESSION_SAVE = CommonLogger.M_SESSION_SAVE
  val M_SESSION_LOAD = CommonLogger.M_SESSION_LOAD
  val M_SESSION_DELETE = CommonLogger.M_SESSION_DELETE
  val M_SESSION_EXPIRED = CommonLogger.M_SESSION_EXPIRED
  
  MarkerTree.construct(MarkerTree(M_AUDIT,
    MarkerTree(M_SESSION,
      MarkerTree(M_SESSION_SAVE),
      MarkerTree(M_SESSION_LOAD),
      MarkerTree(M_SESSION_DELETE),
      MarkerTree(M_SESSION_EXPIRED)
    )
  ))
  
  /**
   * ログメッセージの書式化関数
   * 
   * 受け取ったログメッセージに対してIPアドレス等のシステム情報を付与する。
   */
  def mkLogMsg:String => String
}
