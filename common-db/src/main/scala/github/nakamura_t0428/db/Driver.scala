package github.nakamura_t0428.db

import slick.driver.JdbcProfile

trait Driver {
  val driver: JdbcProfile
}