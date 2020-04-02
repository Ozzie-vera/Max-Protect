#include <msp430g2553.h>
#include <math.h>
#include"Init.h"
#include"Funct.h"



int main(void)
{
    init_hw();
    signed int thresh = 600;
    while(1){

        RW_adc();// 

        x_acc = accel_conv(x);
        y_acc = accel_conv(y);
        z_acc = accel_conv(z);

        if( (x_acc > thresh) || (y_acc > thresh) || (z_acc > thresh)){
            while ((UCA0STAT & UCBUSY));
            UCA0TXBUF = 0x42;
            transmit(x_acc);
            transmit(y_acc);
            transmit(z_acc);

        }
    }

}

