package github.nakamura_t0428.web.parts

import net.liftweb._
import net.liftweb.http._
import net.liftweb.util.BindHelpers._
import net.liftweb.common._
import net.liftweb.util.CssSel
import net.liftweb.http.js.JsCmd

object CommonCheckBox {
  def renderCheckboxAjax(selector:String, label:String, value:Boolean, func:Boolean => JsCmd, attr:SHtml.ElemAttr*) = {
    {selector+" *"} #> {
      ".dummy" #> "" &
      {if(value){"label [class+]" #> "active"}else{"dummy-tag" #> ""}} &
      ":checkbox" #> SHtml.ajaxCheckbox(value, func, attr:_*) &
      ".lbl" #> label
    }
  }
  
  def renderCheckboxAjax(selector:String, labels:List[String], values:List[Boolean], func:(Int, Boolean) => JsCmd, attr:SHtml.ElemAttr*) = {
    {selector} #> labels.zip(values).zipWithIndex.map{case ((label, value),index) =>
      ".dummy" #> "" &
      {if(value){"label [class+]" #> "active"}else{"dummy-tag" #> ""}} &
      ":checkbox" #> SHtml.ajaxCheckbox(value, b => func(index, b), attr:_*) &
      ".lbl" #> label
    }
  }
  
  /**
   * 初期表示のみでサーバサイドへの値受け渡しをしないチェックボックス
   * 
   * このメソッドはJSで値の送信を行う場合に利用する。
   */
  def renderStaticCheckbox(selector:String, label:String, value:Boolean, attr:SHtml.ElemAttr*) = {
    {selector+" *"} #> {
      ".dummy" #> "" &
      {if(value){
        ":checkbox [checked]" #> "checked" &
        "label [class+]" #> "active"
      }else{
        "dummy-tag" #> ""
      }} &
      ".lbl" #> label
    }
  }
  
  /**
   * 初期表示のみでサーバサイドへの値受け渡しをしないチェックボックス
   * 
   * このメソッドはJSで値の送信を行う場合に利用する。
   */
  def renderStaticCheckbox(selector:String, labels:List[String], values:List[Boolean], attr:SHtml.ElemAttr*) = {
    {selector} #> labels.zip(values).zipWithIndex.map{case ((label, value),index) =>
      ".dummy" #> "" &
      {if(value){
        ":checkbox [checked]" #> "checked" &
        "label [class+]" #> "active"
      }else{
        "dummy-tag" #> ""
      }} &
      ".lbl" #> label
    }
  }
  def renderStaticCheckbox(selector:String, labels:List[(String, Boolean, List[(String, String)])]) = {
    {selector} #> labels.map{case (label, value, attrs) =>
      ".dummy" #> "" &
      {if(value){
        ":checkbox [checked]" #> "checked" &
        "label [class+]" #> "active"
      }else{
        "dummy-tag" #> ""
      }} &
      attrs.map{case (a, v) => {
        s":checkbox [${a}]" #> v
      }}.reduce(_ & _) &
      ".lbl" #> label
    }
  }
}
