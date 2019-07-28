# API Orders

An orders crud api.

###  Requisites

* **Java 8**: i.e. install using sdkman:

```bash
curl -s "https://get.sdkman.io" | bash
sdk install java 8.0.222-amzn
```

* **Maven**: i.e. install using sdkman:

```bash
sdk install maven
```

* **MongoDB**: i.e. install using yaourt (archlinux):

```bash
yaourt -S mongodb-bin
```
  
* **Docker**: i.e. install using yaourt (archlinux):

```bash
yaourt -S docker
```

### Start API with docker

**Step 1:** Start docker daemon.

```bash
sudo systemctl start docker
```

**Step 2:** Start app docker env.

```bash
bash docker-startup
```

**Step 3:** Check api info:

```bash
curl "http://localhost:8080/api/navent/info" | python -m json.tool
```

```json
{
    "build": {
        "groupId": "com.navent.api.orders",
        "artifactId": "api-orders",
        "version": "0.0.1-SNAPSHOT"
    }
}
```

**Step 4:** Check api health.
    
```bash
curl "http://localhost:8080/api/navent/health" | python -m json.tool
```

```json
{
    "status": "UP",
    "details": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 502996557824,
                "free": 248056803328,
                "threshold": 10485760
            }
        },
        "mongo": {
            "status": "UP",
            "details": {
                "version": "4.0.0"
            }
        }
    }
}
```

**Step 5:** Go to [swagger ui](http://localhost:8080/api/navent/swagger-ui.html) doc to test api-orders.


### Start API locally

**Step 1:** Start mongodb server.

```bash
sudo systemctl start mongodb
```

**Step 2:** Go to mongo console.
    
```bash
mongo
```

**Step 3** Create/switch to orders db.

```bash
use orders
```

**Step 4:** Create authentication user use by api-orders to connect to orders db.

```javascript
db.createUser(
  {
    user: "orders",
    pwd: "1234",
    roles: [ { role: "readWrite", db: "orders" } ]
  }
);
```

**Step 5:** Check authentication.

```bash
mongo --port 27017 -u "orders" -p "1234" --authenticationDatabase "orders"
```

**Step 6:** Switch to java 8 version.

```bash
sdk use java 8.0.222-amzn
```

**Step 7:** Download api-orders.

```bash
git clone https://github.com/adrianmarino/orders.git
cd orders
```

**Step 8:** Startup api-orders.

```bash
bash startup
```

**Step 9:** Check api info:

```bash
curl "http://localhost:8080/api/navent/info" | python -m json.tool
```

```json
{
    "build": {
        "groupId": "com.navent.api.orders",
        "artifactId": "api-orders",
        "version": "0.0.1-SNAPSHOT"
    }
}
```

**Step 10:** Check api health.
    
```bash
curl "http://localhost:8080/api/navent/health" | python -m json.tool
```


```json
{
    "status": "UP",
    "details": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 502996557824,
                "free": 252307681280,
                "threshold": 10485760
            }
        },
        "mongo": {
            "status": "UP",
            "details": {
                "version": "4.0.11"
            }
        }
    }
}
```

**Step 11:** Go to [swagger ui](http://localhost:8080/api/navent/swagger-ui.html) doc to test api-orders.
