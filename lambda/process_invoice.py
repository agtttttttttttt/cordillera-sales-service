import json
import urllib.request

def lambda_handler(event, context):
    for record in event.get("Records", []):
        body = record.get("body", "")
        print(f"Mensaje recibido de SQS: {body}")

        # Simular envio de email o notificacion
        print(f"Notificacion procesada: {body}")

    return {"statusCode": 200, "body": "OK"}
