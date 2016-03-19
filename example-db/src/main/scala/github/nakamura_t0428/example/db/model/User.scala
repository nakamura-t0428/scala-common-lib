package github.nakamura_t0428.example.db.model

import java.sql.Date

case class User(
    id:Option[Long],
    email:String,
    name:String,
    birthday:Date
    )
    