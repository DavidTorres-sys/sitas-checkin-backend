# SITAS CHECK-IN

This Docker Compose configuration allows you to quickly set up an Oracle database instance using the `gvenzl/oracle-free:latest` Docker image.
Also using a Dockerfile for the Spring-Boot app with Maven.

## Prerequisites

- Docker installed on your machine.

## Getting Started

1. Clone or download this repository to your local machine.

2. Navigate to the directory containing the `compose.yaml` file.

3. Run the following command to start the database:

    ```bash
    docker-compose up --build oracle
    ```
4. After the database is build, run the next command to test and build the backend.  
    ```bash
   docker-compose up --build backend
   ```
5. In the test folder can be found a example test. 
Run test locally (on intellij) automatically fails. On step 4 tests are being running, so if 
a test fails during the backend build, the log should display witch tests fails. Use this to test the backend


6Connect to the Oracle database using your preferred client (e.g., SQL Developer) with the following connection details:

    - Hostname: localhost, host.docker.internal 127.0.0.1 (or the IP address of your Docker host machine)
    - Port: 1521
    - SID: free (or the name of your pluggable database)
    - Username: system or sys
    - Password: password (or the password you specified in the environment variables)
    
7.Once the application is running, you can access the Swagger UI at

   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
## Customization

You can customize the Oracle database configuration by modifying the `compose.yaml` file:

- **Environment Variables**: Adjust the environment variables (`ORACLE_ALLOW_REMOTE`, `ORACLE_PASSWORD`, `ORACLE_PDB`) to suit your requirements. These variables control various aspects of the Oracle database setup.

- **Volumes**: Modify the volume mapping (`./data:/opt/oracle/oradata`) to specify the location where the Oracle database data files will be stored on your local machine.

## Cleanup

To stop and remove the containers, run the following command:

```bash
docker-compose down
