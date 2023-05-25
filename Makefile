jar:
	./mvnw clean install

docker:
	docker-compose -f docker-compose.yml up
