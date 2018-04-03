package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

class InsertionSortController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){

  def message = Action.async{
    Future {
      def insertionSort(anIntArray: Array[Int]):Array[Int] = {
        println(anIntArray.mkString(" "))
        for(i <- anIntArray.indices) {
          var j = i
          while (j > 0 && anIntArray(j) < anIntArray(j - 1)) {
            val temp = anIntArray(j-1)
            anIntArray(j-1) = anIntArray(j)
            anIntArray(j) = temp
            j -= 1
          }
        }
        anIntArray
      }

      val res = insertionSort(Random.shuffle(1 to 10).toArray)
      Ok(res.mkString(", "))
    }
  }
}
