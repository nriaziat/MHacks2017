#include <Wire.h>
#include <Adafruit_MotorShield.h>
#include "utility/Adafruit_MS_PWMServoDriver.h"

// Create the motor shield object with the default I2C address
Adafruit_MotorShield AFMS = Adafruit_MotorShield(); 
// Or, create it with a different I2C address (say for stacking)
// Adafruit_MotorShield AFMS = Adafruit_MotorShield(0x61); 

// Connect a stepper motor with 200 steps per revolution (1.8 degree)
// to motor port #2 (M3 and M4)
Adafruit_StepperMotor *myMotor = AFMS.getStepper(200, 1);

String receivedChar;
boolean newData = 0;
String oldChar;
int led = 13;

void setup(){
  Serial.begin(9600);
  AFMS.begin();
  myMotor->setSpeed(300); 
}

void loop(){
  recvInfo();
}
  
  
void recvInfo(){
  char x = 'c';
  int i = 0;
  if (Serial.read() > 0){
    receivedChar = Serial.readString();
    Serial.println(receivedChar);
    while (x != '\0'){
       x = receivedChar[i];
       Serial.println(x);
       motorFunction(x);
       i++;
       
    }
  }
}

void motorFunction(int x){
  switch (x){
    case 49: 
      myMotor->step(100, FORWARD, DOUBLE); 
      myMotor->step(100, BACKWARD, DOUBLE);
      break;
    case 50:
      myMotor->step(200, FORWARD, DOUBLE); 
      myMotor->step(200, BACKWARD, DOUBLE);
      break;
    case 51:
      myMotor->step(300, FORWARD, DOUBLE); 
      myMotor->step(300, BACKWARD, DOUBLE);
      break;
    case 52:
      myMotor->step(400, FORWARD, SINGLE); 
      myMotor->step(400, BACKWARD, SINGLE);
      break;
  }
}

