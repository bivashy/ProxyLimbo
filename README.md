# ~~ProxyLimbo~~ (deprecated)

# Consider using [NanoLimbo](https://github.com/Nan1t/NanoLimbo)!

Limbo plugin for minecraft proxy servers

### Maven:
```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.U61vashka</groupId>
    <artifactId>ProxyLimbo</artifactId>
    <version>1.0.2</version>
</dependency>
```
### Gradle:
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.U61vashka:ProxyLimbo:1.0.2'
}

```
### plugin.yml/bungee.yml
```
name: YourPluginHere
main: class.to.main
version: 1.0.0 # Your version here
depends: [ProxyLimbo] # or softDepends
```
