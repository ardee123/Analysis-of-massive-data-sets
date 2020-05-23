M1<-c(0.33,	0,	0,	-0.67,	0.33)
M2<-c(0,	0,	-1,	-1,	2)
M3<-c(0.2, -0.8, -0.8, 1.2, 0.2)
M4<-c(-0.33, 0, -0.33, 0.33, 0)
M5<-c(-1, 0, 1, -1, 1)
N=4

m<-matrix(c(M1, M2, M3, M4, M5), byrow=TRUE, nrow=5, ncol=5)
m
for(i in 1:5){
  mi=m[i,]
  #print(mi)
  mN=m[N,]
  cat("COS(", N,",", i, ")= ",crossprod(mi,mN)/sqrt(crossprod(mi)*crossprod(mN)), "\n")
}