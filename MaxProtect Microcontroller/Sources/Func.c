#include <msp430g2553.h>
#include <math.h>
#include"Init.h"
#include"Funct.h"

void RW_adc(void){

    while(ADC10CTL1 & BUSY);
    ADC10CTL0 &= ~ENC;

    ADC10SA = (signed int)adc;
    ADC10DTC1 |= 3;

    ADC10CTL0 |= ENC + ADC10SC;

    while(ADC10CTL1 & BUSY);

    x = adc[0];
    y = adc[1];
    z = adc[2];


}





signed int accel_conv(signed int vec){

    signed int mid;


    if(vec == x){
        mid = 513;
    }
    else if(vec == y){
        mid = 519;
    }
    else if(vec == z){
        mid = 515;
    }


    return 4*(vec-mid)+(vec-mid)/2;

}

void transmit( signed int val){

    first = val  & 0xFF;
    second = val >> 8;

    while ((UCA0STAT & UCBUSY));
    UCA0TXBUF = first;
    while ((UCA0STAT & UCBUSY));
    UCA0TXBUF = second;
    while ((UCA0STAT & UCBUSY));

}
