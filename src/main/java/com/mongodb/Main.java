package com.mongodb;

public class Main {

	public static void main(String[] args) {
		CRUD operation = new CRUD();
		operation.create();
		operation.read();
		operation.update();
		operation.delete();
	}
}
