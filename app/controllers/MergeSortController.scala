package controllers

import javax.inject.Inject

import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

class MergeSortController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){

  def message = Action.async{
    Future {
      /*
      MergeSort is the main call and Merge is the helper function.

      MergeSort needs to devid the array into pieces until it reaches the 1 element in a single array.
      then merge by comparing two arrays into one.

      1. mergeSort split into two
      2. it calls merge function which compares two arrays, left and right.
      3. either smaller comes first to the res array.
      4. in scala tail is the rest of the array except head.
      5. repeats until it's sorted.
       */
      def mergeSort(anIntArray: Array[Int]):Array[Int] = {
        println(anIntArray.mkString(" "))
        if(anIntArray.length <= 1 ) return anIntArray

        val (leftArray, rightArray) = anIntArray.splitAt(anIntArray.length/2)

        merge(leftArray, rightArray)
      }

      def merge(leftArray:Array[Int], rightArray:Array[Int]):Array[Int] = {

        var res = new ArrayBuffer[Int]()
        var left = mergeSort(leftArray)
        var right = mergeSort(rightArray)

        while(left.nonEmpty && right.nonEmpty){
          val leftHead = left.head
          val rightHead = right.head

          if(leftHead > rightHead){
            res += rightHead
            right = right.tail
          } else if(leftHead < rightHead){
            res += leftHead
            left = left.tail
          } else {
            res += leftHead
            res += rightHead
            right = right.tail
            left = left.tail
          }
        }
        if(left.nonEmpty){
          res ++= left
        } else if(right.nonEmpty){
          res ++= right
        }
        res.toArray
      }

      val res = mergeSort(Random.shuffle(1 to 10).toArray)
      println(res)
      Ok(res.mkString(", "))
    }
  }
}
