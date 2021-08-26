package tests

import org.scalatest.FunSuite
import game.enemyai.{AIGameState, AIPlayer, PlayerLocation}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation

class LectureTask5 extends FunSuite{

  test("Hello"){

    val gameState: AIGameState = new AIGameState
    val AIplayer: AIPlayer = new AIPlayer("three")


    val locationOne: PlayerLocation = new PlayerLocation(1,1,"one")
    val locationTwo: PlayerLocation = new PlayerLocation(2,2,"two")
    val locationThree: PlayerLocation = new PlayerLocation(0,2,"three")
    val locationFour: PlayerLocation = new PlayerLocation(5,2,"four")


    var myList: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](locationOne, null)
    myList = new LinkedListNode[PlayerLocation](locationTwo, myList)
    myList = new LinkedListNode[PlayerLocation](locationThree, myList)
    myList = new LinkedListNode[PlayerLocation](locationFour, myList)

    val walls:List[GridLocation] = List(new GridLocation(3,10), new GridLocation(3,9),new GridLocation(3,8),new GridLocation(3,7))

    gameState.levelWidth = 6
    gameState.levelHeight= 6
    gameState.playerLocations = myList
    gameState.wallLocations = walls
    assert(AIplayer.distanceAvoidWalls(gameState, new GridLocation(2, 3), new GridLocation(5, 5)) == 5, "test1")
    val start = new GridLocation(2,5)
    val end = new GridLocation(4,1)
    assert(AIplayer.distanceAvoidWalls(gameState,start,end) == 6,"two")

  }

  test("test2"){

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

    val walls:List[GridLocation] = List(new GridLocation(3,10), new GridLocation(3,9),new GridLocation(3,8),new GridLocation(3,7))

    gameState.levelWidth = 10
    gameState.levelHeight= 10
    gameState.playerLocations = myList
    gameState.wallLocations = walls


    assert(AIplayer.distanceAvoidWalls(gameState, new GridLocation(2, 3), new GridLocation(5, 5)) == 5, "test1")

    val start = new GridLocation(2,9)
    val end = new GridLocation(4,9)

    assert(AIplayer.distanceAvoidWalls(gameState,start,end) == 8,"two")

  }

  test("test3"){
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

    gameState.levelWidth = 10
    gameState.levelHeight= 10
    gameState.playerLocations = myList

    val start = new GridLocation(2,9)
    val end = new GridLocation(4,9)
    assert(AIplayer.distanceAvoidWalls(gameState,start,end) == 2,"two")

  }
}
