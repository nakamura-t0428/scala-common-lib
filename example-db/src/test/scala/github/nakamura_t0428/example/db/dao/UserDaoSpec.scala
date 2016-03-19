package github.nakamura_t0428.example.db.dao

import org.scalatest._
import github.nakamura_t0428.example.db.MyDB
import github.nakamura_t0428.db.DbBase
import github.nakamura_t0428.util.helper.RandomHelper
import github.nakamura_t0428.util.converter.JavaDateConverter._
import github.nakamura_t0428.example.db.model.User

class UserDaoSpec extends FunSpec with BeforeAndAfter {
  val dbm = new MyDB(DbBase.RUNMODE_TEST)
  val init = new DBInitializer(dbm)
  val userDao = new UserDao(dbm)
  
  before {
    init.initDb
  }
  
  it("メールアドレスに重複の無いユーザを登録できる") {
    val nonDepUsers = List(
        User(None, "user001@shiftinc.jp", "テスト001", RandomHelper.randomPassedDate(365 * 30).sqlDate),
        User(None, "user002@shiftinc.jp", "テスト002", RandomHelper.randomPassedDate(365 * 30).sqlDate),
        User(None, "user003@shiftinc.jp", "テスト003", RandomHelper.randomPassedDate(365 * 30).sqlDate),
        User(None, "user004@shiftinc.jp", "テスト001", RandomHelper.randomPassedDate(365 * 30).sqlDate),
        User(None, "user005@shiftinc.jp", "テスト002", RandomHelper.randomPassedDate(365 * 30).sqlDate)
        )
    nonDepUsers.foreach(user => userDao.addUser(user))
  }
  
  it("メールアドレスが重複するユーザを登録できない") {
    val depUsers = List(
        User(None, "user011@shiftinc.jp", "テスト001", RandomHelper.randomPassedDate(365 * 30).sqlDate),
        User(None, "user011@shiftinc.jp", "テスト002", RandomHelper.randomPassedDate(365 * 30).sqlDate)
        )
    try {
      depUsers.foreach(user => userDao.addUser(user))
      fail("ユーザの多重登録に対して処理が継続されました")
    } catch {
    case e:Exception => //重複登録がブロックされた
    }
  }
  
}
