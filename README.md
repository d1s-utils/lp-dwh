# lp-dwh
Long-polling events bridge for Discord. Interacts with your long-polling API through https://github.com/d1snin/long-polling and sends received events to Discord webhook.

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
