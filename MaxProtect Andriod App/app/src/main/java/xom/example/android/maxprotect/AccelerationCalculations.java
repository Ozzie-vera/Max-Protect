package xom.example.android.maxprotect;

import java.util.Arrays;

// Default constructor
public class AccelerationCalculations {


    public int[] rawBytes = new int[70];
    public double alpha_xy[] = new double[10];
    public double alpha_zy[] = new double[10];
    public double risk = 0;

    public AccelerationCalculations(byte[] b) {

        for (int i = 0; i < 70;i++) {

            rawBytes[i] = b[i];
            rawBytes[i] = rawBytes[i] & 255;

        }

    }


    public void Accelerations() {
        int num = 10;
        // arrays for storing linear accelerations in x, y, and z directions
        int a_x[] = new int[(num)];
        int a_y[] = new int[num];
        int a_z[] = new int[num];
        double max = 0;

        Arrays.fill(alpha_xy,0);
        Arrays.fill(alpha_zy,0);


        int a = 0;
        for (int i = 0; i <= 64;i++) {

            if((rawBytes[i] == 0x42)) {
                a_x[a] = rawBytes[i+1] + (rawBytes[i+2]<<8) ;
                a_y[a] = rawBytes[i+3] + (rawBytes[i+4]<<8) ;
                a_z[a] = rawBytes[i+5] + (rawBytes[i+6]<<8) ;

                double n = a_x[a] * a_x[a] + a_y[a] * a_y[a];
                double m = a_y[a] * a_y[a] + a_z[a] * a_z[a];
                alpha_xy[a] = 6.48 * Math.sqrt(n);
                alpha_zy[a] = 6.48 * Math.sqrt(m);

                if(alpha_xy[a] > max){
                    max = alpha_xy[a];
                }
                if(alpha_zy[a] > max){
                    max = alpha_zy[a];
                }

                a++;
            }

        }
         risk = 1/(1+Math.exp(12.531-max*0.002));


    }

}

