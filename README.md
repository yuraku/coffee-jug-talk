# Hazelcast Management Center UI Test Suite

## Running tests on local machine

NOTE:
`IMDG` and `Management Center` should be started before test started by running jars or containers!!!
If you have no setup, you can run `docker-compose` file and all setup will be created and started with the current sources.

By default, tests are looking for the Management Center by the URL/PORT -> `http://localhost:8080`, in case if MC is started on the different url/port, additional parameter should be provided to the maven command:
`-DMC_START_URL={url:port}`.

####To run the tests on local:
```bash
mvn clean test
```

`Chrome` is a default browser to use on local machine. If you would like to use another browser please change the following code:
`src/test/com.hazelcast.mancenter.BaseTest:77-78`

```bash
WebDriverManager.chromedriver().setup();
WebDriverRunner.setWebDriver(new ChromeDriver());
```

replace with (for example change to Firefox (or any other available browser)):
```
WebDriverManager.firefoxdriver().setup();
WebDriverRunner.setWebDriver(new FirefoxDriver());
```

## Running tests on BrowserStack from local machine 

```bash
mvn clean test -Dbrowser=chrome -DisBrowserstack=true -Dbrowserstack.localIdentifier=randomString
```

Available configured browsers:
- chrome
- firefox
- safari
- ie
- edge

* `browserstack.localIdentifier` property is used for the running tests in parallel on BrowserStack, can be used any random string.

## Full test suite
in order to run full test suite with prepared test infrastructure: 
in root project folder run command ->
```./run-ui-tests.sh [browser_name]```

ie ```./run-ui-tests.sh chrome```

and it will start containers with all needed setup.
