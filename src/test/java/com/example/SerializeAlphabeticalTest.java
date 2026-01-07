package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

class SerializeAlphabeticalTest {

    private static final TestObject TEST_OBJECT = new TestObject("input"
            , new LinkedHashSet<>(Arrays.asList("test2", "test1"))
            , 123_456_789L);


    @Test
    void testJackson2() throws IOException, URISyntaxException {

        final String expected = Files.readString(Path.of(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource("testObject.json")).toURI()));

        com.fasterxml.jackson.databind.json.JsonMapper jackson2JsonMapper =
                com.fasterxml.jackson.databind.json.JsonMapper.builder()
                .configure(com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                .build();

        String actual = jackson2JsonMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(TEST_OBJECT);

        Assertions.assertEquals(expected, actual);


    }

    @Test
    void testJackson3() throws IOException, URISyntaxException {

        final String expected = Files.readString(Path.of(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource("testObject.json")).toURI()));

        tools.jackson.databind.json.JsonMapper jackson3JsonMapper =
                tools.jackson.databind.json.JsonMapper.builder()
                        //this is true by default in jackson 3, but here to be explicit
                        .configure(tools.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                        .build();

        String actual = jackson3JsonMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(TEST_OBJECT);

        Assertions.assertEquals(expected, actual);


    }

    @Test
    void testJackson3WithSortCreatorPropertiesFirstFalse() throws IOException, URISyntaxException {

        final String expected = Files.readString(Path.of(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource("testObject.json")).toURI()));

        tools.jackson.databind.json.JsonMapper jackson3JsonMapper =
                tools.jackson.databind.json.JsonMapper.builder()
                        .configure(tools.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                        .configure(tools.jackson.databind.MapperFeature.SORT_CREATOR_PROPERTIES_FIRST, false)
                        .build();

        String actual = jackson3JsonMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(TEST_OBJECT);

        Assertions.assertEquals(expected, actual);
    }

    private record TestObject(String foo,
                              Set<String> bars,
                              Long baz) {
    }
}
