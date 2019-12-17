## Script that emulates a user registering himself on the platform


import time
import json
import pika
import calendar
from random import uniform,randrange

connection=pika.BlockingConnection(pika.ConnectionParameters('localhost', 5672))
channel=connection.channel()
channel.queue_declare(queue='plants_info')
def main():
    send()
    connection.close()

def send():
    #create json
    d={"type":"ADD_PLANT", "owner": "Plant_Lover99", "name":"Rosa", "ideal_temp":20.50, "ideal_soil":15.12, "ideal_wind":1.0}
    y=json.dumps(d)
    channel.basic_publish(exchange='', routing_key='plants_info',body=y)
    print ("Sending "+y)


main()
