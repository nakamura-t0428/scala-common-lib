package github.nakamura_t0428.web.parts

import net.liftweb._
import net.liftweb.http._
import net.liftweb.util.BindHelpers._
import net.liftweb.common._
import net.liftweb.util.CssSel
import net.liftweb.http.js.JsCmd
import scala.xml.Text

object CommonSelect {
  def renderStaticSelect[T](selector:String, options:List[T], current:Option[T]):CssSel = {
    s"${selector}" #> {
      s"option .rewrite" #>
        options.map(opt =>{
          val optValue = if(opt.toString.isEmpty) " " else opt.toString
          current match {
            case Some(c) if c == opt => {<option value={Text(optValue)} selected="selected">{Text(opt.toString)}</option>}
            case _ => {<option value={opt.toString}>{Text(opt.toString)}</option>}
          }}) &
      "option .dummy" #> ""
    }
  }
  def renderStaticSelectMap[T](selector:String, options:Map[T,String], current:Option[T]):CssSel = {
    s"${selector}" #> {
      s"option .rewrite" #>
        options.map{case (opt, label) =>{
          val optValue = if(opt.toString.isEmpty) " " else opt.toString
          current match {
            case Some(c) if c == opt => {<option value={Text(optValue)} selected="selected">{Text(label)}</option>}
            case _ => {<option value={opt.toString}>{Text(label)}</option>}
          }}} &
      "option .dummy" #> ""
    }
  }
  def renderStaticSelectMap[T](selector:String, options:List[(T,String)], current:Option[T]):CssSel = {
    s"${selector}" #> {
      s"option .rewrite" #>
        options.map{case (opt, label) =>{
          val optValue = if(opt.toString.isEmpty) " " else opt.toString
          current match {
            case Some(c) if c == opt => {<option value={Text(optValue)} selected="selected">{Text(label)}</option>}
            case _ => {<option value={opt.toString}>{Text(label)}</option>}
          }}} &
      "option .dummy" #> ""
    }
  }
  
  def renderStaticMultiSelect[T](selector:String, options:List[T], currents:Set[T]):CssSel = {
    s"${selector}" #> {
      s"option .rewrite" #>
        options.map(opt =>{
          val optValue = if(opt.toString.isEmpty) " " else opt.toString
          if(currents.contains(opt)){<option value={Text(optValue)} selected="selected">{Text(opt.toString)}</option>}
          else {<option value={opt.toString}>{Text(opt.toString)}</option>}
          }) &
      "option .dummy" #> ""
    }
  }
  def renderStaticMapMultiSelect[T](selector:String, options:List[(String, T)], currents:Set[T]):CssSel = {
    s"${selector}" #> {
      s"option .rewrite" #>
        options.map{case (label, opt) =>{
          val optValue = if(opt.toString.isEmpty) " " else opt.toString
          if(currents.contains(opt)){<option value={Text(optValue)} selected="selected">{Text(label)}</option>}
          else {<option value={opt.toString}>{Text(label)}</option>}
          }} &
      "option .dummy" #> ""
    }
  }

}