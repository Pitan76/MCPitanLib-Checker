# MCPitanLib Checker
## 日本語
MCPitanLibやその他前提MODが入っているか、また、前提MODをダウンロードすることもできます。

### ダウンロードできる前提MOD
- MCPitanLib
- Architectury API
- Small Stairs (Additional Small Stairs)

### 使い方
```groovy
repositories {
    maven {
        url = "https://maven.pitan76.net/"
    }
}

dependencies {
    modImplementation "net.pitan:mcpitanlib_checker-fabric:${mcpitanlib_checker_version}"
}
```

`gradle.properties`に以下のように記述してください。
```properties
# Example: 1.0.4
mcpitanlib_checker_version=x.x.x
```

### ライセンス
このMODはMITライセンスのもとで公開されています。詳しくは[LICENSE](LICENSE)をご覧ください。

## English
You can check if MCPitanLib and other prerequisite MODs are installed, and you can also download prerequisite MODs.

### Prerequisite MODs that can be downloaded
- MCPitanLib
- Architectury API
- Small Stairs (Additional Small Stairs)

### How to use
```groovy
repositories {
    maven {
        url = "https://maven.pitan76.net/"
    }
}

dependencies {
    modImplementation "net.pitan:mcpitanlib_checker-fabric:${mcpitanlib_checker_version}"
}
```

Please write as follows in `gradle.properties`.
```properties
# Example: 1.0.4
mcpitanlib_checker_version=x.x.x
```

### License
This MOD is released under the MIT license. Please see [LICENSE](LICENSE) for details.
