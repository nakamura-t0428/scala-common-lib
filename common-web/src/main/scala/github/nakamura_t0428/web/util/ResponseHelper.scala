package github.nakamura_t0428.web.util

import net.liftweb.http.InMemoryResponse

object ResponseHelper {
  def csvFileResponse(data:String, fileName:String) =
    InMemoryResponse(
        data.getBytes("SHIFT-JIS"),
        "Content-Type" -> "text/plain; charset=shift-jis"::
        "Content-Disposition" -> s"""attachment; filename="${fileName}""""::Nil,
        cookies=Nil, 200
        )
}