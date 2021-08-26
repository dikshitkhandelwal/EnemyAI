package tests

import org.scalatest.FunSuite
import game.enemyai.decisiontree.{ActionNode, DecisionNode, DecisionTreeValue}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.physics.PhysicsVector
import game.{AIAction, Fire, MovePlayer}
import game.enemyai.{AIGameState, AIPlayer, ExtraMethods, PlayerLocation, Queue}
import game.enemyai.ExtraMethods.{Helper, ListToLinkedList, MakeGridLocation, PointByX, PointByY}
import game.enemyai.bfs.bfs

class ApplicationObjective1 extends FunSuite{

  test("test1"){

    val gameState: AIGameState = new AIGameState
    val AIplayer: AIPlayer = new AIPlayer("one")

    val locationOne: PlayerLocation = new PlayerLocation(2,9,"one")
    val locationTwo: PlayerLocation = new PlayerLocation(8,6,"two")
    val locationThree: PlayerLocation = new PlayerLocation(4,9,"three")
    val locationFour: PlayerLocation = new PlayerLocation(7,2,"four")

    var myList: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](locationOne, null)
    myList = new LinkedListNode[PlayerLocation](locationTwo, myList)
    myList = new LinkedListNode[PlayerLocation](locationThree, myList)
    myList = new LinkedListNode[PlayerLocation](locationFour, myList)

    val walls:List[GridLocation] = List(new GridLocation(3, 10), new GridLocation(3, 9), new GridLocation(3,8), new GridLocation(3,7))
    gameState.levelWidth = 30
    gameState.levelHeight= 30
    gameState.playerLocations = myList
    gameState.wallLocations = walls

    println(AIplayer.closestPlayerAvoidWalls(gameState))
    println(AIplayer.distanceAvoidWalls(gameState, locationOne.asGridLocation(), locationThree.asGridLocation()))
    assert(AIplayer.getPath(gameState).size() == 7)
  }


  test("test2"){

    val gameState: AIGameState = new AIGameState
    val AIplayer: AIPlayer = new AIPlayer("one")

    val locationOne: PlayerLocation = new PlayerLocation(3,3,"one")
    val locationTwo: PlayerLocation = new PlayerLocation(6,3,"two")
    val locationThree: PlayerLocation = new PlayerLocation(12,13,"three")
    val locationFour: PlayerLocation = new PlayerLocation(4,7,"four")

    var myList: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](locationOne, null)
    myList = new LinkedListNode[PlayerLocation](locationTwo, myList)
    myList = new LinkedListNode[PlayerLocation](locationThree, myList)
    myList = new LinkedListNode[PlayerLocation](locationFour, myList)

    val walls:List[GridLocation] = List(new GridLocation(5, 1), new GridLocation(5, 2), new GridLocation(5,3), new GridLocation(5,4), new GridLocation(5,5), new GridLocation(5,6))
    gameState.levelWidth = 30
    gameState.levelHeight= 30
    gameState.playerLocations = myList
    gameState.wallLocations = walls

    println(AIplayer.closestPlayerAvoidWalls(gameState).x)
    println(AIplayer.closestPlayerAvoidWalls(gameState).y)

//    println(AIplayer.distanceAvoidWalls(gameState, locationOne.asGridLocation(), locationThree.asGridLocation()))
    assert(AIplayer.getPath(gameState).size() == 6)
  }




}
