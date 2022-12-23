#!/usr/bin/env bash

set -ex

mvn dependency:get -DremoteRepositories=jitpack::default::https://jitpack.io -Dartifact=com.github.shelajev:maven-profiler:main-c980368781-1
mvn -Dmaven.ext.class.path=~/.m2/repository/com/github/shelajev/maven-profiler/main-c980368781-1/maven-profiler-main-c980368781-1.jar test -DforkCount=4