package github.nakamura_t0428.util.helper

import scala.io.Source
import scala.util.parsing.combinator._
import java.io.InputStream
import java.net.URL
import scala.io.BufferedSource

object CsvHelper extends JavaTokenParsers {
  override def skipWhitespace = false
  
  def csvFile = rep(line <~ eol)
  def line = repsep(cell, ',')
  //最後のセル要素に改行が含まれるので trim で取り除く
  def cell = quotedCell | """[^,\n]*""".r ^^ (_.trim)
  //quotedCellが行の最後に来た場合のみ \r\n になる
  def eol = "\n" | "\r\n"
  def quotedCell = '"' ~> quotedChars ~ rep(escapeQuotedChars) <~ '"' ^^ {case(x~xs) => x + xs.mkString}
  def quotedChars = """[^"]*""".r
  def escapeQuotedChars = "\"\"" ~> quotedChars ^^ ('"' + _)
  
  type CSVParseResult = CsvHelper.ParseResult[List[List[String]]]
  
  def loadCsv(source:BufferedSource):CSVParseResult = {
    CsvHelper.parseAll(CsvHelper.csvFile, source.bufferedReader)
  }
  def loadCsv(file:String):CSVParseResult = {
    this.loadCsv(Source.fromFile(file, "Shift-JIS"):BufferedSource)
  }
  def loadCsv(in:InputStream):CSVParseResult = {
    this.loadCsv(Source.fromInputStream(in, "Shift-JIS"):BufferedSource)
  }
  def loadCsv(url:URL):CSVParseResult = {
    this.loadCsv(Source.fromURL(url, "Shift-JIS"):BufferedSource)
  }
}