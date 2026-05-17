import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "1.9.21"
    id("com.gradleup.shadow") version "9.2.2"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    id("xyz.jpenilla.run-paper") version "3.0.2"
    // com.github.johnrengelman.shadow ENTFERNT – com.gradleup.shadow ersetzt es
}

group = "io.github.black_Kittys22"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    compileOnly("net.luckperms:api:5.4")
    compileOnly("dev.jorel:commandapi-bukkit-core:11.2.0")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.3.0")

    // JDA (Discord Bot)
    implementation("net.dv8tion:JDA:5.0.2") {
        exclude(module = "opus-java")
    }

    // MySQL
    implementation("com.mysql:mysql-connector-j:8.3.0")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    compileKotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    shadowJar {
        archiveBaseName.set("Anarchie")
        archiveVersion.set(version.toString())
        archiveClassifier.set("")
        mergeServiceFiles()
        relocate("net.dv8tion", "io.github.black_Kittys22.libs.jda")
        relocate("com.mysql",   "io.github.black_Kittys22.libs.mysql")
        exclude("*.kotlin_module")
        exclude("META-INF/maven/**")
    }

    runServer {
        minecraftVersion("1.21.11")
    }
}