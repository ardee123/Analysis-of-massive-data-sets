# Shingles
jacc_sim<-function(x, y){
    and<- bitwAnd(x,y)
    or<- bitwOr(x,y)
    cnt_and =0
    cnt_or = 0
    for(i in 1:length(and)){
      if (and[i]==1)
        cnt_and=cnt_and+1
      if (or[i]==1)
        cnt_or=cnt_or+1
    }
    return(cnt_and/cnt_or)
}

sign_sim<-function(x, y){
  sum=0
  for(i in 1:length(x)){
    if(x[i]==y[i])
      sum=sum+1
  }
  
  return(sum/length(x))
}

# INIT HERE
# ==============================================================================

a<-c(1, 1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0)
b<-c(1, 1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	1,	1,	1,	1,	0,	0,	0)
c<-c(1, 1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1)

signA<-c(1, 1, 1, 3)
signB<-c(1, 3, 1, 3)
signC<-c(0, 1, 1, 0)

N = 3 # broj parova

# ==============================================================================
jaccAB = jacc_sim(a, b)
jaccAC = jacc_sim(a, c)
jaccBC = jacc_sim(b, c)
cat("Jacc_sim(A,B) = ", jaccAB)
cat("Jacc_sim(A,C) = ", jaccAC)
cat("Jacc_sim(B,C) = ", jaccBC)

signAB = sign_sim(signA,signB)
signAC = sign_sim(signA,signC)
signBC = sign_sim(signB,signC)
cat("Sign_sim(A,B) = ", signAB)
cat("Sign_sim(A,C) = ", signAC)
cat("Sign_sim(B,C) = ", signBC)

mae = (abs(signAB-jaccAB) + abs(signAC - jaccAC) + abs(signBC - jaccBC))/N
cat("MAE = ", mae)