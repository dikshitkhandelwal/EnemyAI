package tests

import game.enemyai.{AIGameState, AIPlayer, PlayerLocation}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest.{FunSuite, stats}

class LectureTask3 extends FunSuite{

  test("Lecture Task 3"){

    val gameState: AIGameState = new AIGameState
    var AIplayer: AIPlayer = new AIPlayer("three")

    val locationOne: PlayerLocation = new PlayerLocation(1,1,"one")
    val locationTwo: PlayerLocation = new PlayerLocation(2,2,"two")
    val locationThree: PlayerLocation = new PlayerLocation(0,2,"three")
    val locationFour: PlayerLocation = new PlayerLocation(5,2,"four")

    var myList: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](locationOne, null)
    myList = new LinkedListNode[PlayerLocation](locationTwo, myList)
    myList = new LinkedListNode[PlayerLocation](locationThree, myList)
    myList = new LinkedListNode[PlayerLocation](locationFour, myList)

    val start = new GridLocation(2,9)
    val end = new GridLocation(4,9)

    var testls1 = new LinkedListNode(new GridLocation(4, 9), null)
    testls1 = new LinkedListNode(new GridLocation(3, 9), testls1)
    testls1 = new LinkedListNode(new GridLocation(2, 9), testls1)

    assert(AIplayer.computePath(start, end).value.x == 2, "one")
    assert(AIplayer.computePath(start, end).value.y == 9, "two")

    assert(AIplayer.computePath(start, end).next.value.x == 3, "three")
    assert(AIplayer.computePath(start, end).next.value.y == 9, "four")

    assert(AIplayer.computePath(start, end).next.next.value.x == 4, "five")
    assert(AIplayer.computePath(start, end).next.next.value.y == 9, "six")


  }



}
