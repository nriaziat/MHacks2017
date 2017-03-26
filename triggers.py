import serial
import urllib2
from twilio.rest import TwilioRestClient

ser = serial.Serial('/dev/ttyACM1', 9600)
prevResponse = ''
ser.write('0')
account_sid = "ACd2452950046c0b6a89c8c455019e9754"
auth_token = "0e6d95210e01bc0e3590afb558460bdd"
client = TwilioRestClient(account_sid, auth_token)
prevResponseQuantity = ""
prevResponsePattern = ''
dataQuantity = "0"


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
    # Download the library from twilio.com/docs/libraries

# Get these credentials from http://twilio.com/user/account
    # Make the call
        try:
            responseQuantity = urllib2.urlopen('https://s3.amazonaws.com/trigfile/quantity')
        except urllib2.URLError:
            pass
        else:
            count = 0
            dataQuantity = responseQuantity.read()
            responseQuantity.close()
            if(prevResponseQuantity != dataQuantity and prevResponseQuantity != ''):
                string = ""
                for i in range(1, 5):
                    findThis= "Tray"+str(i)
                    place = dataQuantity.find(findThis)
                    if int(dataQuantity[place+6]) <= 3:
                        if count == 0:
                            string += "?Tray"
                            string += str(i)
                            string += "=Tray"
                            string += str(i)
                            count = 1;
                        else:
                            string += "&Tray"
                            string += str(i)
                            string += "=Tray"
                            string += str(i)
                #print(string)
                call = client.calls.create(to="+15109363524",  # Any phone number
                               from_="+15104910043", # Must be a valid Twilio number
                               url="https://handler.twilio.com/twiml/EH6fbc3c785b3d84e49c7d495bb42299ba"+string)
                #print(call.sid)

            prevResponseQuantity = dataQuantity
        try:
            responsePattern = urllib2.urlopen('https://s3.amazonaws.com/trigfile/pattern')
        except urllib2.URLError:
            pass
        else:
            dataPattern = responsePattern.read()
            responsePattern.close()
            if prevResponsePattern != dataPattern and prevResponsePattern != '': 
                message = client.messages.create(to="+15109363524", from_="+15104910043",
                                     body="You have pills ready to be taken")
            prevResponsePattern = dataPattern

        #conn = boto.connect_s3(AWS_ACCESS_KEY, AWS_SECRET_KEY)

        #bucket = conn.get_bucket(bucket_name)

        #testfile = 'triggered'
        #k = Key(bucket)
        #k.key = testfile
        #k.set_contents_from_string('{:%m%d%Y%H%M%S}'.format(datetime.datetime.now()))
