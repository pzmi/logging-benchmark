package io.github.pzmi.logbench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LogBenchmark {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogBenchmark.class);

    @Benchmark
    public void control() {
        LOGGER.error("The quick brown fox jumps over the lazy dog");
    }

    @Benchmark
    public void plain() {
        LOGGER.debug("The quick brown fox jumps over the lazy dog");
    }

    @Benchmark
    public void oneConcatenation() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        LOGGER.debug("The quick brown fox jumps over the lazy " + i + " dog");
    }

    @Benchmark
    public void onePlaceholder() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        LOGGER.debug("The quick brown fox jumps over the lazy {} {}", i, "dog");
    }

    @Benchmark
    public void manyConcatenations() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        LOGGER.debug("The " + i + " quick " + i + " brown " + i + " fox " + i + " jumps " + i + " over "
                + i + " the " + i + " lazy " + i + " dog");
    }

    @Benchmark
    public void manyPlaceholders() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        LOGGER.debug("{} {} {} {} {} {} {} {} {} {} {} {} {} {} {} {} {}",
                "The ", i, " quick ", i, " brown ", i, " fox ", i, " jumps ", i, " over ", i, " the ", i, " lazy ", i, " dog");
    }

    @Benchmark
    public void plainConditional() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The quick brown fox jumps over the lazy dog");
        }
    }

    @Benchmark
    public void oneConditionalConcatenation() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The quick brown fox jumps over the lazy " + i + " dog");
        }
    }

    @Benchmark
    public void oneConditionalPlaceholder() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The quick brown fox jumps over the lazy {} {}", i, "dog");
        }
    }

    @Benchmark
    public void manyConditionalConcatenations() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The " + i + " quick " + i + " brown " + i + " fox " + i + " jumps " + i + " over "
                    + i + " the " + i + " lazy " + i + " dog");
        }
    }

    @Benchmark
    public void manyConditionalPlaceholders() {
        int i = ThreadLocalRandom.current().nextInt(0, 10);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{} {} {} {} {} {} {} {} {} {} {} {} {} {} {} {} {}",
                    "The ", i, " quick ", i, " brown ", i, " fox ", i, " jumps ", i, " over ", i, " the ", i, " lazy ", i, " dog");
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(LogBenchmark.class.getSimpleName())
                .forks(2)
                .warmupIterations(10)
                .measurementIterations(10)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .build();
        new Runner(options).run();
    }
}
