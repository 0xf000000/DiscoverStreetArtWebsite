#!/bin/bash


echo "OKAY BUILDING RELEASE VERSIONS FROM FRONTEND";




# moving the newly produced files into the backend rootdir
cp ./html/* ../../backend/src/main/resources/templates
cp ./css/*.css  ../../backend/src/main/resources/static/assets
cp ./js/*.js ../../backend/src/main/resources/static/
echo "checking if java is installed"
if type -p java; then 
echo "found java executable!"

else 
 echo "no java please install java in order to start the backend!"
 exit;
fi 




# checking java version
JAVA_VERSION=$(java --version | grep -c 'OpenJDK' )
echo $JAVA_VERSION

cd ../../backend
mvn clean spring-boot:run
