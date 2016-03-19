package github.nakamura_t0428.web.util

import net.liftweb.http.S

/**
 * 接続情報の取得メソッド
 */
object ConnectionInfo {
  /** 現在のセッションIDを返す */
  def sessionId:String = S.containerSession.map(_.sessionId).openOr("-")
  /** 接続元IPアドレスを返す */
  def sourceIp:String = S.containerRequest.map(_.remoteAddress).openOr("localhost")
}
