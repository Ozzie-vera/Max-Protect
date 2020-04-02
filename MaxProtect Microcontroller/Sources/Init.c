#include <msp430g2553.h>
#include <math.h>
#include"Init.h"
#include"Funct.h"

void init_hw(void){

    WDTCTL = WDTPW | WDTHOLD; // stop watchdog timer

    if (CALBC1_16MHZ ==0xFF || CALDCO_16MHZ == 0xFF) while(1);//Trap cpu if calibration contants erased
    DCOCTL = CALDCO_16MHZ; // have DCO at 16MHZ
    BCSCTL1 = CALBC1_16MHZ;

    P1DIR |= 0xFF;                                              //all P1.X outputs
    P1OUT = 0;                                                  //all P1.X Reset
    P2DIR |= 0xFF;                                              //all P2.X outputs
    P2OUT = 0;                                                  //all P2.X Reset
    P3DIR |= 0xFF;                                              //all P3.X outputs
    P3OUT = 0;                                                  //all P3.X Reset
	//adding this to test git
    P1SEL |= BIT1 + BIT2;                                       // set UART RX and TX enabled
    P1SEL2 |= BIT1 + BIT2;

    UCA0CTL1 |= UCSSEL_2;                                       // SMCLK = 16MHZ,
    UCA0BR0 = 0x82;                                             //16MHz/9600 = 1666 = Ox0082 + 0x0600
    UCA0BR1 = 0x06;
    UCA0MCTL = UCBRS_6;                                         //Modulation error .6666 of 16Mhz/9600
    UCA0CTL1 &= ~UCSWRST;                                       // Initialize state machine
    IE2 |= UCA0RXIE;

    ADC10CTL1 |= INCH_7 + CONSEQ_1 + ADC10SSEL_3;               // set single reapeated sequence
                                                                // and highest analog input for RSC mode A7
    ADC10AE0 |= 0xF0;                                           //select analog inputs

    ADC10CTL0 |= SREF_0;                                        //setup reference voltage

    ADC10CTL0 |= MSC + ADC10ON ;                                // Multiple sample and conversions and interrupt flag enabled
                                                                // And turn on ADC10
    ADC10DTC0 |= BIT0;

}

