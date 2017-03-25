import serial
import urllib2
import time

ser = serial.Serial('/dev/ttyACM0', 9600)
prevResponse = ''
ser.write('0')

while True:
    response = urllib2.urlopen('https://s3.amazonaws.com/trigfile/triggered')
    data = response.read()
    response.close()
		
    if data != prevResponse:
        print data
	ser.write('1')
	time.sleep(1);
        prevResponse = data
