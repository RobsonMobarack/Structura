package com.msoft.structura;

public class Node {
	private int value;
	private int balanceFactor;
	private Node left;
	private Node right;
	
	public Node() {
		
	}
	
	public Node(int value) {
		this.value = value;
		this.balanceFactor = 0;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getBalanceFactor() {
		return balanceFactor;
	}
	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
}
