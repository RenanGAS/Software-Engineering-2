compile:
	mvn compile

refresh:
	mvn clean install

run:
	mvn exec:java -Dexec.mainClass="project.Main"

test:
	mvn test

checkstyle:
	java -jar checkstyle-10.12.3-all.jar -c bcc_engsw2_checks.xml ./src
