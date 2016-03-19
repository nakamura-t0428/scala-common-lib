package github.nakamura_t0428.example.db.dao

import github.nakamura_t0428.example.db.MyDB
import github.nakamura_t0428.example.db.model.User
import com.typesafe.scalalogging.LazyLogging

class DBInitializer(dbm:MyDB) extends LazyLogging {
  import dbm.db
  import dbm.driver.api._
  
  def initDb = {
      try{
        try{
          dbm.userTable.schema.drop
          logger.info("Tables are dropped.")
        } catch {
          case _:Throwable => {}
        }
        dbm.userTable.schema.createStatements.foreach { s => logger.info(s + ";") }
        dbm.userTable.schema.create
      } catch {
        case e:Throwable => {
          logger.error("Failed to create tables", e)
        }
      }
  }
}
