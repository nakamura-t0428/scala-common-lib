package github.nakamura_t0428.example.db.table

import github.nakamura_t0428.db.Driver
import github.nakamura_t0428.example.db.model.User
import java.sql.Date

trait UserTableT extends Driver {
  import driver.api._
  class UserTable(tag:Tag) extends Table[User](tag, "USER") {
    def id = column[Option[Long]]("USER_ID", O.PrimaryKey, O.AutoInc)
    def email = column[String]("EMAIL", O.SqlType("VARCHAR(191)"))
    def name = column[String]("NAME", O.SqlType("VARCHAR(64)"))
    def birthday = column[Date]("BIRTHDAY")
  
    def idx_email = index("idx_user_email", email, unique = true)
  
    def * = (id, email, name, birthday) <> (User.tupled, User.unapply)
  }
  val userTable = TableQuery[UserTable]
  val userTable_returnId = userTable  returning userTable.map(_.id)
}
