import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CF {
    public static int N;
    public static int M;
    public static int type;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String [] line = sc.nextLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        float [][] user_user = new float[N][M];
        float [][] item_item = new float[M][N];

        // Fill in matrix for user-user recommending
        for(int i = 0; i<N; i++){
            line = sc.nextLine().split(" ");
            for(int j= 0; j<M; j++){
                if(line[j].equals("X")){
                    user_user[i][j] = 0;
                } else {
                    user_user[i][j] = Float.parseFloat(line[j]);
                }
            }
        }

        // Fill in matrix for item-item recommending
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                item_item[i][j] = user_user[j][i];
            }
        }
        int Q = Integer.parseInt(sc.nextLine());
        // queries type: (i_coordinate, j_coordinate, type of recommending, cardinality)
        for(int i=0; i<Q; i++){
            line = sc.nextLine().split(" ");
            type = Integer.parseInt(line[2]);
            if(line[2].equals("0")){
                predict(line[0], line[1], line[3], user_user); // T=0, user-user recommending
            } else {
                predict(line[1], line[0], line[3], item_item); // T=1, item-item recommending
            }
        }

    }

    /**
     * Function calculates prediction of missing rating in the matrix given by 
     * coordinates i_coord and j_coord. It normalizes the matrix, then calculates 
     * all cosine similarities. In the end it calculates pearson similarity and
     * gives the prediction. It prints the prediction on the console.
     * @param i_coord i coordinate in matrix of missing rating
     * @param j_coord j coordinate in matrix of a missing rating
     * @param cardinalitet max number of values taken into account when calculating prediction
     * @param mat original matrix
     */
    private static void predict(String i_coord, String j_coord, String cardinalitet, float[][] mat) {
        int n=N;
        int m=M;
        if(type == 1){
            n = M;
            m = N;
        }

        float [][] mat_norm = normalize(mat,n, m);
        List<CosineValue> cosinuses = calculate_cosinuses(mat, mat_norm, i_coord, j_coord, n, m);
        cosinuses = cosinuses.stream()
                .sorted(Comparator.comparing(CosineValue::getCosine).reversed()).collect(Collectors.toList());; // descending sorted by cosine val
        float pred_rating = calculate_rating(cosinuses, cardinalitet);
        DecimalFormat df = new DecimalFormat("#.000");
        BigDecimal bd = new BigDecimal(pred_rating);
        BigDecimal res = bd.setScale(3, RoundingMode.HALF_UP);
        System.out.println(df.format(res));
    }

    /**
     * Function calculates prediction of rating based on largest cosinuses.
     * If cosine is negative or rating is zero, we don't take that
     * values into account.
     * @param cosinuses list of cosinuses and values sorted in descending order by cosine value
     * @param cardinalitet maximum number of cosinuses and ratings taken into account is given by
     * @return prediction = cos_val_sum/cos_sum
     */
    private static float calculate_rating(List<CosineValue> cosinuses, String cardinalitet) {
        float cos_val_sum=0;
        float cos_sum = 0;
        int card_sum=0;
        int card = Integer.parseInt(cardinalitet);

        for(CosineValue cv : cosinuses){
            if(cv.getCosine() > 0  && cv.getRating()!=0) {
                cos_val_sum += cv.getCosine() * cv.getRating();
                cos_sum += cv.getCosine();
                card_sum++;
            }
            if(card==card_sum) break;
        }
        return cos_val_sum/cos_sum;
    }
    
    /**
     * Function calculates pearson correlation coefficient of a row in which
     * wanted prediction element is, and all other rows. It returns a list
     * of all cosine values.
     * @param mat original matrix
     * @param mat_norm normalized matrix
     * @param i_coord i coordinate in matrix of element we want to predict
     * @param j_coord j coordinate in matrix of element we want to predict
     * @param n number of rows
     * @param m number of columns
     * @return cosinuses 
     */
    private static List<CosineValue> calculate_cosinuses(float[][] mat, float[][] mat_norm, String i_coord, String j_coord, int n, int m) {
        List<CosineValue> cosinuses = new ArrayList<>();
        for(int i = 0; i<n; i++){
            float cos = cosine_product(mat_norm[i], mat_norm[Integer.parseInt(i_coord)-1]);
            if((i) == Integer.parseInt(i_coord)-1)continue;
            CosineValue cv = new CosineValue(cos, mat[i][Integer.parseInt(j_coord)-1]);
            cosinuses.add(cv);
        }
        return cosinuses;
    }

    /**
     * Function calculates cosine similarity of two vectors.
     * @param v1 is the first row of the matrix
     * @param v2 is the second row of matrix
     * @return res is given by calculating cosine similarity of two vectors
     */
    private static float cosine_product(float[] v1, float[] v2) {
        float sum_v1v2 = 0;
        float sq_sum_v1 = 0;
        float sq_sum_v2 = 0;
        for(int i = 0; i<v1.length; i++){
            sum_v1v2 += v1[i]*v2[i];
            sq_sum_v1 += v1[i]*v1[i];
            sq_sum_v2 += v2[i]*v2[i];
        }
        float res = (float) ((sum_v1v2)/((Math.sqrt(sq_sum_v1)) * Math.sqrt(sq_sum_v2)));
        return res;
    }


    /**
     * Normalization of matrix. The function calculates avg value of a row by
     * taking a sum of all elements in a row and divides it by number
     * of non-zero elements in that row. Then it goes over the same row and
     * subtracts avg value from each element.
     * @param mat original matrix
     * @param n number of rows
     * @param m number of columns
     * @return new_mat that is normalized
     */
    private static float[][] normalize(float[][] mat, int n, int m) {
        float [][]new_mat = new float[n][m];
        for(int i = 0; i<n; i++){
            float sum = 0;
            int cnt = 0;
            for(int j=0; j<m; j++){
                if(mat[i][j]== 0.0) continue;
                else{
                    sum+=mat[i][j];
                    cnt++;
                }
            }
            float avg = sum/cnt;
            for(int j=0; j<m; j++){
                if(mat[i][j]== 0.0) {
                    new_mat[i][j] = 0;
                } else{
                    new_mat[i][j] = mat[i][j]-avg;
                }
            }
        }
        return new_mat;
    }

    public static class CosineValue{
        float cosine;
        float rating;

        public CosineValue(float cosine, float rating){
            this.cosine=cosine;
            this.rating=rating;
        }

        public float getCosine(){
            return this.cosine;
        }

        public float getRating(){
            return this.rating;
        }
    }
}
