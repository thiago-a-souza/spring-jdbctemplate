## Starting up MySQL container

```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=my-password -p 3306:3306 -d mysql
```

## Creating table

```
$ docker exec -it mysql bash

# mysql -u root -p

mysql> create database test;

mysql> use test;

mysql> create table person (
          id bigint not null,
          first_name varchar(30) not null,
          last_name  varchar(30) not null,
          primary key(id)
       );
```

## Running project 

```
mvn spring-boot:run
```

## Usage

**1.** Add new person

```
curl -X POST -s http://localhost:8080/person \
-H "Content-Type: application/json" \
-d '{"id" : 1, "firstName" : "quentin", "lastName" : "tarantino"}'

curl -X POST -s http://localhost:8080/person \
-H "Content-Type: application/json" \
-d '{"id" : 2, "firstName" : "peter", "lastName" : "jackson"}'

curl -X POST -s http://localhost:8080/person \
-H "Content-Type: application/json" \
-d '{"id" : 3, "firstName" : "steven", "lastName" : "spielberg"}'
```

**2.** Get person by id

```
curl -X GET -s http://localhost:8080/person/1
```

**3.** Get all rows 

```
curl -X GET -s http://localhost:8080/person
```

**4.** Delete person by id

```
curl -X DELETE -s http://localhost:8080/person/3
```

**5.** Update person

```
curl -X PUT -s http://localhost:8080/person \
 -H "Content-Type: application/json" \
 -d '{"id" : 1, "firstName" : "Quentin", "lastName" : "Tarantino"}'
```


