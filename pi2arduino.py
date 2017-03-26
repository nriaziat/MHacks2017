import serial
import urllib2

ser = serial.Serial('/dev/ttyACM0', 9600)
prevResponse = ''
ser.write('0')

    
while True:
    while prevResponse == '':
        try:
            prevResponse = urllib2.urlopen('https://s3.amazonaws.com/trigfile/triggered')
        except urllib2.URLError:
            pass
    else:
        try:
            response = urllib2.urlopen('https://s3.amazonaws.com/trigfile/triggered')
        except urllib2.URLError:
            pass
        else:
            data = response.read()
            response.close()
                        
            if data != prevResponse:
                response = urllib2.urlopen('https://s3.amazonaws.com/trigfile/pattern')
                pattern = response.read()
                response.close()
                ser.write("0" + pattern)
                prevResponse = data
