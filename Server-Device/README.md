# v1. Device manager: Distributed device manager

## Application architecture

The application is composed of:

* `Device` interface and `DefaultDevice` implementation: represents a
  device characterized by an identifier, a name, and having a status
  which value changes
  
* `DeviceManager` interface and `DefaultDeviceManager` implementation:
  a manager that registers devices, and assign them unique identifiers
  and maintains informations about their names and status
  
* `ServiceDevice` class: the entry point of the application, that creates
  server and allows the users to connect to the server
* `Status` enumeration of the status
  
## Build and execution

The application is distributed as a Maven project, composed of a
`src/` directory that contains the java sources and a `pom.xml` file
that contains the Maven project description

### Project build

To build the project:

    mvn build
	
### Application execution

To launch the application:

    java -jar target/device-server-1.0.jar <host> <port> <rmi>
	
A device is registered to a device manager, that assigns it an id. The
id value is 0, as it is the first and only device of the device
manager. When the application is launched, you can enter the following
commands: 
* `status 0`: to display the device status
* `start`, `stop`: to change the device status
* `quit`: to stop the application
