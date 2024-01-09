#!/bin/bash


echo "OKAY BUILDING RELEASE VERSIONS FROM FRONTEND";

npm run build;

exitCode=$?

if [ $exitCode -ne 0  ]; then
	echo "an error occurred,
       	are u sure your in the right directory? current Directory:";
       	pwd;
	exit;
fi;

# moving the newly produced files into the backend rootdir
cp ./dist/*.html ../../backend/src/main/resources/templates
cp ./dist/assets/* ../../backend/src/main/resources/static/assets

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
