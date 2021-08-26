package tests

import org.scalatest._
import game.enemyai.PlayerLocation
import game.enemyai.AIPlayer
import game.lo4_data_structures.linkedlist.LinkedListNode

class LectureTask1 extends FunSuite {


  test("your test") {

    val locationOne: PlayerLocation = new PlayerLocation(10.0, 20.0, "one")
    val locationTwo: PlayerLocation = new PlayerLocation(20.0, 30.0, "two")
    val locationThree: PlayerLocation = new PlayerLocation(30.0, 40.0, "three")
    val locationFour: PlayerLocation = new PlayerLocation(40.0, 50.0, "four")

    val PlayerOne: AIPlayer = new AIPlayer("one")
    val PlayerTwo: AIPlayer = new AIPlayer("two")
    val PlayerThree: AIPlayer = new AIPlayer("three")
    val PlayerFour: AIPlayer = new AIPlayer("four")
    val PlayerFive: AIPlayer = new AIPlayer("five")

    var myList: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](locationFour, null)
    myList = new LinkedListNode[PlayerLocation](locationThree, myList)
    myList = new LinkedListNode[PlayerLocation](locationTwo, myList)
    myList = new LinkedListNode[PlayerLocation](locationOne, myList)

    assert(PlayerOne.locatePlayer("one" ,myList).playerId == "one", "one" )
    assert(PlayerOne.locatePlayer("one" ,myList).x == 10.0, "two" )
    assert(PlayerOne.locatePlayer("one" ,myList).y == 20.0, "three" )


    assert(PlayerTwo.locatePlayer("two" ,myList).playerId == "two", "one" )
    assert(PlayerTwo.locatePlayer("two" ,myList).x == 20.0, "two" )
    assert(PlayerTwo.locatePlayer("two" ,myList).y == 30.0, "three" )

    assert(PlayerOne.closestPlayer(myList).playerId == "two", "four")
    assert(PlayerFour.closestPlayer(myList).playerId == "three", "last")
    println(myList)
    println(myList.size())







  }

}
