package github.nakamura_t0428.web.vars

import net.liftweb.http._
import net.liftweb.common.Box

/**
 * DBセッション管理用のセッション変数管理オブジェクトの基底クラス
 */
trait SessionAuthBase[I,T] {
  /** セッションへ認証オブジェクト(User)を格納する */
  def store(x:Box[T]):Unit
  /** セッションに格納された認証オブジェクトを取得する */
  def get:Box[T]
  
  /** DBから指定されたID(userId)の認証オブジェクトを取得し、セッションへ格納する */
  def load(id:I):Box[T]
  
  /** 現在のユーザセッションをクリアする。 */
  def clear = {
    S.request.foreach(_.request.session.terminate)
  }
}