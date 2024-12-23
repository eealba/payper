#!/bin/bash
./mvnw clean deploy -Prelease -pl '!e2e-examples,!report-aggregate'