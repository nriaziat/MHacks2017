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

char receivedChar;
boolean newData = 0;
char oldChar;
int led = 13;

void setup(){
  Serial.begin(9600);
  AFMS.begin();
  myMotor->setSpeed(80); 
  pinMode(13, OUTPUT);
}

void loop(){
  digitalWrite(led, LOW);
  recvInfo();
}
  
  
void recvInfo(){
  if (Serial.available() > 0){
    receivedChar = Serial.read();
    int val = receivedChar - '0';
    if (val == 1){
      motorFunction();
    }
  }
}

void motorFunction(){

  myMotor->step(400, FORWARD, SINGLE); 
  myMotor->step(400, BACKWARD, SINGLE);
  digitalWrite(led, HIGH);
  delay(1000);
}

