package com.msoft.structura;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JOptionPane;

public class AVLTree extends Node{
	private Node root;
	
	public AVLTree() {
		super();
	}

	public AVLTree(Node root) {
		super();
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	public Node search(Node node, int value) {
		if (node == null)
			return null;
		
		if (node.getValue() == value)
			return node;
		else if (value > node.getValue() && node.getRight() != null)
			return search(node.getRight(), value);
		else if (value < node.getValue() && node.getLeft() != null)
			return search(node.getLeft(), value);
		
		return null;
	}
	
	public void insert(Node node, int value) {
		if (root == null) {
			root = new Node(value);
			root.setBalanceFactor(0);
			return;
		}
		
		Queue<Node> nodeQueue = new LinkedList<Node>();
		_insert(node, value, nodeQueue);
	}
	
	private void _insert(Node node, int value, Queue<Node> nodeQueue) {
		if (value < node.getValue()) {
			if (node.getLeft() == null) {
				node.setLeft(new Node(value));
				nodeQueue.add(node);
				updateBalanceFactor(nodeQueue);
				stackPathToValue(value);
				return;
			}
			
			nodeQueue.add(node);
			_insert(node.getLeft(), value, nodeQueue);
		}
		
		else if (value > node.getValue()) {
			if (node.getRight() == null) {
				node.setRight(new Node(value));
				nodeQueue.add(node);
				updateBalanceFactor(nodeQueue);
				stackPathToValue(value);
				return;
			}
			
			nodeQueue.add(node);
			_insert(node.getRight(), value, nodeQueue);
		}
		else
			System.out.println("O valor já existe na árvore");
	}
	
	private void updateBalanceFactor(Queue<Node> nodeQueue) {
		if (root == null)
			return;
		
		Node node = null;
		_updateBalanceFactor(nodeQueue, node);
	}
	
	private void _updateBalanceFactor(Queue<Node> nodeQueue, Node node) {		
		node = nodeQueue.poll();
		int leftSubtreeHeight;
		int rightSubtreeHeight;
		
		if(node.getLeft() != null) {
			leftSubtreeHeight = countHeight(node.getLeft(), 1);
		}
		else 
			leftSubtreeHeight = 0;
		
		if(node.getRight() != null) {
			rightSubtreeHeight = countHeight(node.getRight(), 1);
		}
		else
			rightSubtreeHeight = 0;
		
		node.setBalanceFactor(leftSubtreeHeight - rightSubtreeHeight);
		
		if(!nodeQueue.isEmpty())
			_updateBalanceFactor(nodeQueue, node);
		
		return;
	}
	
	public int countHeight(Node node, int height){
        if(node.getLeft() != null && node.getRight() != null){
            height = Math.max(countHeight(node.getLeft(), height + 1), countHeight(node.getRight(), height + 1));
        } else if(node.getLeft() != null){
            height = countHeight(node.getLeft(), height + 1);
        } else if(node.getRight() != null){
            height = countHeight(node.getRight(), height + 1);
        }

        return height;
    }
	
	private void checkRotation(Node node, Stack<Node> stack) {
		Node parent = null;
		
		if (!stack.isEmpty())
			parent = stack.pop();
		
		if(node.getBalanceFactor() > 1) {
			if(node.getLeft().getBalanceFactor() > 0) {
				if(parent != null) {
					//System.out.println("\n\nRotação a direita porque FB é positivo e seu filho a esquerda também tem FB positivo");
					JOptionPane.showMessageDialog(null, "Rotação a direita porque FB é positivo e seu filho a esquerda também tem FB positivo");
					parent.setLeft(rightRotation(node));
				} else {
					//System.out.println("\n\nRotação a direita porque FB é positivo e seu filho a esquerda também tem FB positivo");
					JOptionPane.showMessageDialog(null, "Rotação a direita porque FB é positivo e seu filho a esquerda também tem FB positivo");
					rightRotation(node);
				}
				
				System.out.println();
				System.out.println();
				
				return;
			} else if (node.getLeft().getBalanceFactor() < 0) {
				if (parent != null) {
					//System.out.println("\n\nRotação a dupla a direita porque FB é positivo e seu filho a esquerda tem FB negativo");
					JOptionPane.showMessageDialog(null, "Rotação a dupla a direita porque FB é positivo e seu filho a esquerda tem FB negativo");
					parent.setLeft(doubleRightRotation(node));
				} else {
					//System.out.println("\n\nRotação a dupla a direita porque FB é positivo e seu filho a esquerda tem FB negativo");
					JOptionPane.showMessageDialog(null, "Rotação a dupla a direita porque FB é positivo e seu filho a esquerda tem FB negativo");
					doubleRightRotation(node);
				}
				
				System.out.println();
				System.out.println();

				return;
			}
		} else if(node.getBalanceFactor() < -1) {
			if(node.getRight().getBalanceFactor() < 0) {
				if (parent != null) {
					//System.out.println("\n\nnRotação a esquerda porque FB é negativo e seu filho a direita também tem FB negativo");
					JOptionPane.showMessageDialog(null, "Rotação a esquerda porque FB é negativo e seu filho a direita também tem FB negativo");
					parent.setRight(leftRotation(node));
				} else {
					//System.out.println("\n\nRotação a esquerda porque FB é negativo e seu filho a direita também tem FB negativo");
					JOptionPane.showMessageDialog(null, "Rotação a esquerda porque FB é negativo e seu filho a direita também tem FB negativo");
					leftRotation(node);
				}
				
				System.out.println();
				System.out.println();
				
				return;
			} else if (node.getRight().getBalanceFactor() > 0) {
				if (parent != null) {
					//System.out.println("\n\nRotação a dupla a esquerda porque FB é negativo e seu filho a direita tem FB positivo");
					JOptionPane.showMessageDialog(null, "Rotação a dupla a esquerda porque FB é negativo e seu filho a direita tem FB positivo");
					parent.setRight(doubleLeftRotation(node));
				} else {
					//System.out.println("\n\nRotação a dupla a esquerda porque FB é negativo e seu filho a direita tem FB positivo");
					JOptionPane.showMessageDialog(null, "Rotação a dupla a esquerda porque FB é negativo e seu filho a direita tem FB positivo");
					doubleLeftRotation(node);
				}
				
				System.out.println();
				System.out.println();

				return;
			}
		}
	}
	
	private Node leftRotation(Node node) {
		Node aux = node.getRight();
		
		if(aux.getLeft() == null)
			node.setRight(null);
		else
			node.setRight(aux.getLeft());
		
		aux.setLeft(node);
		//node.setRight(null);
		
		if(node == root)
			root = aux;

		return aux;
	}
	
	private Node rightRotation(Node node) {
		Node aux = node.getLeft();
		
		if(aux.getRight() == null)
			node.setLeft(null);
		else
			node.setLeft(aux.getRight());
		
		aux.setRight(node);		
		
		if(node == root)
			root = aux;
		
		return aux;
	}
	
	private Node doubleLeftRotation(Node node) {
		node.setRight(rightRotation(node.getRight()));
		return leftRotation(node);
	}
	
	private Node doubleRightRotation(Node node) {
		node.setLeft(leftRotation(node.getLeft()));
		return rightRotation(node);
	}
	
	private void stackPathToValue(int value) {
		Node node = root;
		Stack<Node> stack = new Stack<Node>();
		
		_stackPathToValue(value, node, stack);
	}
	
	private void _stackPathToValue(int value, Node node, Stack<Node> stack) {
		if (node == null)
			return;
		
		if (value < node.getValue() && node.getLeft() != null) {
			stack.push(node);
			_stackPathToValue(value, node.getLeft(), stack);
		} else if (value > node.getValue() && node.getRight() != null) {
			stack.push(node);
			_stackPathToValue(value, node.getRight(), stack);
		}
		
		rotationNeeded(stack);
	}
	
	private void rotationNeeded(Stack<Node> stack) {
		if(stack.isEmpty())
			return;
		
		Node node = stack.pop();
		
		if (node.getBalanceFactor() > 1 || node.getBalanceFactor() < -1)
			checkRotation(node, stack);
		
		rotationNeeded(stack);
	}
	
	public void remove(int value) {
		Node node = root;
		
		if(search(node, value) == null) {
			System.out.println("O valor digitado não está na árvore!");
			return;
		}
		
		if(node.getValue() == value && node.getLeft() == null && node.getRight() == null) {
			root = null;
			return;
		} else if(node.getValue() == value && node.getLeft() != null) {
			if(node.getLeft().getRight() != null) {
				Node aux = node.getLeft();
				Node parent = node;
				while(aux.getRight() != null) {
					parent = aux;
					aux = aux.getRight();
				}
				parent.setRight(null);
				aux.setRight(node.getRight());
				aux.setLeft(node.getLeft());
				root = aux;
			}
			else {
				node.getLeft().setRight(node.getRight());
				root = node.getLeft();
			}
			
        	return;
        }else if (node.getValue() == value && node.getRight() != null) {
        	
        	root = node.getRight();
        	return;
        }
		
		Queue<Node> nodeQueue = new LinkedList<Node>();
		_remove(value, node, nodeQueue);
	}
	
	private Node _remove(int value, Node node, Queue<Node> nodeQueue) {		
        if (value < node.getValue()) {
        	nodeQueue.add(node);
            node.setLeft(_remove(value, node.getLeft(), nodeQueue));
        } else if (value > node.getValue()) {
        	nodeQueue.add(node);
        	node.setRight(_remove(value, node.getRight(), nodeQueue));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
                updateBalanceFactor(nodeQueue);
				stackPathToValue(value);
            } else if (node.getLeft() == null) {
                node = node.getRight();
                updateBalanceFactor(nodeQueue);
				stackPathToValue(value);
            } else if (node.getRight() == null) {
                node = node.getLeft();
                updateBalanceFactor(nodeQueue);
				stackPathToValue(value);
            } else {
                Node aux = node.getLeft();
                Node parent = node;
                while (aux.getRight() != null) {
                	nodeQueue.add(aux);
                	parent = aux;
                    aux = aux.getRight();
                }
                aux.setRight(node.getRight());
                if(aux != node.getLeft())
                	aux.setLeft(node.getLeft());
                else
                	aux.setLeft(null);
                
                parent.setRight(null);
                node = aux;
                updateBalanceFactor(nodeQueue);
				stackPathToValue(value);
            }
        }

        return node;
    }
	
	public void printTree(Node node) {
		if (node.getLeft() != null)
			printTree(node.getLeft());
		
		System.out.print("Valor " + node.getValue());
		System.out.print(" - FB " + node.getBalanceFactor());
		if (node.getLeft() != null)
			System.out.print(" - Left " + node.getLeft().getValue());
		if (node.getRight() != null)
			System.out.print(" - Right " + node.getRight().getValue());
		
		System.out.println();
		
		if (node.getRight() != null)
			printTree(node.getRight());
	}
}
