package github.nakamura_t0428.util.helper

import org.scalatest._

class StringHelperSpec extends FunSpec {
  import StringHelper._
  
  describe("isTelNum") {
    it("数字とハイフンのみで構成され、先頭が数字ならば電話番号") {
      assert(isTelNum("090-1234-5678"))
      assert(isTelNum("0124-111-222"))
      assert(isTelNum("123-456-789"))
    }
    it("空文字は電話番号ではない") {
      assert(! isTelNum(""))
    }
    it("数字から始まっていなければ電話番号ではない") {
      assert(! isTelNum("-123-456-789"))
    }
    it("ハイフンが連続するならば電話番号ではない") {
      assert(! isTelNum("123--456-789"))
    }
    it("数字とハイフン以外を含むなら電話番号ではない") {
      assert(! isTelNum("#1234-5678"))
    }
  }
  
  describe("toReadableTelNum") {
    it("03 から始まる番号を整形する") {
      assert(toReadableTelNum("0312345678") == "03-1234-5678")
    }
    it("携帯番号を整形する") {
      assert(toReadableTelNum("09012345678") == "090-1234-5678")
      assert(toReadableTelNum("08012345678") == "080-1234-5678")
      assert(toReadableTelNum("07012345678") == "070-1234-5678")
      assert(toReadableTelNum("06012345678") == "060-1234-5678")
    }
    it("フリーダイアル(0120 + 6桁)を整形する") {
      assert(toReadableTelNum("0120123456") == "0120-123-456")
    }
    it("その他書式を4文字区切りにする") {
      assert(toReadableTelNum("012312345678") == "0123-1234-5678")
    }
    it("文字を含む番号も数値同様に扱える") {
      assert(toReadableTelNum("03abcdefgh") == "03-abcd-efgh")
      assert(toReadableTelNum("090abcdefgh") == "090-abcd-efgh")
      assert(toReadableTelNum("0123abcdefgh") == "0123-abcd-efgh")
      assert(toReadableTelNum("0120xxxzzz") == "0120-xxx-zzz")
    }
  }
  
  describe("alphIndex") {
    it("0から25までの数値に対して対応すう小文字アルファベットを返す") {
      assert(alphIndex(0) == "a")
      assert(alphIndex(1) == "b")
      assert(alphIndex(2) == "c")
      assert(alphIndex(3) == "d")
      assert(alphIndex(4) == "e")
      assert(alphIndex(5) == "f")
      assert(alphIndex(6) == "g")
      assert(alphIndex(7) == "h")
      assert(alphIndex(8) == "i")
      assert(alphIndex(9) == "j")
      
      assert(alphIndex(10) == "k")
      assert(alphIndex(11) == "l")
      assert(alphIndex(12) == "m")
      assert(alphIndex(13) == "n")
      assert(alphIndex(14) == "o")
      assert(alphIndex(15) == "p")
      assert(alphIndex(16) == "q")
      assert(alphIndex(17) == "r")
      assert(alphIndex(18) == "s")
      assert(alphIndex(19) == "t")
      
      assert(alphIndex(20) == "u")
      assert(alphIndex(21) == "v")
      assert(alphIndex(22) == "w")
      assert(alphIndex(23) == "x")
      assert(alphIndex(24) == "y")
      assert(alphIndex(25) == "z")
    }
    it("26以上の数値に対しては繰り返しで大きさを表す") {
      assert(alphIndex(26) == "aa")
      assert(alphIndex(26+1) == "bb")
      assert(alphIndex(26+26) == "aaa")
    }
  }
  
  describe("filterSpaces") {
    it("スペースが存在しない文字列は変化しない") {
      assert(filterSpaces("testtest") == "testtest")
      assert(filterSpaces("123456789") == "123456789")
      assert(filterSpaces("\\[]\\^~@;.") == "\\[]\\^~@;.")
    }
    it("スペースの位置に関わらず、スペースが除去される") {
      assert(filterSpaces(" test  test ") == "testtest")
      assert(filterSpaces("\t test \t test ") == "testtest")
      assert(filterSpaces("  \\  [  ]\t\\^~@;.  ") == "\\[]\\^~@;.")
    }
  }
}