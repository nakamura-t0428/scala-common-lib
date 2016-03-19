package github.nakamura_t0428.web.parts

import org.scalatest._
import net.liftweb.mocks.MockHttpServletRequest
import net.liftweb.mockweb.MockWeb
import github.nakamura_t0428.util.model.ExistsOrNotOptions
import net.liftweb.http.{S, LiftSession}
import net.liftweb.util._
import net.liftweb.common._

class SelectOptionsRadioSpec extends FunSpec {
  val session : LiftSession = new LiftSession("", StringHelpers.randomString(20), Empty)
  val xml1 = {
            <div id="test_radio1" class="btn-group" data-toggle="buttons">
                <label class="btn btn-primary rewrite disabled">
                  <input type="radio" name="exam_interest"/>
									<span class="lbl">とても楽しかった</span>
								</label>
                <label class="dummy btn btn-primary disabled">
                  <input type="radio" name="exam_interest"/>
									<span class="lbl">まあ楽しかった</span></label>
                <label class="dummy btn btn-primary disabled">
                  <input type="radio" name="exam_interest"/>
									<span class="lbl">あまり楽しくなかった</span>
								</label>
                <label class="dummy btn btn-primary disabled">
                  <input type="radio" name="exam_interest"/>
									<span class="lbl">苦痛だった</span>
								</label>
            </div>
    }
  override def withFixture(test:NoArgTest) = {
    S.initIfUninitted(session){
      test()
    }
  }

  describe("static renderRadio") {
    it("描画によりXMLからdummyが取り除かれること。") { () =>
      val rendered = SelectOptionsRadio.renderRadio("#test_radio1", ExistsOrNotOptions.YES, ExistsOrNotOptions)(xml1)
      for{
        label <- rendered \\ "div" if label.attribute("class").contains("dummy")
      } {
        fail("dummy を含む")
      }
    }
    it("描画によりXMLから目的のラベルが描画されること。") { () =>
      val rendered = SelectOptionsRadio.renderRadio("#test_radio1", ExistsOrNotOptions.YES, ExistsOrNotOptions)(xml1)
      val labelTexts = (rendered \\ "span").filter(_.attribute("class").contains("lbl")).map(_.text)
      ExistsOrNotOptions.labelList.map(_._2).contains(labelTexts)
      labelTexts.contains(ExistsOrNotOptions.labelList.map(_._2))
    }
    it("現在の値を持つラジオボタンがselectedになっていること。") {pending}
  }
}
