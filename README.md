# Analysis-of-massive-data-sets
Analysis of massive data sets

CF.java is a program which solves the problem of Collaborative Filtering Recommender System
It can give a prediction of user-user or item-item filtering.
It expects the input in the following format:
	N M
	N rows and M columns
	Q
	q queries; query looks like this (I, J, T, K)
	
	example:
	5 5
	1 2 X 2 4
	2 X 3 X 5
	3 1 X 4 X
	X 2 4 X 4
	1 X 3 4 X
	3
	1 3 0 1
	4 1 0 2
	5 5 1 3
	
	PARAMETERS:
	X is the value in matrix where the user or item doesn't have a rating
	N <=100 is number of rows of the following matrix, also items
	M <=100 is number of columns of following matrix, also users
	Q <=100 is number of queries
	I <= N is i coordinate of the rating we want to predict in the matrix
	J <= M is j coordinate of the rating we want to predict in the matrix
	T is type of filtering, if 0 it is user-user, if 1 it is item-item
	K <= N, M is cardinality, i.e. biggest number of values taken into account

NodeRank.java is a programm that calculates rank of each node in a directed graph.
This is done by PageRank algorithm implementation. It doesn't consider dead end nodes.
It expects the input in the following format:
	n B // n is number of nodes, B is beta probability
	A01 A02 A03 ... A0k0 // in the i-th line there are nodes to which i-th node is looking, 0-based indexing 
	A11 A12 A13 ... A1k3
	A21 A22 A23 ... A2k2
	...
	An−11 An−12 An−13 ... An−1kn
	q // number of queries
	n1 t1 // n-node index, t-iteration number [1, 100]
	n2 t2 
	...
	nq tq
