package github.nakamura_t0428.example.db.dao

import github.nakamura_t0428.example.db.MyDB
import github.nakamura_t0428.example.db.model.User

class UserDao(DBM:MyDB) {
  import DBM.db
  import DBM.driver.api._
  
  def addUser(user:User) = {
        DBM.userTable += user
  }
}
