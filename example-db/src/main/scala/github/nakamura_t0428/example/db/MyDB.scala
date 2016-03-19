package github.nakamura_t0428.example.db

import github.nakamura_t0428.db.DbBase
import github.nakamura_t0428.example.db.table.UserTableT

class MyDB(runMode:String = DbBase.envRunMode)
  extends DbBase("/mydb", runMode)
  with UserTableT
