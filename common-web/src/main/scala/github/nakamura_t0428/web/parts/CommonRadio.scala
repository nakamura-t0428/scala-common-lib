package github.nakamura_t0428.web.parts

import net.liftweb._
import net.liftweb.http._
import net.liftweb.util.BindHelpers._
import net.liftweb.common._
import net.liftweb.util.CssSel
import net.liftweb.http.js.JsCmd

object CommonRadio {
  def renderRadioAjax[A](selector:String, current:A, options:List[A], func:A => JsCmd, attr:(String,String)*) = {
    val radios = SHtml.ajaxRadio[A](options, Full(current), func, attr:_*)
    
    {selector+" *"} #> {
      ".dummy" #> "" &
      ".rewrite" #> {radios.map(radio => {
        {{if(radio.key == current){"label [class+]" #> "active"}else{"dummy-tag" #> ""}}} &
        ":radio" #> radio.xhtml &
        ".lbl" #> radio.key.toString
      })}
    }
  }
  
  def renderRadioMapAjax[A](selector:String, current:A, options:List[(A, String)], func:A => JsCmd, attr:(String, String)*) = {
    val radios = SHtml.ajaxRadio[A](options.map(_._1), Full(current), func, attr:_*)
    val optionMap = options.toMap
    
    {selector+" *"} #> {
      ".dummy" #> "" &
      ".rewrite" #> {radios.map(radio => {
        {{if(radio.key == current){"label [class+]" #> "active"}else{"dummy-tag" #> ""}}} &
        ":radio" #> radio.xhtml &
        ".lbl" #> optionMap(radio.key)
      })}
    }
  }
  
  def renderRadio[A](selector:String, current:A, options:List[A], func:Box[A] => Any, attr:(String,String)*) = {
    val radios = SHtml.radioElem[A](options, Full(current), attr:_*)(func)
    
    {selector+" *"} #> {
      ".dummy" #> "" &
      ".rewrite" #> {radios.map(radio => {
        {{if(radio.key == current){"label [class+]" #> "active"}else{"dummy-tag" #> ""}}} &
        ":radio" #> radio.xhtml &
        ".lbl" #> radio.key.toString
      })}
    }
  }
  
  def renderRadioMap[A](selector:String, current:A, options:List[(A, String)], func:Box[A]=>Any, attr:(String,String)*) = {
    val radios = SHtml.radioElem[A](options.map(_._1), Full(current), attr:_*)(func)
    val optionMap = options.toMap
    
    {selector+" *"} #> {
      ".dummy" #> "" &
      ".rewrite" #> {radios.map(radio => {
        {{if(radio.key == current){"label [class+]" #> "active"}else{"dummy-tag" #> ""}}} &
        ":radio" #> radio.xhtml &
        ".lbl" #> optionMap(radio.key)
      })}
    }
  }
  
  /**
   * 初期表示のみでサーバサイドへの値受け渡しをしないラジオボタン
   * 
   * このメソッドはJSで値の送信を行う場合に利用する。
   */
  def renderStaticRadio[A](selector:String, current:A, options:List[A], attr:(String,String)*) = {
    {selector+" *"} #> {
      ".dummy" #> "" &
      ".rewrite" #> {options.map(opt => {
        {{if(opt == current){
          ":radio [checked]" #> "checked" &
          "label [class+]" #> "active"
        }else{
          "dummy-tag" #> ""
        }}} &
        ".lbl" #> opt.toString
      })}
    }
  }
  
  /**
   * 初期表示のみでサーバサイドへの値受け渡しをしないラジオボタン
   * 
   * このメソッドはJSで値の送信を行う場合に利用する。
   */
  def renderStaticMapRadio[A](selector:String, current:A, options:List[(A, String, List[(String,String)])], attr:(String, String)*) = {
    {selector+" *"} #> {
      ".dummy" #> "" &
      ".rewrite" #> {options.map{case (opt, label, attrs) => {
        {{if(opt == current){
          ":radio [checked]" #> "checked" &
          "label [class+]" #> "active"
        }else{
          "dummy-tag" #> ""
        }}} &
        attrs.map{case (a, v) => {
          s":radio [${a}]" #> v
        }}.reduce(_ & _) &
        ":radio [value]" #> opt.toString &
        ".mark" #> opt.toString &
        ".lbl" #> label
      }}}
    }
  }
  
  def renderStaticRadio[A](selector:String, current:A, options:List[(A, List[(String,String)])]) = {
    {selector+" *"} #> {
      ".dummy" #> "" &
      ".rewrite" #> {options.map{case (opt, attrs) => {
        s":radio [value]" #> opt.toString &
        {{if(opt == current){
          ":radio [checked]" #> "checked" &
          "label [class+]" #> "active"
        }else{
          "dummy-tag" #> ""
        }}} &
        attrs.map{case (a, v) => {
          s":radio [${a}]" #> v
        }}.reduce(_ & _) &
        ":radio [value]" #> opt.toString &
        ".lbl" #> opt.toString
      }}}
    }
  }
}