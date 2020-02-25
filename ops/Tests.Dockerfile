FROM hazelcast/maven-dependencies:latest

ENV browser chrome
ENV TINI_VERSION v0.18.0

ADD https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini /tini
RUN chmod +x /tini
ENTRYPOINT ["/tini", "--"]

COPY . .

CMD mvn clean test -Dbrowser=${browser} -DisBrowserstack=true -Dbrowserstack.localIdentifier=MC_UI_TESTS
