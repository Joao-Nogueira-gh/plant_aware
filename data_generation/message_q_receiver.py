import pika
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()
channel.queue_declare(queue='plants_info')

def callback(ch, method, properties, body):
    print("Received %r" % body)


channel.basic_consume(queue='plants_info', on_message_callback=callback, auto_ack=True)

print(' [*] Waiting for messages. To exit press CTRL+C')
channel.start_consuming()
connection.close()
