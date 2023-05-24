
ssh:
	@echo 'to run: java -jar blps/klerk-0.0.1-SNAPSHOT.jar'
	@echo 'swagger: localhost:9090/api/swagger-ui.html'
	ssh -L 9090:localhost:9090 s313087@helios.se.ifmo.ru -p 2222


scp:
	scp -P 2222 ./target/klerk-0.0.1-SNAPSHOT.jar s313087@helios.se.ifmo.ru:/home/studs/s313087/blps/

jar:
	./mvnw clean install

docker:
	docker-compose -f docker-compose.yml up