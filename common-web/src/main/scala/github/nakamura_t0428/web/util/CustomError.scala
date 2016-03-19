package github.nakamura_t0428.web.util

import net.liftweb._
import net.liftweb.http._
import net.liftweb.common._
import net.liftweb.util.BindHelpers._
import scala.xml.Text
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JE._
import net.liftweb.util.CssSel
import scala.xml.NodeSeq
import net.liftweb.util.PassThru

/**
 * エラーハンドリング用のユーティリティ
 */
object CustomError {
  /** ロード時に指定されたページ内アンカーに移動する
   *  
   *  ページがロードされた時に与えられたページ内アンカーまで移動するJsCmdを生成する。
   *  エラー発生時にこの値を S.appendJs に渡すことでエラーカ所を表示できる。
   *  
   *  なお、hash には #を先頭に持つ文字列を指定すること。
   */
  def scrollToHashOnLoad(hash:String, offset:Int) = {
    OnLoad(scrollToHash(hash, offset))
  }
  
  /**
   * ページ内アンカーまでスクロールするスクリプト
   */
  def scrollToHash(hash:String, offset:Int) = {
    JsRaw(s"""
var target = $$('${hash}');

$$('html, body').stop().animate({
  'scrollTop': target.offset().top-${offset}
}, 150, 'swing', function(){
  window.location.hash = '${hash}';
});
""".trim)
  }
  
  /**
   * エラーを表示するための汎用メソッド（まとめて表示用）
   * 
   * 以下のようにエラー表示用のdivを指定し、エラーリストを与える事で、
   * そのDiv内にエラーを表示できる。
   * 
   * <pre>
   * CustomError.errorDiv("#errors", S.errors)
   * </pre>
   */
  def errorDiv(selector:String, errors: List[(scala.xml.NodeSeq, net.liftweb.common.Box[String])]) = {
    if(errors.isEmpty) {
      selector #> Text("")
    }
    else {
      s"${selector} *" #> errors.map{case(e, _) => {"li *" #> e}}
    }
  }
  
  /**
   * エラーを表示するための汎用メソッド（個別表示用）
   * 
   * 以下のようにエラーリストを与えることで、"エラーID-div"というIDを持つタグのクラスに
   * "has-error" というCSSクラスを追加する。
   * 
   * has-error はBootstrapの汎用CSSクラスで、form-controlやラベルを赤く表示する。
   */
  def hasErrorDiv(errors:List[(scala.xml.NodeSeq, net.liftweb.common.Box[String])]):CssSel = {
    if(errors.isEmpty) "#dummy" #> PassThru
    else {
      errors.filter(_._2.isDefined).map{
        case (msg, Full(name)) => {
          s"#${name}-div [class+]" #> "has-error"
        }
        case (_, _) => s"#dummy-div" #> PassThru
      }.reduceLeft(_ & _)
    }
  }
  
  /**
   * エラーを表示するための汎用メソッド（Ajax用）
   * 
   * idsが含むエラーIDについて、そのIDとメッセージでsetHtmlを行う。
   * Ajax利用時にエラーをHTML内に表示できる。
   */
  def ajaxError(ids:List[String], errors: List[(scala.xml.NodeSeq, net.liftweb.common.Box[String])]):JsCmd = {
    val errorMap = errors.filter(_._2.isDefined).map{
      case (msg, Full(id)) => (id, msg)
      case (msg, _) => ("global", msg)
    }.toMap
    val cmds:List[JsCmd] = ids.map(id => {
      val msg = errorMap.getOrElse(id, Text(""))
      SetHtml(id, msg)
    })
    cmds.reduceLeft(_ & _)
  }
}
