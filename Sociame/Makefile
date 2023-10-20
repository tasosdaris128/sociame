all: run

build:
	clear
	mvn clean
	mvn compile
	mvn package

run:
	make build
	cp src/main/resources/application.properties target/
	cp log4j2.properties target/
	cd target/ && java -Dlog4j2.configurationFile=log4j2.properties -jar Sociame-1.0-SNAPSHOT.jar && cd ..
