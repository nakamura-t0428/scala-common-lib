package github.nakamura_t0428.web.util

import com.typesafe.scalalogging.LazyLogging
import java.sql.Timestamp
import net.liftweb.http._
import net.liftweb.common.{Box, Full}
import github.nakamura_t0428.web.vars.SessionAuthBase
import CommonLogger._

/**
 * セッション管理用の基底クラス
 * 
 * ログイン時、ログアウト時の操作を共通化する。
 */
class SessionManagerBase[L,T](
    sessionAuth:SessionAuthBase[L,T],
    commonLogger: CommonLogger
    ) extends LazyLogging {
  
  def getSessionId:Box[String] = {
    S.containerSession.map(_.sessionId)
  }
  
  def logout = {
    sessionAuth.clear
  }
  
  def login(userId:L) = {
    (sessionAuth.load(userId), getSessionId) match {
      case (Full(_), Full(sessionId)) => {
        logger.info(M_SESSION_SAVE, commonLogger.mkLogMsg("SESSION_SAVED"))
      }
      case _ => {
        logger.error(s"Tryied to store UNKNOWN SessionUser: ${userId}")
      }
    }
  }
}