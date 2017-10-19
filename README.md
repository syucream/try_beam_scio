# try_beam_scio

Try [Apache Beam](https://beam.apache.org/) and [Scio](https://github.com/spotify/scio)

## How to run

* Use sbt runMain

```
# build and run
$ GOOGLE_APPLICATION_CREDENTIALS=/path/to/json_key sbt "runMain syucream.BqSample --project=<project> --runner=<runner>"
```

* Run prebuilt

```
# build by using sbt-pack
$ GOOGLE_APPLICATION_CREDENTIALS=/path/to/json_key sbt pack

# run
$ GOOGLE_APPLICATION_CREDENTIALS=/path/to/json_key ./target/pack/bin/bq-sample --project=<project> --runner=<runner>"
```
