# youtube_video_resolve
it's java resolve youtube video's media and audio

# quick start
    1. download resrouces code
    2. open terminal in project root path
    3. run maven command 
```shell
    mvn clean install 
```
    4.add dependency in your project's pom.xml
```xml
<dependency>
    <groupId>com.des1gn</groupId>
    <artifactId>youtube_resolve</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
    5.show example to use it
```java
    Client client = new Client();
    ResolveMediaInfoVo resolve = client.resolve("{youtube url}");
```