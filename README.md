# logging-benchmark

Benchmark checking different style of logging with emphesis on ommiting unnecessary processing on disabled log levels.

## Setup

### Hardware

OS: Arch Linux x86_64  
Model: Z170X-Gaming 3  
Kernel: 4.12.4-1-ARCH  
CPU: Intel i7-6700K (8) @ 4.200GHz  
GPU: NVIDIA GeForce GTX 1060 6GB  
Memory: 6987MiB / 16000MiB  

### JDK

openjdk version "1.8.0_144"  
OpenJDK Runtime Environment (build 1.8.0_144-b01)  
OpenJDK 64-Bit Server VM (build 25.144-b01, mixed mode)  


## Results

### Throughput

| Benchmark                                  | Mode  | Cnt | Score            | Error              | Units |
|--------------------------------------------|-------|-----|------------------|--------------------|-------|
| LogBenchmark.control                       | thrpt | 20  | 598,288.09       | ±     23599.418    | ops/s |
| LogBenchmark.manyConcatenations            | thrpt | 20  | 17,436,507.11    | ±     691385.969   | ops/s |
| LogBenchmark.oneConcatenation              | thrpt | 20  | 69,953,873.45    | ±     2765668.873  | ops/s |
| LogBenchmark.manyPlaceholders              | thrpt | 20  | 252,526,488.49   | ±     5668439.671  | ops/s |
| LogBenchmark.onePlaceholder                | thrpt | 20  | 255,301,157.36   | ±     4491132.276  | ops/s |
| LogBenchmark.manyConditionalConcatenations | thrpt | 20  | 305,563,348.03   | ±     6788568.738  | ops/s |
| LogBenchmark.manyConditionalPlaceholders   | thrpt | 20  | 306,108,301.81   | ±     7705621.850  | ops/s |
| LogBenchmark.oneConditionalPlaceholder     | thrpt | 20  | 307,892,817.73   | ±     6539062.424  | ops/s |
| LogBenchmark.oneConditionalConcatenation   | thrpt | 20  | 313,076,821.34   | ±     4229229.723  | ops/s |
| LogBenchmark.plain                         | thrpt | 20  | 929,108,731.86   | ±     21306675.160 | ops/s |
| LogBenchmark.plainConditional              | thrpt | 20  | 1,047,982,909.10 | ±     19078044.519 | ops/s |

### Average time

| Benchmark                                  | Mode  | Cnt | Score            | Error              | Units |
|--------------------------------------------|-------|-----|------------------|--------------------|-------|
| LogBenchmark.control                       | avgt  | 20  | 0.953            | ±     43.233       | ns/op |
| LogBenchmark.manyConcatenations            | avgt  | 20  | 1.093            | ±     1.979        | ns/op |
| LogBenchmark.manyConditionalConcatenations | avgt  | 20  | 3.107            | ±     0.104        | ns/op |
| LogBenchmark.manyConditionalPlaceholders   | avgt  | 20  | 3.159            | ±     0.05         | ns/op |
| LogBenchmark.manyPlaceholders              | avgt  | 20  | 3.246            | ±     0.073        | ns/op |
| LogBenchmark.oneConcatenation              | avgt  | 20  | 3.267            | ±     0.259        | ns/op |
| LogBenchmark.oneConditionalConcatenation   | avgt  | 20  | 3.865            | ±     0.031        | ns/op |
| LogBenchmark.oneConditionalPlaceholder     | avgt  | 20  | 3.933            | ±     0.049        | ns/op |
| LogBenchmark.onePlaceholder                | avgt  | 20  | 12.981           | ±     0.076        | ns/op |
| LogBenchmark.plain                         | avgt  | 20  | 56.932           | ±     0.019        | ns/op |
| LogBenchmark.plainConditional              | avgt  | 20  | 1673.349         | ±     0.015        | ns/op |

## Conclusion

### To wrap or not to wrap

The difference between conditional and not conditional is rather negligible.
If you consider wrapping your DEBUG level logs in `isDebug` conditions and you don't log 10 000 debug messages each action you'd probably be better off optimizing some I/O operation, e.g. a database call.
