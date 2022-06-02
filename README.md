# lp-dwh
Long-polling events bridge for Discord. Interacts with your long-polling API through https://github.com/d1snin/long-polling and sends received events to Discord webhook.

![image](https://user-images.githubusercontent.com/68169685/171650807-c0b9db5d-4e0c-4ebc-bd70-c6bdcac19ff9.png)


## Building and running.

Build the OCI image using the `bootBuildImage` Gradle task:

```sh
./gradlew bootBuildImage
```

Edit the `docker-compose.yml` file with your own configuration.

Run using `docker-compose`:

```sh
docker-compose up -d
```
