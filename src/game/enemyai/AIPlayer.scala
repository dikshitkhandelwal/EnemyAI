package game.enemyai

import game.enemyai.decisiontree.{ActionNode, DecisionNode, DecisionTreeValue}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.physics.PhysicsVector
import game.{AIAction, Fire, MovePlayer}
import game.enemyai.PlayerLocation
import game.enemyai.ExtraMethods
import game.enemyai.ExtraMethods.{Helper, ListToLinkedList, MakeGridLocation, PointByX, PointByY}
import game.enemyai.Queue
import game.enemyai.bfs.bfs



class AIPlayer(val id: String) {



  def MakeList(playerLocations: LinkedListNode[PlayerLocation]): List[PlayerLocation]  = {
    if (playerLocations.next == null){
      List(playerLocations.value)
    }
    else{
      val newLinkList = playerLocations.next
      List(playerLocations.value) ++  MakeList(newLinkList)
    }
  }

  def Distance(one: PlayerLocation, two: PlayerLocation): Double = {
    Math.sqrt((Math.pow((two.x-one.x), 2)) + (Math.pow((two.y-one.y), 2)))
  }

  def locatePlayer(playerId: String, playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    if (playerLocations.value.playerId == playerId){
      playerLocations.value
    }
    else{
      locatePlayer(playerId, playerLocations.next)
    }
  }


  def closestPlayer(playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    val AIPlayer = locatePlayer(id, playerLocations)
    val InitialList: List[PlayerLocation] = MakeList(playerLocations)
    val SortedList : List[PlayerLocation] = InitialList.sortBy((x: PlayerLocation) => Distance(AIPlayer, x))
    SortedList.apply(1)
  }


  def pathToDirection(playerLocations: LinkedListNode[PlayerLocation], path: LinkedListNode[GridLocation]): PhysicsVector = {
    val AIPlayer = locatePlayer(id, playerLocations)
    val xCordinate = AIPlayer.x
    val yCordinate = AIPlayer.y
    val sizeOfGrid = path.size()
    if (sizeOfGrid == 1){
      val x = path.value.x + 0.5
      val y = path.value.y + 0.5
      new PhysicsVector(x - xCordinate, y - yCordinate)
    }
    else{
      val x = path.next.value.x + 0.5
      val y = path.next.value.y + 0.5
      new PhysicsVector(x - xCordinate, y - yCordinate)
    }
  }


  def computePath(start: GridLocation, end: GridLocation): LinkedListNode[GridLocation] = {
    val startX = start.x
    val startY = start.y

    val endX = end.x
    val endY = end.y

    val XFirst = PointByX(startX, endX, startY, endY)
    val YFirst = PointByY(startX, endX, startY, endY)

    var FinalList: List[List[Int]] = List()
    if (XFirst.length >= YFirst.length){
      FinalList = YFirst
    }
    else{
      FinalList = XFirst
    }
    val ListOfGrid = MakeGridLocation(FinalList)
    ListToLinkedList(ListOfGrid)
  }


  def makeDecision(gameState: AIGameState, decisionTree: BinaryTreeNode[DecisionTreeValue]): AIAction = {
    Helper(gameState, decisionTree)
  }

   //TODO: LT5
  def distanceAvoidWalls(gameState: AIGameState, start: GridLocation, end: GridLocation): Int ={
    val graph = gameState.levelAsGraph()
    val IdOne = ExtraMethods.GraphId(gameState, start)
    val IdTwo = ExtraMethods.GraphId(gameState, end)
    val distanceMap = ExtraMethods.bfs(graph, IdOne, IdTwo)
    distanceMap
  }

  // TODO: LT6
  def closestPlayerAvoidWalls(gameState: AIGameState): PlayerLocation = {
    val players = gameState.playerLocations
    val list_of_players = MakeList(players)
    val AIPlayer = locatePlayer(this.id, players)
    val sortedList = list_of_players.sortBy((x: PlayerLocation) => distanceAvoidWalls(gameState, AIPlayer.asGridLocation(), x.asGridLocation()))
    if (distanceAvoidWalls(gameState, AIPlayer.asGridLocation(), sortedList(0).asGridLocation()) == 0){
      sortedList(1)
    }
    else{
      sortedList(0)
    }
  }

/*BFS CODE FROM JESSE
*
* LINK => https://cse116.com/
* */
  // TODO: AO1

  def getPath(gameState: AIGameState): LinkedListNode[GridLocation] = {
    val player: LinkedListNode[PlayerLocation] = gameState.playerLocations
    val AIPlayer: PlayerLocation = this.locatePlayer(this.id, player)
    val closestPlayer: PlayerLocation = this.closestPlayerAvoidWalls(gameState)
    val idOne: Int = ExtraMethods.GraphId(gameState, AIPlayer.asGridLocation())
    val idTwo: Int = ExtraMethods.GraphId(gameState, closestPlayer.asGridLocation())
    bfs(gameState.levelAsGraph(), idOne, idTwo)
  }
















  // TODO: AO2
  def decisionTree(referencePlayer: AIPlayer): BinaryTreeNode[DecisionTreeValue] = {
    val huntClosestPlayer = new BinaryTreeNode[DecisionTreeValue](new ActionNode((gameState: AIGameState) => {
      val myLocation: PlayerLocation = referencePlayer.locatePlayer(referencePlayer.id, gameState.playerLocations)
      val closestPlayer: PlayerLocation = referencePlayer.closestPlayer(gameState.playerLocations)
      val path = referencePlayer.computePath(myLocation.asGridLocation(), closestPlayer.asGridLocation())
      val direction: PhysicsVector = referencePlayer.pathToDirection(gameState.playerLocations, path)
      MovePlayer(referencePlayer.id, direction.x, direction.y)
    }), null, null)

    val fire = new BinaryTreeNode[DecisionTreeValue](new ActionNode((gameState: AIGameState) => {
      Fire(referencePlayer.id)
    }), null, null)

    val fireProbability = 0.1
    val decider = new BinaryTreeNode[DecisionTreeValue](
      new DecisionNode((gameState: AIGameState) => if (Math.random() < fireProbability) -1 else 1), fire, huntClosestPlayer
    )

    decider
  }


}

