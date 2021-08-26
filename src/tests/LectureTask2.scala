package tests

import game.enemyai.{AIPlayer, PlayerLocation}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest.FunSuite

class LectureTask2 extends FunSuite{

  test("Lecture Task 2"){

    val locationOne: PlayerLocation = new PlayerLocation(10.0, 20.0, "one")
    val locationTwo: PlayerLocation = new PlayerLocation(0.0, 0.0, "two")
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


    val grid1 : GridLocation = new GridLocation(10, 12)
    val grid2 : GridLocation = new GridLocation(1, 2)
    val grid3 : GridLocation = new GridLocation(3, 12)
    val grid4 : GridLocation = new GridLocation(0, 1)
    val grid5 : GridLocation = new GridLocation(0, 0)


    var myGrid1: LinkedListNode[GridLocation] = new LinkedListNode[GridLocation](grid5, null)
    myGrid1 = new LinkedListNode[GridLocation](grid4, myGrid1)
    myGrid1 = new LinkedListNode[GridLocation](grid3, myGrid1)
    myGrid1 = new LinkedListNode[GridLocation](grid2, myGrid1)
    myGrid1 = new LinkedListNode[GridLocation](grid1, myGrid1)

    var myGrid2: LinkedListNode[GridLocation] = new LinkedListNode[GridLocation](grid1, null)


    var myGrid3: LinkedListNode[GridLocation] = new LinkedListNode[GridLocation](grid2, null)
    myGrid3 = new LinkedListNode[GridLocation](grid1, myGrid3)


    var myGrid4: LinkedListNode[GridLocation] = new LinkedListNode[GridLocation](grid5, null)
    myGrid4 = new LinkedListNode[GridLocation](grid4, myGrid4)


    assert(PlayerOne.pathToDirection(myList, myGrid2).x == 0.5, "one")
    assert(PlayerOne.pathToDirection(myList, myGrid2).y == -7.5, "two")


    assert(PlayerOne.pathToDirection(myList, myGrid1).x == -8.5, "three")
    assert(PlayerOne.pathToDirection(myList, myGrid1).y == -17.5, "four")


    assert(PlayerTwo.pathToDirection(myList, myGrid2).x == 10.5, "five")
    assert(PlayerTwo.pathToDirection(myList, myGrid2).y == 12.5, "six")

    assert(PlayerTwo.pathToDirection(myList, myGrid1).x == 1.5, "seven")
    assert(PlayerTwo.pathToDirection(myList, myGrid1).y == 2.5, "eight")



  }

}
