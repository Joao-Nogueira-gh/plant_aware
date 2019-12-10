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
    d={"type":"USER_REG", "username": "Plant_Lover99", "password":"bromelia123","email":"email@ua.pt"}
    y=json.dumps(d)
    channel.basic_publish(exchange='', routing_key='plants_info',body=y)
    print ("Sending "+y)


main()
