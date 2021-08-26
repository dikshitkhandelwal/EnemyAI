package game.enemyai

import game.AIAction
import game.enemyai.decisiontree.DecisionTreeValue
import game.lo4_data_structures.graphs.Graph
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.enemyai.Queue


object ExtraMethods {

  def PointByX(x1: Int, x2: Int, y1: Int, y2: Int): List[List[Int]] = {
    val Initial = List(x1, y1)
    val End = List(x2, y2)

    var a: List[List[Int]] = List(Initial, End)
    var b = List(a(0))

    if (a(1)(0) > a(0)(0)) {
      while (a(0)(0) != a(1)(0)) {
        a = List(List(a(0)(0) + 1, a(0)(1)), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }
    else if (a(0)(0) > a(1)(0)) {
      while (a(0)(0) != a(1)(0)) {
        a = List(List(a(0)(0) - 1, a(0)(1)), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }
    if (a(1)(1) > a(0)(1)) {

      while (a(0)(1) != a(1)(1)) {
        a = List(List(a(0)(0), a(0)(1) + 1), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }
    else if (a(0)(1) > a(1)(1)) {
      while (a(0)(1) != a(1)(1)) {
        a = List(List(a(0)(0), a(0)(1) - 1), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }
    b
  }


  def PointByY(x1: Int, x2: Int, y1: Int, y2: Int): List[List[Int]] = {
    val Initial = List(x1, y1)
    val End = List(x2, y2)

    var a: List[List[Int]] = List(Initial, End)
    var b = List(a(0))


    if (a(1)(1) > a(0)(1)) {

      while (a(0)(1) != a(1)(1)) {
        a = List(List(a(0)(0), a(0)(1) + 1), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }

    else if (a(0)(1) > a(1)(1)) {
      while (a(0)(1) != a(1)(1)) {
        a = List(List(a(0)(0), a(0)(1) - 1), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }


    if (a(1)(0) > a(0)(0)) {
      while (a(0)(0) != a(1)(0)) {
        a = List(List(a(0)(0) + 1, a(0)(1)), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }

    else if (a(0)(0) > a(1)(0)) {
      while (a(0)(0) != a(1)(0)) {
        a = List(List(a(0)(0) - 1, a(0)(1)), List(a(1)(0), a(1)(1)))
        b = b :+ a(0)
      }
    }

    b
  }


  def MakeGridLocation(input: List[List[Int]]): List[GridLocation] = {
    var FinalOutput: List[GridLocation] = List()

    for (ls <- input){
      FinalOutput = FinalOutput :+ new GridLocation(ls(0),ls(1))
    }
    FinalOutput
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

  def ListToLinkedList[A](input: List[A]): LinkedListNode[A] = {
    val ls = input.reverse.drop(1)
    var initialLinkedList = new LinkedListNode[A](input.reverse.head, null)
    for (grids<- ls){
      initialLinkedList = new LinkedListNode[A](grids, initialLinkedList)
    }
    initialLinkedList
  }



  def Helper (gameState: AIGameState, decisionTree: BinaryTreeNode[DecisionTreeValue]): AIAction = {
    val left = decisionTree.left
    val right = decisionTree.right
    if (decisionTree.value.check(gameState) == 0){
      decisionTree.value.action(gameState)
    }
    else if (decisionTree.value.check(gameState) < 0) {
      Helper(gameState, left)
    }
    else {
      Helper(gameState, right)
    }
  }

  def GraphId(gameState: AIGameState, grid: GridLocation): Int = {
    val output: Int = grid.x + grid.y*gameState.levelWidth
    output
  }


  def bfs[A](graph: Graph[A], startID: Int, endId: Int): Int = {

    var explored: Set[Int] = Set(startID)
    var distance:  Map[Int, Int] = Map(startID -> 0)

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()

      for (node <- graph.adjacencyList(nodeToExplore)) {
        if (!explored.contains(node)) {
          toExplore.enqueue(node)
          explored = explored + node
          distance = distance ++ Map(node -> (distance(nodeToExplore) + 1))
        }
      }
    }
    distance(endId)
  }

  def main(args: Array[String]): Unit = {



  }
}
