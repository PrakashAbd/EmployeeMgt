FROM tomcat:8
rm -rf /usr/local/tomcat/webapps/*
COPY ./target/EmployeeMgt.war /usr/local/tomcat/webapps/ROOT.war
cmd ["catalina.sh", "run"]
