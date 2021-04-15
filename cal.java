public class cal {
    public static double lambda = 25;
    public static double w = 0.038945;
    public static double rho = 0.24981;
    public static double x = 0.0;
    public static double w_ = w-x;
    

    public static void mu_rho(){
        double ans = lambda / rho;
        System.out.println("mu_rho " + ans);
    }

    public static void mu_w(){
        double tmp = 2-rho;
        double tmp1 = 2 * w * (1 - rho);
        double ans = tmp / tmp1;
        System.out.println("mu_w " + ans);
    }

    public static void mu_w_(){
        double tmp = 2-rho;
        double tmp1 = 2 * w_ * (1 - rho);
        double ans = tmp / tmp1;
        System.out.println("mu_w_ " + ans);
    }


    public static void main(String[]args){
        mu_rho();
        mu_w();
    }
}
