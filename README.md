# MD5 Hash Function for Apache Drill

This project contains a simple example for a custom function for Apache Drill.

This functions is a mask one that allows the following queries to be executed:

```
SELECT MD5(email) customer_guid, email FROM dfs.`example.csv`;
```


## How to Compile Install

Clone and compile.

```

git clone git@github.com:mdasilva/drill-md5-function.git

cd drill-md5-function

mvn package

```

Now download and unpack Apache Drill.

```
wget http://getdrill.org/drill/download/apache-drill-1.9.0.tar.gz
tar xvf apache-drill-1.9.0.tar.gz
```

Copy the jar files from your functions into the 3rdparty directory in the Drill distro

```
cp drill-md5-function/target/*.jar apache-drill-1.9.0/jars/3rdparty
```

Now run drill and test the results

```
$ cd apache-drill-1.9.0/
$ bin/drill-embedded
0: jdbc:drill:zk=local>
SELECT MD5(email) customer_guid, email FROM dfs.`example.csv`;
```


