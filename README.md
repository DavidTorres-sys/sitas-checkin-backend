# SITAS CHECK-IN

This Docker Compose configuration allows you to quickly set up an Oracle database instance using the `gvenzl/oracle-free:latest` Docker image.
Also using a Dockerfile for the Spring-Boot app with Maven.

## Prerequisites

- Docker installed on your machine.

## Getting Started

1. Clone or download this repository to your local machine.

2. Navigate to the directory containing the `prometheus.yml` file and change the ip in the last line.

3. Run the following commands to start the containers:
   ```bash
    docker pull prom/prometheus
    docker run -d -p 9090:9090 -v .\prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
    ```
    ```bash
    docker-compose up --build
    ```

   This command will pull the necessary Docker image (if not already present) and start the container.

4. Wait for the container to initialize. You can monitor the progress by checking the logs:

    - To see all the containers:

      ```bash
      docker ps
      ```

    - For example, to see the logs of the Oracle container:

      ```bash
      docker logs -f oracle
      ```

   Once the initialization is complete, you should see a message indicating that the database is ready to use.

5. Connect to the Oracle database using your preferred client (e.g., SQL Developer) with the following connection details:

    - Hostname: localhost, host.docker.internal 127.0.0.1 (or the IP address of your Docker host machine)
    - Port: 1521
    - SID: free (or the name of your pluggable database)
    - Username: system or sys
    - Password: password (or the password you specified in the environment variables)
    
6. Once the application is running, you can access the Swagger UI at

   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
## Customization

You can customize the Oracle database configuration by modifying the `compose.yaml` file:

- **Environment Variables**: Adjust the environment variables (`ORACLE_ALLOW_REMOTE`, `ORACLE_PASSWORD`, `ORACLE_PDB`) to suit your requirements. These variables control various aspects of the Oracle database setup.

- **Volumes**: Modify the volume mapping (`./data:/opt/oracle/oradata`) to specify the location where the Oracle database data files will be stored on your local machine.

## Cleanup

To stop and remove the Oracle container, run the following command:

```bash
docker-compose down
