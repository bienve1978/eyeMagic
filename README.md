# eyeMagic

Simple server that has as finalize activate the sensor of movement of the raspberry so that when it detects movement send us an email with the video

## Compile

* mvn clean package -DskipTests 

generate deb package to install in the raspberry in the target folder with the name eyeMagic_1.0.0_armhf.deb

## Configuration
There is a configuration file /opt/eyeMagic/conf/eyeMagic.properties
Example: 
* port=8080
* captDir=/home/pi/eyeMagic
* captSec=10
* emails=example1@example.com;example2@example.com

## Start Service
* service eyeMagic start 
