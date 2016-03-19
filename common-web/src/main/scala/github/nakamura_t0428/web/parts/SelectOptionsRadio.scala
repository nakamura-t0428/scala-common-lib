package github.nakamura_t0428.web.parts

import net.liftweb._
import net.liftweb.http._
import net.liftweb.util.BindHelpers._
import github.nakamura_t0428.util.model.SelectOptions
import net.liftweb.http.RequestVar
import net.liftweb.common._
import net.liftweb.util.CssSel
import net.liftweb.util.Settable

object SelectOptionsRadio {
  type RadioVar = Settable{type ValueType=Int}
  def renderRadio(selector:String, v:RadioVar, options:SelectOptions, attr:(String,String)*):CssSel = {
    val radioElem = SHtml.radioElem[Int](options.labelList.map(_._1), Full(v.get), attr:_*)(_ match {
      case Full(value) => v.set(value)
      case _ => v.set(options.UNDEF)
    })
    s"${selector} *" #> {
      ".dummy" #> "" &
      ".rewrite" #> {radioElem.map(radio => {
        {if(v.get == radio.key){"label [class+]" #> "active"}else{"dummy-div" #> ""}} &
        ":radio" #> radio.xhtml &
        ".lbl" #> options.labelMap(radio.key)
      })}
    }
  }
  
  def renderRadio(selector:String, v:Int, options:SelectOptions, attr:(String,String)*):CssSel = {
    val radioElem = SHtml.radioElem[Int](options.labelList.map(_._1), Full(v), attr:_*)(i => {})
    s"${selector} *" #> {
      ".dummy" #> "" &
      ".rewrite" #> {radioElem.map(radio => {
        {if(v == radio.key){"label [class+]" #> "active"}else{"dummy-div" #> ""}} &
        ":radio" #> radio.xhtml &
        ".lbl" #> options.labelMap(radio.key)
      })}
    }
  }
}