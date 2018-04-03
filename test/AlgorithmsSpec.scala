import org.scalatest._
import controllers._

import scala.collection.immutable

import scala.util.Random
class AlgorithmsSpec extends FlatSpec with Matchers {
  "InsertionSort" should "Do the job" in {
    val randomArray = Random.shuffle(1 to 100).toArray
  }

}
