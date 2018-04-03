package controllers


import javax.inject._

import play.api.mvc._
import akka.actor.{ Actor, ActorLogging, ActorRef, ActorSystem, Props }

import scala.collection.AbstractSeq
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.Random

@Singleton
class SelectionSortController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc) {

  def message = Action.async {
    Future {

//      seletionSort Function

      def selectionSort(anIntArray : Array[Int]):Array[Int] = {
        for (i <- anIntArray.indices) {
          for (n <- i + 1 until anIntArray.length) {
            if (anIntArray(n) < anIntArray(i)) {
              val temp = anIntArray(i)
              anIntArray(i) = anIntArray(n)
              anIntArray(n) = temp
            }
          }
        }
        anIntArray
      }
//      Generates a shuffled random 1 to 100 array
      val res = selectionSort(Random.shuffle(1 to 100).toArray)
//      return to http 200 response
      Ok(res.mkString(", \n"))
    }
  }
}
