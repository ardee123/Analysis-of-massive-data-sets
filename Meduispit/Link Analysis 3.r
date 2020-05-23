#Link Analysis: r3 = M * r0
RMSE <- function(r3, r, N){
  sum=0
  for(i in 1:N){
    sum=sum + (r3[i][1] - r[i][1])^2
  }
  return (sum/N)
}

# INIT HERE
# =============================================================================

mat <-matrix(c(0, 0.5, 0, 0, 0,
               0, 0, 1/3, 0, 0,
               0, 0.5, 0, 1, 0,
               0, 0, 1/3, 0.0, 1,
               1, 0, 1/3, 0, 0), byrow = TRUE, nrow = 5)

r<-matrix(c(1/9, 1/3, 1/3, 2/9), byrow=TRUE, nrow=4) #vektor ranga

r0<-matrix(c(0.25,0.25,0.25,0.25),byrow=TRUE, nrow=4) # uniformna razdioba r0

N=5 # broj cvorova

# =============================================================================

mat=(mat%*%mat)%*%mat
r3= mat%*%r0
r3

rmse = RMSE(r3, r, N)
rmse
