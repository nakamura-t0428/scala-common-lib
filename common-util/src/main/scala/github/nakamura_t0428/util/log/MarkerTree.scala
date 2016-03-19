package github.nakamura_t0428.util.log

import com.typesafe.scalalogging.LazyLogging
import org.slf4j.Marker
import org.slf4j.MarkerFactory

case class MarkerTree(marker:Marker, children: MarkerTree*)
object MarkerTree {
  def construct(t : MarkerTree):Unit = {
    t.children.foreach(child => {
      child.marker.add(t.marker)
      construct(child)
    })
  }
}
