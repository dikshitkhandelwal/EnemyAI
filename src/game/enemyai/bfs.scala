package game.enemyai


import game.enemyai.decisiontree.{ActionNode, DecisionNode, DecisionTreeValue}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.physics.PhysicsVector
import game.{AIAction, Fire, MovePlayer}
import game.enemyai.PlayerLocation
import game.enemyai.ExtraMethods
import game.enemyai.ExtraMethods.{Helper, ListToLinkedList, MakeGridLocation, PointByX, PointByY, main}
import game.enemyai.Queue
import game.lo4_data_structures.graphs.Graph

object bfs {


  def ListToLinkedList(input: List[GridLocation]): LinkedListNode[GridLocation] = {
    val ls = input.reverse.drop(1)
    var initialLinkedList = new LinkedListNode(input.reverse.head, null)
    for (grids<- ls){
      initialLinkedList = new LinkedListNode(grids, initialLinkedList)
    }
    initialLinkedList
  }


  def bfs(graph: Graph[GridLocation], startID: Int, endId: Int): LinkedListNode[GridLocation] = {

    var explored: Set[Int] = Set(startID)

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)
    var map: Map[Int, List[Int]] = Map()
    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- graph.adjacencyList(nodeToExplore)) {
        if (!explored.contains(node)) {
          toExplore.enqueue(node)
          explored = explored + node
          map = map ++ Map(node -> (map.getOrElse(nodeToExplore, List()) ++ List(nodeToExplore)))
        }
      }
    }

    val ls: List[Int] = map(endId)
    var output: List[GridLocation] = List()
    for (l <- ls){
      output = output ++ List(graph.nodes(l))
    }
      output = output ++ List(graph.nodes(endId))
    ListToLinkedList(output)

  }





  def main(args: Array[String]): Unit = {
    println("hello")
  }



}

