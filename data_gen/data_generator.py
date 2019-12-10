## Script that emulates a sensor placed inside a vase
# Sends water levels on the soil, temperature and wind speed values
# All messages MUST be timestamped


import time
import json
import pika
import calendar
from random import uniform,randrange

connection=pika.BlockingConnection(pika.ConnectionParameters('localhost', 5672))
channel=connection.channel()
channel.queue_declare(queue='plants_info')
def main():
    id_planta=1
    while True:
        (soil,temp,wind)=data_generation()
        #send json to message queue
        send(id_planta, soil,temp,wind)
        time.sleep(5)
    connection.close()

def send(id_planta,soil, temp,wind):
    #create json
    d={"plant_id": id_planta, "soil":soil,"temp":temp,"wind":wind, "timestamp": str(calendar.timegm(time.gmtime()))}
    y=json.dumps(d)
    channel.basic_publish(exchange='', routing_key='plants_info',body=y)
    print ("Sending "+y)



def data_generation():
    soil=uniform(0,100) #percentage
    temp=uniform(20,22)
    wind=randrange(0,30)
    return soil,temp,wind



main()
