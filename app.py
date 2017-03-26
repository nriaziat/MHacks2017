import boto
import boto.s3
import sys, time, urllib2
from boto.s3.key import Key
from flask import Flask
from flask_ask import Ask, statement, session, question
import datetime
import os

app = Flask(__name__)
ask = Ask(app, '/')

AWS_ACCESS_KEY = ''
AWS_SECRET_KEY = ''
bucket_name = 'trigfile'

@ask.intent("TRIGGER")
def upload():
    conn = boto.connect_s3(AWS_ACCESS_KEY, AWS_SECRET_KEY)

    bucket = conn.get_bucket(bucket_name)

    testfile = 'triggered'
    k = Key(bucket)
    k.key = testfile
    k.set_contents_from_string('{:%m%d%Y%H%M%S}'.format(datetime.datetime.now()))

    return statement('Dispensing!')

@ask.intent("QUANTITY")
def qty():

    response = urllib2.urlopen("https://s3.amazonaws.com/trigfile/quantity")
    quant = response.read()
    response.close()
    qtyArr = []

    for i in range(1, 5):
        findThis= "Tray"+str(i)
        place = quant.find(findThis)
        qtyArr.append(int(quant[place+6]))

    phrase = ("You have %d left in slot 1, %d left in slot 2, %d left in slot 3,"
                        "and %d left in slot 4." %(qtyArr[0], qtyArr[1], qtyArr[2], qtyArr[3]))

    return statement(phrase)

if __name__ == '__main__':
    app.run()
