package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

class QuickSortController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){

  def message = Action.async{
    Future {
      /*
        1. return the input if the array has got only elem or nil
        2. find the middle value then delete from the array then put it back when return
        3. partition into Left and Right inorder for recursion
       */
      def quickSort(anIntArray: Array[Int]):Array[Int] = {
        if (anIntArray.length <= 1) {
          return anIntArray
        }
        val middle = anIntArray(anIntArray.length /2)
        val arrayBuffer = anIntArray.toBuffer
        arrayBuffer -= middle
        val (left, right) = arrayBuffer.toArray.partition(_ <= middle)
        quickSort(left) ++ Array(middle) ++ quickSort(right)
      }
      val ranArray = Random.shuffle(1 to 10).toArray
      val res = quickSort(ranArray)
      Ok(res.mkString(", "))
    }
  }
}
