FROM tomcat:8
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/Boot_App-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
