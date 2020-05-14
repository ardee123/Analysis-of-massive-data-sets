# Analysis-of-massive-data-sets
Analysis of massive data sets

CF.java is a program which solves the problem of Collaborative Filtering Recommender System
It can give a prediction of user-user or item-item filtering.
It expects the output in the following format:
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

