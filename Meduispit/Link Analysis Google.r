# Google matrica A s vjv beta

# INIT HERE
# ================================================================
mat <-matrix(c(0, 0.5, 0, 0, 0,
               0, 0, 1/3, 0, 0,
               0, 0.5, 0, 1, 0,
               0, 0, 1/3, 0.0, 1,
               1, 0, 1/3, 0, 0), byrow = TRUE, nrow = 5)

#r<-matrix(c(1/9, 1/3, 1/3, 2/9), byrow=TRUE, nrow=5) #vektor ranga
mat
beta = 0.8
r0<-matrix(c(1/5, 1/5, 1/5, 1/5, 1/5),byrow=TRUE, nrow=5)
N = 5 # broj cvorova
matN <- matrix(c(1/N, 1/N, 1/N, 1/N, 1/N,
                 1/N, 1/N, 1/N, 1/N,1/N,
                 1/N, 1/N, 1/N, 1/N, 1/N,
                 1/N, 1/N, 1/N, 1/N, 1/N,
                 1/N, 1/N, 1/N, 1/N, 1/N), byrow=TRUE, nrow=N, ncol=5)
matN
# ================================================================
A<- beta*mat + (1-beta)*matN
A
res= (A%*%A)%*%A
r3= res%*%r0
r3