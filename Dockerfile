# 빌드 - jar 파일 생성
FROM gradle:8.10-jdk17 as builder

WORKDIR /app

# Gradle 빌드 파일 및 래퍼 스크립트 복사
COPY gradlew .
COPY gradle/ gradle/
COPY settings.gradle .
COPY build.gradle .

COPY src/ src/

RUN ./gradlew build --no-daemon

# 실행
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/moong-server-0.0.1-SNAPSHOT.jar ./moong-server.jar

CMD ["java", "-jar", "moong-server.jar"]