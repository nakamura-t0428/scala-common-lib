package github.nakamura_t0428.web.parts

import net.liftweb._
import net.liftweb.http._
import net.liftweb.common._
import net.liftweb.util.BindHelpers._
import net.liftweb.http.js.JsCmd
import scala.xml.Text
import net.liftweb.util.Settable
import net.liftweb.util.CssSel

object Pager {
  type PageVar = Settable{type ValueType=Int}
  type ChangePageFunc = Int => () => JsCmd
  private def pageButton(i:Int, changePage:ChangePageFunc) = SHtml.a(changePage(i), Text({i+1}.toString))
  private def pageElem(i:Int, changePage:ChangePageFunc) = <li>{pageButton(i, changePage)}</li>
  private def pageElemActive(i:Int, changePage:ChangePageFunc) = <li class="active">{pageButton(i, changePage)}</li>
  private def pageElemSep = <li disabled="disabled"><span>..</span></li>
  private def prevPager(page:PageVar, changePage:ChangePageFunc) = {
    if({page.get - 0} < 5) {
      Range(0, page.get).map(p => pageElem(p, changePage))
    }
    else {
      pageElem(0, changePage) ++ pageElemSep ++ pageElem(page.get-2, changePage) ++ pageElem(page.get-1, changePage)
    }
  }
  
  private def forwPager(pages:Int, page:PageVar, changePage:ChangePageFunc) = {
    if({{pages-1} - page.get} < 5) {
      Range(page.get+1, pages).map(p => pageElem(p, changePage))
    }
    else {
      pageElem(page.get+1, changePage) ++ pageElem(page.get+2, changePage) ++ pageElemSep ++ pageElem(pages-1, changePage)
    }
  }
  
  def renderPager(selector:String)(pages:Int, page:PageVar, changePage:ChangePageFunc):CssSel = {
    s"${selector}" #> {
      ".dummy" #> "" &
      ".pager-elem" #> {prevPager(page, changePage) ++ {pageElemActive(page.get, changePage)} ++ forwPager(pages, page, changePage)}
    }
  }
}
