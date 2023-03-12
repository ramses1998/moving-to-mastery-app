FROM openjdk:latest
FROM gradle:latest
WORKDIR /
COPY . .
#RUN gradle bootRun
EXPOSE 8080
CMD ["gradle", "bootRun"]