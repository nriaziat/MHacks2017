import boto
import boto.s3
import sys, time
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
    bucket_name = 'trigfile'
    conn = boto.connect_s3(AWS_ACCESS_KEY, AWS_SECRET_KEY)

    bucket = conn.get_bucket(bucket_name)

    testfile = 'triggered'
    k = Key(bucket)
    k.key = testfile
    k.set_contents_from_string('{:%m%d%Y%H%M%S}'.format(datetime.datetime.now()))

    return statement('Dispensing!')

@ask.intent("QUANTITY")
def qty():
    conn = boto.connect_s3(AWS_ACCESS_KEY, AWS_SECRET_KEY)

    bucket = conn.get_bucket(bucket_name)

    testfile = 'triggered'
    k = Key(bucket)
    k.key = testfile
    k.set_contents_from_string('{:%m%d%Y%H%M%S}'.format(datetime.datetime.now()))

    return statement('Dispensing!')
    

if __name__ == '__main__':
    app.run()
