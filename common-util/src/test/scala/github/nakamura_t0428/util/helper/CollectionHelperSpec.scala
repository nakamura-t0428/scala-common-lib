package github.nakamura_t0428.util.helper

import org.scalatest.FunSpec

class CollectionHelperSpec extends FunSpec {
  import CollectionHelper._
  
  describe("resizeList") {
    describe("整数リストの場合") {
      it("長すぎる場合に先頭から切りつめられること") {
        val lst = Stream.from(1).take(100).toList
        val res = resizeList(lst, 10, 0)
        assertResult((1 to 10).toList)(res)
      }
      it("短すぎる場合に0で埋められること") {
        val lst = List(1,2,3)
        val res = resizeList(lst, 5, 0)
        assertResult(List(1,2,3,0,0))(res)
      }
      it("短すぎる場合に-1で埋められること") {
        val lst = List(1,2,3)
        val res = resizeList(lst, 5, -1)
        assertResult(List(1,2,3,-1,-1))(res)
      }
    }
    describe("文字列リストの場合") {
      it("長すぎる場合に先頭から切りつめられること") {
        val lst:List[String] = List.fill(100)("init")
        val res = resizeList(lst, 5, "")
        assertResult(List.fill(5)("init"))(res)
      }
      it("短すぎる場合に空文字列で埋められること") {
        val lst:List[String] = List.fill(3)("init")
        val res = resizeList(lst, 5, "")
        assertResult(List("init","init","init","",""))(res)
      }
    }
  }
}