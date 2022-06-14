rabbit_mq:
	docker run -d -p 5672:5672 -p 15672:15672 --hostname my-rabbit --name my-rabbit -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management