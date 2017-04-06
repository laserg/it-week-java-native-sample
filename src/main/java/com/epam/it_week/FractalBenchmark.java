package com.epam.it_week;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class FractalBenchmark {

    @Param({"10", "100", "1000", "3000"})
    private int size;

    @Setup(Level.Trial)
    public void doSetup(Blackhole blackhole) {
        blackhole.consume(com.epam.it_week.fractal.java.Biomorph.class);
        blackhole.consume(com.epam.it_week.fractal.jni.Fractal.class);
        blackhole.consume(com.epam.it_week.fractal.jna.Fractal.class);
        blackhole.consume(com.epam.it_week.fractal.jnr.Fractal.class);
    }

    @Benchmark
    public void testJavaDraw() {
        new com.epam.it_week.fractal.java.Biomorph(size, size);
    }

    @Benchmark
    public void testJniDraw() {
        new com.epam.it_week.fractal.jni.Fractal(size, size);
    }

    @Benchmark
    public void testJnaDraw() {
        new com.epam.it_week.fractal.jna.Fractal(size, size);
    }

    @Benchmark
    public void testJnrDraw() {
        new com.epam.it_week.fractal.jna.Fractal(size, size);
    }

    public static void main(String... args) throws Exception{
        Options opts = new OptionsBuilder()
                .include(FractalBenchmark.class.getCanonicalName())
//                .jvmArgs("-Djava.library.path=D:\\NATIVE\\it-week-java-native-sample\\libs\\natives")
                .resultFormat(ResultFormatType.TEXT)
                .measurementIterations(5)
                .warmupIterations(5)
                .forks(1)
                .build();

        for (RunResult rr : new Runner(opts).run()) {
            Result r = rr.getPrimaryResult();
            System.out.println("API replied benchmark score: "
                    + r.getScore() + " "
                    + r.getScoreUnit() + " over "
                    + r.getStatistics().getN() + " iterations");
        }

        new com.epam.it_week.fractal.java.Biomorph(100, 100).draw("D:\\out\\javaTest.jpg");
        new com.epam.it_week.fractal.jni.Fractal(100, 100).draw("D:\\out\\jniTest.jpg");
        new com.epam.it_week.fractal.jna.Fractal(100, 100).draw("D:\\out\\jnaTest.jpg");
        new com.epam.it_week.fractal.jna.Fractal(100, 100).draw("D:\\out\\jnrTest.jpg");
    }
}
