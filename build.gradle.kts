plugins {
    kotlin("jvm") version "1.9.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "21"
    }

    compileJava {
        options.release.set(21)
    }

    processResources {
        from("src/main/resources")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE // 중복 파일 오류 방지
    }

    shadowJar {
        archiveFileName.set("MOTD.jar")
        destinationDirectory.set(file("C:/Users/yongy/Desktop/1.21.8 ㄴ놀ㅇ/plugins"))
        mergeServiceFiles()
    }

    build {
        dependsOn(shadowJar)
    }
}

tasks.processResources {
    from(sourceSets.main.get().resources.srcDirs) {
        include("server-icon/**")
    }
}
