CREATE TABLE course_rating(
    "student_id"int8 REFERENCES"student"("id")ON UPDATE CASCADE ON DELETE RESTRICT,
    "course_id"int8 REFERENCES"course"("id")ON UPDATE CASCADE ON DELETE RESTRICT,
    PRIMARY KEY("student_id","course_id")
);


HELP.md
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**
!**/src/test/**

### STS ###
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

### IntelliJ IDEA ###
.idea
*.iws
*.iml
*.ipr

### NetBeans ###
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/

### VS Code ###
.vscode/





